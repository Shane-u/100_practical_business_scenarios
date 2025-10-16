package com.shane;

import com.shane.service.SmsService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.SocketTimeoutException;

/**
 * @Author: Shane
 * @Date: 2025/10/08/15:32
 * @Description:
 */
@SpringBootTest
@Slf4j
public class RetryTest {

    @Autowired
    private SmsService smsService;

    @Test
    public void Test01() {
        try {
            for (int i = 0; i < 20; i++) {
                smsService.sendSms("Hello" + i);
            }
        } catch (SocketTimeoutException e) {
            throw new RuntimeException(e);
        } catch (CallNotPermittedException e) { // 捕获熔断异常
            log.error("熔断已开启，暂时无法调用：{}", e.getMessage());
        }
    }
}
