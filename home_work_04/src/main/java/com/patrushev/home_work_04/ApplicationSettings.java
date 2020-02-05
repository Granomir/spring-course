package com.patrushev.home_work_04;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("application")
public class ApplicationSettings {
    private String locale;
    private String csvPrefix;
    private String adminName;
    private String adminPassword;
}
