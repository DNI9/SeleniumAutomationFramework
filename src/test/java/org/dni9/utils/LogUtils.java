package org.dni9.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtils {

  public static void infoPass(String msg) {
    log.info(msg);
    ExtentManager.getTest().pass(msg);
  }

  public static void infoPassWithScreenshot(String msg) {
    log.info(msg);
    String path = ScreenshotUtils.captureScreenshot(msg);
    ExtentManager.getTest().pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
  }
}
