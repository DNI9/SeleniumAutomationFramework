package org.dni9.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.dni9.pom.utils.ConfigLoader;
import org.dni9.pom.utils.EnvUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager implements DriverManager {

  public static final String DRIVER_VERSION = ConfigLoader.getInstance().getConfig("chrome.driver.version");

  @Override
  public WebDriver createDriver() {
    WebDriverManager.chromedriver().driverVersion(DRIVER_VERSION).setup();

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    if (EnvUtils.isCIServer()) {
      options.addArguments(
          "--headless",
          "--no-sandbox",
          "--disable-dev-shm-usage",
          "--window-size=1920,1080"
      );
    }

    WebDriver driver = new ChromeDriver(options);

    driver.manage().window().maximize();
    return driver;
  }
}
