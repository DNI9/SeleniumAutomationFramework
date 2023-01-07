package org.dni9.pom.factory;

import org.dni9.pom.constants.DriverType;

public class DriverManagerFactory {
  public static DriverManager getManager(DriverType driverType) {
    switch (driverType) {
      case CHROME: {
        return new ChromeDriverManager();
      }
      case FIREFOX: {
        return new FirefoxDriverManager();
      }
      default: {
        throw new IllegalStateException("Invalid driver type: " + driverType);
      }
    }
  }
}
