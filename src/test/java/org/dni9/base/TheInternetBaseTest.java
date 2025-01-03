package org.dni9.base;

import lombok.extern.slf4j.Slf4j;
import org.dni9.pages.LoginPage;
import org.dni9.pages.SecurePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class TheInternetBaseTest extends BaseTest {

  protected LoginPage loginPage;
  protected SecurePage securePage;

  @Override
  protected void initializePageObjects(WebDriver driver) {
    loginPage = PageFactory.initElements(driver, LoginPage.class);
    securePage = PageFactory.initElements(driver, SecurePage.class);
  }

  @Override
  protected void openUrl() {
    String baseUrl = configReader.getProperty("theInternetUrl");
    log.info("Opening {}", baseUrl);
    getDriver().get(baseUrl);
  }
}
