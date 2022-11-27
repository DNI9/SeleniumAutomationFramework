package org.dni9.pom.utils;

import java.util.Objects;
import java.util.Properties;

public class ConfigLoader {
  private static ConfigLoader configLoader;
  private final Properties properties;

  private ConfigLoader() {
    properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
  }

  public static ConfigLoader getInstance() {
    if (Objects.isNull(configLoader)) configLoader = new ConfigLoader();
    return configLoader;
  }

  public String getConfig(String key) {
    String prop = properties.getProperty(key);
    if (prop.isBlank()) throw new RuntimeException("Config with key: " + key + " is not defined");
    return prop;
  }
}
