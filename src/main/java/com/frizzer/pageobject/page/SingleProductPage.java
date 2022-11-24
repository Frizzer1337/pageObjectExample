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

public class SingleProductPage {

    private WebDriver driver;

    private PageService service = new PageService();
    private WebDriverWait wait;

    public SingleProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(className = "product-form__submit")
    WebElement addToCartButton;

    @FindBy(className = "header__icon--cart")
    WebElement cart;

    @FindBy(className = "cart-item")
    List<WebElement> cartItem;

    public WebElement findAddToCartButton(){
        return addToCartButton;
    }

    public SingleProductPage addToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-item")));
        return this;
    }

    public SingleProductPage openCart(){
        wait.until(ExpectedConditions.elementToBeClickable(cart));
        cart.click();
        return this;
    }



}
