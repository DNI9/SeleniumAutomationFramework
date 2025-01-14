package org.dni9.base;

import lombok.extern.slf4j.Slf4j;
import org.dni9.data.TestData;
import org.dni9.utils.ConfigReader;
import org.dni9.utils.DriverFactory;
import org.dni9.utils.ExtentTestNGITestListener;
import org.dni9.utils.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Slf4j
@Listeners(ExtentTestNGITestListener.class)
public abstract class BaseTest {

  protected static final ConfigReader configReader = new ConfigReader();

  protected abstract void initializePageObjects(WebDriver driver);

  protected abstract String getAppName();

  @BeforeMethod
  public synchronized void setup() {
    WebDriver driver = getDriver();
    if (!DriverFactory.isExistingSessionActive()) {
      openUrl(driver);
    }
    initializePageObjects(getDriver());
  }

  @AfterMethod(alwaysRun = true)
  public synchronized void tearDown() {
    log.info("Quiting browser");
    DriverFactory.getInstance().quitDriver();
  }

  protected WebDriver getDriver() {
    return DriverFactory.getInstance().getDriver();
  }

  protected <T extends TestData> T getData(String fileName, Class<T> clazz) {
    return JsonUtils.readJson("%s/%s".formatted(getAppName(), fileName), clazz);
  }

  private void openUrl(WebDriver driver) {
    String baseUrl = switch (getAppName()) {
      case "theinternet" -> configReader.getProperty("theInternetUrl");
      case "bookstore" -> configReader.getProperty("bookstoreUrl");
      default -> throw new IllegalArgumentException("App url is required");
    };
    log.info("Opening {}", baseUrl);
    driver.get(baseUrl);
  }
}
