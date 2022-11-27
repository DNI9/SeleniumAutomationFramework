package org.dni9.pom.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
  public static Properties propertyLoader(String filePath) {
    Properties properties = new Properties();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      properties.load(reader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("File not found " + filePath);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to load properties from file: " + filePath);
    }

    return properties;
  }
}
