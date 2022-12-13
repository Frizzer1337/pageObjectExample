package com.frizzer.pageobject.page;

import com.frizzer.pageobject.service.PageService;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

  protected WebDriver driver;
  protected PageService service = new PageService();
  protected WebDriverWait wait;

  protected AbstractPage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    PageFactory.initElements(driver, this);
  }

}
