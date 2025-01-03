package org.dni9.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

  private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
  private static ExtentReports extent;

  public synchronized static void init() {
    if (extent == null) {
      ScreenshotUtils.createScreenshotDir();
      ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/test-output/ExtentReport.html");
      sparkReporter.config().setDocumentTitle("Modern Automation Framework");
      sparkReporter.config().setTheme(Theme.DARK);
      extent = new ExtentReports();
      extent.attachReporter(sparkReporter);
    }
  }

  public synchronized static void createTest(String testName, String description) {
    test.set(extent.createTest(testName, description));
  }

  public synchronized static void flush() {
    if (extent != null) {
      extent.flush();
    }
  }

  public static ExtentTest getTest() {
    return test.get();
  }
}
