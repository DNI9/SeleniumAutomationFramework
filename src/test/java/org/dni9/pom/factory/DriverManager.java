package org.dni9.pom.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
  public WebDriver initializeDriver() {
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // TODO: remove implicit wait
    return driver;
  }
}
