package org.dni9.tests.common;

import org.dni9.pages.LoginPage;
import org.dni9.pages.SecurePage;
import org.dni9.tests.utils.ConfigReader;
import org.dni9.tests.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  private static final ConfigReader configReader = new ConfigReader();

  protected LoginPage loginPage;
  protected SecurePage securePage;

  @BeforeMethod
  public void setup() {
    var driver = DriverFactory.getInstance().getDriver();
    driver.get(configReader.getBaseUrl());
    initializePageObjects(driver);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    DriverFactory.getInstance().quitDriver();
  }

  // Initialize common page objects
  private void initializePageObjects(WebDriver driver) {
    loginPage = PageFactory.initElements(driver, LoginPage.class);
    securePage = PageFactory.initElements(driver, SecurePage.class);
  }
}
