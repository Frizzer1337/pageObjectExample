package com.frizzer.pageobject.page;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends AbstractPage {

  @FindBy(tagName = "form")
  WebElement search;

  public SearchPage(WebDriver driver) {
    super(driver);
  }

  public SearchPage searchText(String text) {
    driver.get("https://store.vaporesso.com/search?q=" + text + "&options%5Bprefix%5D=last");
    return this;
  }

  public List<String> findVapeNames() {
    return driver.findElements(By.className("full-unstyled-link")).stream()
        .map(x -> x.getText().toLowerCase())
        .filter(x -> !x.isBlank())
        .collect(
            Collectors.toList());
  }

}
