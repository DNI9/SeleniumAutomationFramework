package org.dni9.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  private static final ConfigReader configReader = new ConfigReader();

  private DriverFactory() {
    // Private constructor to prevent instantiation
  }

  public static DriverFactory getInstance() {
    return InstanceHolder.instance;
  }

  public WebDriver getDriver() {
    if (driver.get() == null) {
      driver.set(createDriver(configReader.getBrowser()));
    }
    return driver.get();
  }

  public void quitDriver() {
    if (driver.get() != null) {
      if (configReader.getProperty("closeBrowserOnFinish", "true").equals("true")) {
        driver.get().quit();
      }
      driver.remove();
    }
  }

  private WebDriver createDriver(String browser) {
    WebDriver driver;
    switch (browser) {
      case "chrome" -> {
        var chromeOptions = new ChromeOptions();
        if (configReader.isHeadless()) {
          chromeOptions.addArguments("--headless");
        }
        chromeOptions.addArguments(
            "--disable-gpu",
            "--ignore-certificate-errors",
            "--no-sandbox",
            "--disable-extensions",
            "--disable-popup-blocking",
            "--start-maximized",
            "--incognito",
            "--window-size=1920,1080",
            "--disable-infobars",
            "--disable-notifications"
        );
        driver = new ChromeDriver(chromeOptions);
      }
      case "firefox" -> {
        var firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        firefoxOptions.addPreference("dom.disable_open_during_load", false);
        firefoxOptions.addPreference("browser.privatebrowsing.autostart", true);
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");
        driver = new FirefoxDriver(firefoxOptions);
      }
      default -> throw new IllegalArgumentException("Unexpected browser " + browser);
    }

    // Provide Common options to all browsers
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configReader.getImplicitWait()));

    return driver;
  }

  private static final class InstanceHolder {
    private static final DriverFactory instance = new DriverFactory();
  }
}
