package org.dni9.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
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
    return Boolean.parseBoolean(properties.getProperty("headless"));
  }

  public int getImplicitWait() {
    return Integer.parseInt(getProperty("implicitWait", "10"));
  }
}