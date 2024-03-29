package org.dni9.pom.base;

import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.dni9.pom.constants.DriverType;
import org.dni9.pom.factory.DriverManager;
import org.dni9.pom.factory.DriverManagerFactory;
import org.dni9.pom.utils.CookieUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
  private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  protected WebDriver getDriver() {
    return driver.get();
  }

  private void setDriver(WebDriver driver) {
    this.driver.set(driver);
  }

  @BeforeMethod(description = "Start driver")
  @Step("Start {browser} driver")
  @Parameters("browser")
  public synchronized void startDriver(@Optional("CHROME") String browser) {
    String localBrowser = System.getProperty("browser", browser);
    DriverManager driverManager = DriverManagerFactory.getManager(DriverType.valueOf(localBrowser));
    setDriver(driverManager.createDriver());
  }

  // NOTE: by using `synchronized` keyword, other thread have to wait before one thread finishes using this method
  // basically making this run sequentially.
  @AfterMethod(description = "Quit driver")
  @Step("Quit {browser} driver")
  @Parameters("browser")
  public synchronized void quitDriver(@Optional("CHROME") String browser, ITestResult result) throws IOException {
    if (result.getStatus() == ITestResult.FAILURE) {
      String filePath = "screenshots" + File.separator + browser + File.separator
          + result.getTestClass().getRealClass().getSimpleName() + "_"
          + result.getMethod().getMethodName() + ".png";
      File destFile = new File(filePath);

      takeScreenshot(destFile);
    }

    getDriver().quit();
  }

  public void injectCookiesToBrowser(Cookies cookies) {
    List<Cookie> seleniumCookies = new CookieUtils().convertToSeleniumCookies(cookies);
    seleniumCookies.forEach(cookie -> getDriver().manage().addCookie(cookie));
  }

  private void takeScreenshot(File destFile) throws IOException {
    File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(srcFile, destFile);
  }

  private void takeFullScreenshot(File destfile) throws IOException {
    Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(getDriver());
    ImageIO.write(screenshot.getImage(), "PNG", destfile);
  }
}
