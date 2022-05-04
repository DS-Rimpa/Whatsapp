package com.notify.notify.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@Configuration
public class NexmoConfig {

    @Value("${nexmo.uri}")
    private String uri;
    @Value("${nexmo.apiKey}")
    private String apiKey;
    @Value("${nexmo.apiSecret}")
    private String apiSecret;

    public NexmoConfig() {

    }
}
