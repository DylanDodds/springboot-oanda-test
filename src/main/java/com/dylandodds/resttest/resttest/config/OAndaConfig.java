package com.dylandodds.resttest.resttest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix="oanda")
public class OAndaConfig {
    private String apiToken;
    private String accountNumber;
    private String username;
    private String url;
}
