package com.shane.service.impl;

import com.shane.service.SmsService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @Author: Shane
 * @Date: 2025/10/08/15:29
 * @Description:
 */
@Slf4j
@Service
public class SmsServiceImpl implements SmsService {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public SmsServiceImpl(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @Retryable(
            value = {SocketTimeoutException.class, ConnectException.class}, // 按需捕获异常
            maxAttempts = 2, // 最大尝试次数
            backoff = @Backoff(delay = 1000, multiplier = 2) // 指数退避算法
    )
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "backendA", fallbackMethod = "fallbackMethod")
    @Override
    public void sendSms(String msg) throws SocketTimeoutException {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendA");
        log.info("当前状态: {}，失败计数: {}，成功计数: {}",
                circuitBreaker.getState(),
                circuitBreaker.getMetrics().getNumberOfFailedCalls(),
                circuitBreaker.getMetrics().getNumberOfSuccessfulCalls());
        log.info("开始发送消息");
        if (Math.random() < 0.5) {
            log.warn("发送失败，准备重试...");
            RetryContext ctx = RetrySynchronizationManager.getContext();
            int attempt = (ctx == null ? 1 : ctx.getRetryCount() + 1);
            // 这里记录或累加 attempt
            log.info("sendSms 第 {} 次尝试: {}", attempt, msg);
            throw new SocketTimeoutException("网络连接错误");
        } else {
            log.info("发送消息成功！" + msg);
        }
    }

    public void recover(SocketTimeoutException e, String msg) throws SocketTimeoutException {
        // TODO 恢复之后的操作
        log.info("重试失败，进行降级策略，转入消息队列或者进行补偿");
        if (Math.random() < 0.8) {
            log.info("消息队列抢修成功! 消息是:{}", msg);
        } else {
            throw new SocketTimeoutException("网络连接还是出错啦！没兜住！qwq");
        }
    }

    public void fallbackMethod(String msg, SocketTimeoutException e) {
        // 记录日志及逆行后续的补偿
        log.error("熔断触发！消息: {}, 异常: {}", msg, e.getMessage());
    }
}
