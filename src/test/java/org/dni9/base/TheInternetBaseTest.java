package org.dni9.base;

import lombok.extern.slf4j.Slf4j;
import org.dni9.pages.theinternet.LoginPage;
import org.dni9.pages.theinternet.SecurePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class TheInternetBaseTest extends BaseTest {

  protected static final String APP_NAME = "theinternet";

  protected LoginPage loginPage;
  protected SecurePage securePage;

  @Override
  public String getAppName() {
    return APP_NAME;
  }

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
