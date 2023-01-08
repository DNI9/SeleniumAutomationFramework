package org.dni9.pom.utils;

public class EnvUtils {
  public static boolean isCIServer() {
    String CI = System.getProperty("CI", "false");
    return Boolean.parseBoolean(CI);
  }
}
