package com.frizzer.pageobject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommunityPage extends AbstractPage {

  @FindBy(tagName = "input")
  WebElement searchInput;

  public CommunityPage(WebDriver driver) {
    super(driver);
  }

  public CommunityPage searchText(String text) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("input")));
    searchInput.sendKeys(text);
    return this;
  }
}
