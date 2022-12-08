package com.frizzer.pageobject.service;

import org.openqa.selenium.WebElement;

public class PageService {

    public Double priceToDouble(WebElement price){
        return notBlank(price) ? Double.parseDouble(price.getText().replaceAll("[^\\d.]", "")) : -1;
    }

    public boolean notBlank(WebElement element){
        return element!=null && !element.getText().isEmpty();
    }

}
