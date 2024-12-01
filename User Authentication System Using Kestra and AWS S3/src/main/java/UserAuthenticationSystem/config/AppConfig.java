package com.auth.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }

    public static String getKestraApiUrl() {
        return properties.getProperty("kestra.api.url", "http://localhost:8080");
    }

    public static String getSmtpHost() {
        return properties.getProperty("smtp.host");
    }

    public static String getSmtpPort() {
        return properties.getProperty("smtp.port");
    }

    public static String getS3Bucket() {
        return properties.getProperty("aws.s3.bucket");
    }

    public static String getS3Region() {
        return properties.getProperty("aws.s3.region");
    }
}
