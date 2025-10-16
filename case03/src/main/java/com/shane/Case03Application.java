package com.shane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Author: Shane
 * @Date: 2025/10/07/11:15
 * @Description:
 */
@SpringBootApplication
@EnableRetry
public class Case03Application {
    public static void main(String[] args) {
        SpringApplication.run(Case03Application.class, args);
    }
}
