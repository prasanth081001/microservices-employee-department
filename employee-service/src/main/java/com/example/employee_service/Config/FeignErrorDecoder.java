package com.example.employee_service.Config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder{


    public Exception decode(String methodKey, Response response) {

        if (response.status() == 500 || response.status() == 503) {

            System.out.println(
                    "Feign ErrorDecoder: HTTP " + response.status()
            );
            Long retryAfter=null;

            return new RetryableException(
                    response.status(),
                    "Department Service failed",
                    response.request().httpMethod(),
                    retryAfter,
                    response.request()
            );
        }

        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
