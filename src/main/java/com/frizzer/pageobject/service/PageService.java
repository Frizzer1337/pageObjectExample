package com.frizzer.pageobject.service;

import org.openqa.selenium.WebElement;

public class PageService {

    public Double priceToDouble(WebElement price){
        return Double.valueOf(price.getText().replaceAll("[^\\d.]", ""));
    }

    public boolean notBlank(WebElement element){
        return !element.getText().isBlank();
    }

}
