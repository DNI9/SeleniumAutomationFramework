package org.dni9.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.dni9.pom.constants.BrowserType;
import org.openqa.selenium.WebDriver;

public class DriverManager {
  public WebDriver initializeDriver(String browser) {
    WebDriver driver;

    switch (BrowserType.valueOf(browser)) {
      case CHROME: {
//        WebDriverManager.chromiumdriver().setup();
        driver = WebDriverManager.chromedriver().create();
        break;
      }
      case FIREFOX: {
//        WebDriverManager.firefoxdriver().setup();
        driver = WebDriverManager.firefoxdriver().create();
        break;
      }
      default: {
        throw new IllegalStateException("Invalid browser name: " + browser);
      }
    }

    driver.manage().window().maximize();
    return driver;
  }
}
