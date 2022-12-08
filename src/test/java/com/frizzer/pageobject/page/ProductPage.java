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

public class ProductPage extends AbstractPage {

  @FindBy(className = "full-unstyled-link")
  List<WebElement> productLinks;
  @FindBy(className = "header__icon--cart")
  WebElement cart;

  @FindBy(className = "cart-item")
  List<WebElement> cartItem;

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public String getNameFirstItemInCart(){
    String itemName = driver.findElement(By.className("cart-item__name")).getText();
    return itemName;
  }

  public ProductPage openCart() {
    wait.until(ExpectedConditions.elementToBeClickable(cart));
    cart.click();
    return this;
  }

  public int countItemsInCart() {
    return cartItem.size();
  }

  public List<WebElement> findProductLinks() {
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


}
