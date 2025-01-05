package org.dni9.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ScreenshotUtils {

  public static String captureScreenshot(String msg) {
    File screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String screenshotPath = "target/test-output/screenshots/" + msg.toLowerCase().replaceAll(" ", "-") + "_" + timestamp + ".png";
    File screenshotDestination = new File(screenshotPath);
    try {
      Files.copy(screenshot.toPath(), screenshotDestination.toPath());
    } catch (IOException e) {
      log.error("Error while taking screenshot", e);
    }
    return screenshotPath.replace("target/test-output/", "");
  }

  public static void createScreenshotDir() {
    File ssDir = new File(System.getProperty("user.dir") + "/target/test-output/screenshots");
    if (!ssDir.exists()) {
      ssDir.mkdirs();
    }
  }
}
