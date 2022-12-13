package com.frizzer.pageobject.test;

import com.frizzer.pageobject.driver.DriverManager;
import com.frizzer.pageobject.service.listener.TestListener;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonTest {

  protected WebDriver driver;

  @BeforeClass
  public void setupBrowser() throws MalformedURLException {
    this.driver = DriverManager.getDriver();
//    ChromeOptions capabilities = new ChromeOptions();
//    this.driver = new RemoteWebDriver(new URL("http://localhost:4444/"),capabilities);
  }

  @AfterSuite(alwaysRun = true)
  public void quitBrowser() {
    DriverManager.closeDriver(driver);
  }


}
