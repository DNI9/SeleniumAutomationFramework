package org.dni9.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.dni9.pom.constants.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
  public WebDriver initializeDriver(String browser) {
    WebDriver driver;

    switch (BrowserType.valueOf(browser)) {
      case CHROME: {
//        driver = WebDriverManager.chromedriver().create();
        WebDriverManager.chromiumdriver().setup();
        driver = new ChromeDriver();
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
