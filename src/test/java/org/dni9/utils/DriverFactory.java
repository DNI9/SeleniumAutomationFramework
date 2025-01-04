package org.dni9.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.List;

@Slf4j
public class DriverFactory {
  private static final String CHROME_DEBUG_PORT = "9222";

  private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
  private static final ConfigReader configReader = new ConfigReader();
  private final List<String> chromeArgs = List.of("--disable-gpu", "--ignore-certificate-errors", "--no-sandbox", "--disable-dev-shm-usage", "--disable-extensions", "--disable-popup-blocking", "--start-maximized", "--incognito", "--window-size=1920,1080", "--disable-infobars", "--disable-notifications");

  private DriverFactory() {
    // Private constructor to prevent instantiation
  }

  public static DriverFactory getInstance() {
    return InstanceHolder.instance;
  }

  public static boolean isExistingSessionActive() {
    String currentUrl = driver.get().getCurrentUrl();
    return currentUrl != null && !currentUrl.contains("data");
  }

  public WebDriver getDriver() {
    if (driver.get() == null) {
      driver.set(createDriver(configReader.getBrowser()));
    }
    return driver.get();
  }

  public void quitDriver() {
    if (driver.get() != null) {
      if (!configReader.shouldSaveSession()) {
        driver.get().quit();
      }
      driver.remove();
    }
  }

  private WebDriver createDriver(String browser) {
    WebDriver driver;
    switch (browser) {
      case "chrome" -> {
        try {
          if (configReader.shouldReconnectSession()) {
            driver = new ChromeDriver(getChromeOptions(true));
          } else {
            driver = new ChromeDriver(getChromeOptions(false));
          }
        } catch (SessionNotCreatedException ex) {
          log.warn("failed to reconnect to existing browser, opening new browser session");
          driver = new ChromeDriver(getChromeOptions(false));
        }
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

  private ChromeOptions getChromeOptions(boolean includeDebuggerAddress) {
    var chromeOptions = new ChromeOptions();
    if (configReader.isHeadless()) {
      chromeOptions.addArguments("--headless");
    }
    chromeOptions.addArguments(chromeArgs);
    if (configReader.shouldSaveSession()) {
      chromeOptions.addArguments("--remote-debugging-port=" + CHROME_DEBUG_PORT);
    }
    if (includeDebuggerAddress) {
      chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:" + CHROME_DEBUG_PORT);
    }
//    chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    return chromeOptions;
  }

  private static final class InstanceHolder {
    private static final DriverFactory instance = new DriverFactory();
  }
}
