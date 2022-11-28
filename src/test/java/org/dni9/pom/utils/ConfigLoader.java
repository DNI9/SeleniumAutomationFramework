package org.dni9.pom.utils;

import java.util.Objects;
import java.util.Properties;

import org.dni9.pom.constants.EnvType;

public class ConfigLoader {
  private static ConfigLoader configLoader;
  private final Properties properties;

  private ConfigLoader() {
    String env = System.getProperty("env", EnvType.STAGING.toString());
    String configFile = "";

    switch (EnvType.valueOf(env)) {
      case STAGING:
        configFile = "stg_config.properties";
        break;

      case PRODUCTION:
        configFile = "prod_config.properties";
        break;

      default:
        throw new IllegalArgumentException("Config file for " + env + " not found");
    }

    System.out.println("INFO: Using config file for " + EnvType.valueOf(env) + " environment");
    properties = PropertyUtils.propertyLoader("src/test/resources/" + configFile);
  }

  public static ConfigLoader getInstance() {
    if (Objects.isNull(configLoader))
      configLoader = new ConfigLoader();
    return configLoader;
  }

  public String getConfig(String key) {
    String prop = properties.getProperty(key);
    if (prop.isBlank())
      throw new RuntimeException("Config with key: " + key + " is not defined");
    return prop;
  }
}
