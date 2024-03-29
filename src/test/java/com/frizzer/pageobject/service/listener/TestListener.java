package com.frizzer.pageobject.service.listener;

import com.frizzer.pageobject.driver.DriverManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

  private Logger log = LogManager.getRootLogger();

  @Override
  public void onTestStart(ITestResult result) {
    log.info(result.getTestClass().getName() + " started");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    log.warn("Test " + result.getTestClass().getName() + " failed");
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    String methodName = result.getName();
    if (!result.isSuccess()) {
      File scrFile = null;
      try {
        scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
      } catch (MalformedURLException e) {
        throw new RuntimeException(e);
      }
      try {
        String reportDirectory = "./src/test/resources/";
        File destFile = new File(
            (String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(
                calendar.getTime()) + ".png");
        FileUtils.copyFile(scrFile, destFile);
        Reporter.log(
            "<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
                + "' height='100' width='100'/> </a>");
      } catch (IOException e) {
        log.error(e);
      }
    }

  }
}
