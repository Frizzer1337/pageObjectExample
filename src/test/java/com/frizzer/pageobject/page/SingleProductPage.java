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

    @FindBy(className = "button--tertiary")
    WebElement deleteButton;

    public SingleProductPage(WebDriver driver) {
        super(driver);
    }

    public SingleProductPage addToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-item")));
        return this;
    }

    public SingleProductPage deleteFromCart(){
        wait.until(ExpectedConditions.elementToBeClickable(By.className("button--tertiary")));
        deleteButton.click();
        return this;
    }

    public String checkEmptyCart(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart__empty-text")));
        String cartText = driver.findElement(By.className("cart__empty-text")).getText();
        return cartText;
    }




}
