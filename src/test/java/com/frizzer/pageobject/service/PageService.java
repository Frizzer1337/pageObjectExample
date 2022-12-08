package com.frizzer.pageobject.service;

import org.openqa.selenium.WebElement;

public class PageService {

  public double priceToDouble(WebElement price) {
    String priceText = !price.getText().replaceAll("[^\\d.]", "").isBlank() ?
        price.getText().replaceAll("[^\\d.]", "") : "-1";
    return Double.parseDouble(priceText);
  }

  public boolean notBlank(WebElement element) {
    return !element.getText().isBlank();
  }

}
