package org.dni9.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ConfigReader {

    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            log.error("Failed to load config properties");
            throw new RuntimeException(e);
        }

        // Override with local properties
        try (FileInputStream fis = new FileInputStream("src/test/resources/local.properties")) {
            Properties localProperties = new Properties();
            localProperties.load(fis);
            properties.putAll(localProperties); // override
        } catch (FileNotFoundException ex) {
            log.warn("local.properties file not found");
        } catch (IOException e) {
            log.error("Failed to load local properties");
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return getProperty(key, null);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless")) || Boolean.parseBoolean(properties.getProperty("headless"));
    }

    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicitWait", "10"));
    }

    public boolean shouldReconnectSession() {
        return "true".equals(getProperty("reconnectSession"));
    }

    public boolean shouldSaveSession() {
        return "true".equals(getProperty("saveSession"));
    }
}