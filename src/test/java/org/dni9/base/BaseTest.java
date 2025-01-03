package org.dni9.base;

import lombok.extern.slf4j.Slf4j;
import org.dni9.data.TestData;
import org.dni9.utils.ConfigReader;
import org.dni9.utils.DriverFactory;
import org.dni9.utils.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public abstract class BaseTest {

  protected static final ConfigReader configReader = new ConfigReader();

  protected abstract void initializePageObjects(WebDriver driver);

  protected abstract void openUrl();

  protected abstract String getAppName();

  @BeforeMethod
  public void setup() {
    openUrl();
    initializePageObjects(getDriver());
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    log.info("Quiting browser");
    DriverFactory.getInstance().quitDriver();
  }

  protected WebDriver getDriver() {
    return DriverFactory.getInstance().getDriver();
  }

  protected <T extends TestData> T getData(String fileName, Class<T> clazz) {
    return JsonUtils.readJson("%s/%s".formatted(getAppName(), fileName), clazz);
  }
}
