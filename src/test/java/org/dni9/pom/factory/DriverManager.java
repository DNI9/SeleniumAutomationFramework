package org.dni9.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.dni9.pom.constants.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
  public WebDriver initializeDriver(String browser) {
    String defaultBrowser = browser.isBlank() ? "CHROME" : browser;
    String browserArg = System.getProperty("browser", defaultBrowser);
    WebDriver driver;

    switch (BrowserType.valueOf(browserArg)) {
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
        throw new IllegalStateException("Invalid browser name: " + browserArg);
      }
    }

    driver.manage().window().maximize();
    return driver;
  }
}
