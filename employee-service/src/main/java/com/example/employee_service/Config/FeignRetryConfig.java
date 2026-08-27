package com.example.employee_service.Config;

import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRetryConfig {
    @Bean
    public Retryer feignRetry() {

        System.out.println("========== CUSTOM FEIGN RETRYER CREATED ==========");

        return new Retryer.Default(100, 1000, 3) {

            @Override
            public void continueOrPropagate(RetryableException e) {

                System.out.println("========== FEIGN RETRY ==========");
                System.out.println("Retrying: " + e.getMessage());

                super.continueOrPropagate(e);
            }
        };
    }
}
