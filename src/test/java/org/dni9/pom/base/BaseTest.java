package org.dni9.pom.base;

import org.dni9.pom.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
  private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  protected WebDriver getDriver() {
    return driver.get();
  }

  private void setDriver(WebDriver driver) {
    this.driver.set(driver);
  }

  @BeforeMethod
  @Parameters("browser")
  public void startDriver(String browser) {
    String localBrowser = System.getProperty("browser", browser);
    setDriver(new DriverManager().initializeDriver(localBrowser));
  }

  @AfterMethod
  public void quitDriver() {
    getDriver().quit();
  }

}
