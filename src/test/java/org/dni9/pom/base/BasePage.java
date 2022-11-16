package org.dni9.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
  public static final String BASE_URL = "https://askomdch.com";
  protected final WebDriver driver;
  protected final WebDriverWait waitLong;
  protected final WebDriverWait waitShort;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    waitLong = new WebDriverWait(driver, Duration.ofSeconds(15));
    waitShort = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  public void load(String endpoint) {
    driver.get(BASE_URL + endpoint);
  }

  public void waitForElementsToDisappear(By locator, int waitLength) {
    List<WebElement> elements = driver.findElements(locator);

    if (elements.size() > 0) {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitLength));
      wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    } else {
      System.err.println("No element(s) found to to wait for. " + locator.toString());
    }
  }

  // TODO: remove this
  public BasePage sleep(int length) {
    try {
      Thread.sleep(Duration.ofSeconds(length).toMillis());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return this;
  }
}
