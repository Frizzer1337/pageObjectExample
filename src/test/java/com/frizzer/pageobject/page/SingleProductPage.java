package com.frizzer.pageobject.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SingleProductPage extends AbstractPage {

  @FindBy(className = "product-form__submit")
  WebElement addToCartButton;

  @FindBy(className = "button--tertiary")
  WebElement deleteButton;

  @FindBy(className = "quantity__input")
  WebElement quantityInput;

  public SingleProductPage(WebDriver driver) {
    super(driver);
  }

  public SingleProductPage addToCart() {
    wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
    addToCartButton.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-item")));
    return this;
  }

  public SingleProductPage addToCart(int amount) {
    wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
    quantityInput.clear();
    quantityInput.sendKeys(amount + "");
    addToCartButton.click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-item")));
    return this;
  }

  public SingleProductPage deleteFromCart() {
    wait.until(ExpectedConditions.elementToBeClickable(By.className("button--tertiary")));
    deleteButton.click();
    return this;
  }

  public String quantityOfFirstInCart(){
      wait.until(ExpectedConditions.presenceOfElementLocated(By.className("quantity__input")));
      String cartText = driver.findElement(By.className("quantity__input")).getAttribute("value");
      return cartText;
  }

  public String checkEmptyCart() {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart__empty-text")));
    String cartText = driver.findElement(By.className("cart__empty-text")).getText();
    return cartText;
  }


}
