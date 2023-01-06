package org.dni9.pom.base;

import io.restassured.http.Cookies;
import org.dni9.pom.factory.DriverManager;
import org.dni9.pom.utils.CookieUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

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
  public void startDriver(@Optional("CHROME") String browser) {
    String localBrowser = System.getProperty("browser", browser);
    setDriver(new DriverManager().initializeDriver(localBrowser));
  }

  @AfterMethod
  public void quitDriver() {
    getDriver().quit();
  }

  public void injectCookiesToBrowser(Cookies cookies) {
    List<Cookie> seleniumCookies = new CookieUtils().convertToSeleniumCookies(cookies);
    seleniumCookies.forEach(cookie -> getDriver().manage().addCookie(cookie));
  }
}
