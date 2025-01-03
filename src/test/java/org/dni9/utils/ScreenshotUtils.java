package org.dni9.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

  public static String captureScreenshot(String screenshotName) {
    String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    TakesScreenshot ts = (TakesScreenshot) DriverFactory.getInstance().getDriver();
    File source = ts.getScreenshotAs(OutputType.FILE);
    String destination = System.getProperty("user.dir") + "/target/test-output/screenshots/" + screenshotName + dateName + ".png";
    File finalDestination = new File(destination);
    try {
      FileHandler.copy(source, finalDestination);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return destination;
  }

  public static void createScreenshotDir() {
    File ssDir = new File(System.getProperty("user.dir") + "/target/test-output/screenshots");
    if (!ssDir.exists()) {
      ssDir.mkdirs();
    }
  }
}
