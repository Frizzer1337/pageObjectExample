package com.frizzer.pageobject.page;

import com.frizzer.pageobject.service.PageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SingleProductPage extends AbstractPage {
    @FindBy(className = "product-form__submit")
    WebElement addToCartButton;

    public SingleProductPage(WebDriver driver) {
        super(driver);
    }

    public SingleProductPage addToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-item")));
        return this;
    }




}
