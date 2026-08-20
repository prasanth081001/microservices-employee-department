package com.example.employee_service.Config;

import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRetryConfig {
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100,1000,2){
            @Override
            public void continueOrPropagate(RetryableException e){
                System.out.println("Feign Retry :" +e.getMessage());
                super.continueOrPropagate(e);
            }
        };
    }
}
