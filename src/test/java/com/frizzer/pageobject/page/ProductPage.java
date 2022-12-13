package com.frizzer.pageobject.page;

import com.frizzer.pageobject.model.VaporessoProduct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends AbstractPage {

  @FindBy(className = "transcy-switcher-manual")
  WebElement currencySwitch;

  @FindBy(className = "full-unstyled-link")
  List<WebElement> productLinks;
  @FindBy(className = "header__icon--cart")
  WebElement cart;

  @FindBy(className = "cart-item")
  List<WebElement> cartItem;

  @FindBy(className = "button--tertiary")
  WebElement deleteButton;

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public ProductPage openCurrencySwitch() {
    wait.until(ExpectedConditions.elementToBeClickable(currencySwitch));
    Select select = new Select(currencySwitch.findElement(By.className("transcy-select__field")));
    return this;
  }


  public String getNameFirstItemInCart() {
    String itemName = driver.findElement(By.className("cart-item__name")).getText();
    return itemName;
  }

  public String checkEmptyCart() {
    String cartText = driver.findElement(By.className("cart__empty-text")).getText();
    return cartText;
  }

  public ProductPage openCart() {
    wait.until(ExpectedConditions.elementToBeClickable(cart));
    cart.click();
    return this;
  }

  public ProductPage deleteItem() {
    deleteButton.click();
    return this;
  }

  public int countItemsInCart() {
    return cartItem.size();
  }

  public List<WebElement> findProductLinks() {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("card__information")));
    return productLinks.stream().filter(x -> service.notBlank(x)).collect(Collectors.toList());
  }

  public List<VaporessoProduct> findVapes() {
    List<VaporessoProduct> vapeList = new ArrayList<>();
    for (var link : findProductLinks()) {
      String productLink = link.getAttribute("href");
      String name = link.getText();
      double currentPrice = service.priceToDouble(link.findElement(By.xpath("./.."))
          .findElement(By.xpath("./.."))
          .findElement(By.className("price-item--sale")));
      if (currentPrice == -1) {
        currentPrice = service.priceToDouble(link.findElement(By.xpath("./.."))
            .findElement(By.xpath("./.."))
            .findElement(By.className("price__regular")));
      }
      VaporessoProduct vape = new VaporessoProduct(currentPrice, name, productLink);
      vapeList.add(vape);

    }
    return vapeList;
  }

  public VaporessoProduct findVape() {
    WebElement link = findProductLinks().get(0);
    String productLink = link.getAttribute("href");
    String name = link.getText();
    double currentPrice = service.priceToDouble(link.findElement(By.xpath("./.."))
        .findElement(By.xpath("./.."))
        .findElement(By.className("price-item--sale")));
    if (currentPrice == -1) {
      currentPrice = service.priceToDouble(link.findElement(By.xpath("./.."))
          .findElement(By.xpath("./.."))
          .findElement(By.className("price__regular")));
    }
    return new VaporessoProduct(currentPrice, name, productLink);


  }


}
