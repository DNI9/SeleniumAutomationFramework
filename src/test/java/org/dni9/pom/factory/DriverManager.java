package org.dni9.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.dni9.pom.constants.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
  public WebDriver initializeDriver() {
    String browser = System.getProperty("browser", "CHROME");
    WebDriver driver;

    switch (BrowserType.valueOf(browser)) {
      case CHROME: {
        WebDriverManager.chromiumdriver().setup();
        driver = new ChromeDriver();
        break;
      }
      case FIREFOX: {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
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
