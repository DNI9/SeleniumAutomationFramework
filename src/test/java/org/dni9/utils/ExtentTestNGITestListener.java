package org.dni9.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGITestListener implements ITestListener {

  @Override
  public void onStart(ITestContext context) {
    ExtentManager.init();
  }

  @Override
  public void onFinish(ITestContext context) {
    ExtentManager.flush();
  }

  @Override
  public void onTestStart(ITestResult result) {
    ExtentManager.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    String path = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName() + "_passed");
    ExtentManager.getTest().pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
  }

  @Override
  public void onTestFailure(ITestResult result) {
    String path = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName() + "_failed");
    ExtentManager.getTest().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    String path = ScreenshotUtils.captureScreenshot(result.getMethod().getMethodName() + "_skipped");
    ExtentManager.getTest().skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
  }
}