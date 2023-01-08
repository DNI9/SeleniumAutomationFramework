package org.dni9.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.dni9.pom.utils.EnvUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements DriverManager {

  @Override
  public WebDriver createDriver() {
    WebDriverManager.firefoxdriver().setup();
    
    FirefoxOptions options = new FirefoxOptions();
    if (EnvUtils.isCIServer()) {
      options.addArguments("-headless");
    }

    WebDriver driver = new FirefoxDriver(options);
    driver.manage().window().maximize();
    return driver;
  }
}
