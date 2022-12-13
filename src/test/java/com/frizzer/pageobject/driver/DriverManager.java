package com.frizzer.pageobject.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

  private static WebDriver webDriver;
  private static AtomicBoolean isCreated = new AtomicBoolean(false);
  private static Logger logger = LogManager.getLogger();
  private static final String DRIVER_PROPERTIES = "src/test/resources/driver.properties";

  private DriverManager() {
  }

  public static WebDriver getDriver() throws MalformedURLException {
    Properties properties = new Properties();
    try (FileInputStream inputStream = new FileInputStream(DRIVER_PROPERTIES)) {
      properties.load(inputStream);
    } catch (IOException e) {
      logger.error("Error while reading driver properties");
      throw new RuntimeException(e);
    }
    while (webDriver == null) {
      if (isCreated.compareAndSet(false, true)) {
        switch (properties.getProperty("browser")) {
          case "firefox": {
            FirefoxOptions capabilities = new FirefoxOptions();
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
            break;
          }
          default: {
            webDriver = new ChromeDriver();
            WebDriverManager.chromedriver().setup();
            break;
          }
        }
        logger.info("WebDriver has started");
      }
    }

    return webDriver;
  }

  public static void closeDriver(WebDriver driver) {
//    driver.quit();
//    driver = null;
  }
}
