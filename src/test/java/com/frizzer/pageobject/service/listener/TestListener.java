package com.frizzer.pageobject.service.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

  private Logger log = LogManager.getRootLogger();

  @Override
  public void onTestFailure(ITestResult result) {
      log.warn("Test " + result.getTestClass().getName() + " failed");
  }
}
