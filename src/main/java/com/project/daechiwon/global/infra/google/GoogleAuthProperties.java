package com.project.daechiwon.global.infra.google;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google.oauth")
public class GoogleAuthProperties {
    private String clientId;
    private String clientSecret;
    private String clientRedirect;
}
