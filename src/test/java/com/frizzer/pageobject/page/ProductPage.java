package com.frizzer.pageobject.page;

import com.frizzer.pageobject.constant.PageNaming;
import com.frizzer.pageobject.model.VaporessoProduct;
import com.frizzer.pageobject.service.PageService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

  private WebDriver driver;
  private PageService service = new PageService();
  private WebDriverWait wait;

  public ProductPage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    PageFactory.initElements(driver, this);
  }

  @FindBy(className = "price-item--sale")
  List<WebElement> salePrices;

  @FindBy(className = "price-item--regular")
  List<WebElement> regularPrices;

  @FindBy(className = "card__information")
  List<WebElement> productInfo;

  @FindBy(className = "full-unstyled-link")
  List<WebElement> productLinks;

  @FindBy(className = "prompt-wrapper")
  WebElement promptButton;

  @FindBy(className = "soundest-form-wof-close")
  WebElement circlePromptButton;

  @FindBy(className = "header__icon--cart")
  WebElement cart;

  @FindBy(className = "cart-item")
  List<WebElement> cartItem;

  public WebElement findPromptButton() {
    return promptButton.findElement(By.tagName("button"));
  }

  public WebElement findCirclePromptButton() {
    return circlePromptButton;
  }

  public ProductPage openSingleProductPage(WebElement productLink) {
    wait.until(ExpectedConditions.elementToBeClickable(productLink));
    productLink.click();
    return this;
  }

  public ProductPage openCart() {
    wait.until(ExpectedConditions.elementToBeClickable(cart));
    cart.click();
    return this;
  }

  public int countItemsInCart() {
    return cartItem.size();
  }

  public List<WebElement> findSalePrices() {
    return salePrices;
  }

  public List<WebElement> findRegularPrices() {
    return regularPrices;
  }

  public List<WebElement> findProductLinks() {
    return productLinks.stream().filter(x -> service.notBlank(x)).collect(Collectors.toList());
  }

  public List<Double> findSalePricesDouble() {
    List<Double> salePricesDouble = new ArrayList<>();
    Double emptyElementPrice = -1.0;
    for (var price : salePrices) {
      if (service.notBlank(price)) {
        salePricesDouble.add(service.priceToDouble(price));
      } else {
        salePricesDouble.add(emptyElementPrice);
      }
    }
    return salePricesDouble;
  }

  public List<Double> findRegularPricesDouble() {
    List<Double> regularPricesDouble = new ArrayList<>();
    for (var price : regularPrices) {
      if (service.notBlank(price)) {
        regularPricesDouble.add(service.priceToDouble(price));
      }
    }
    return regularPricesDouble;
  }

  public List<VaporessoProduct> findVapes() {
    List<VaporessoProduct> vapeList = new ArrayList<>();
    for (var link : findProductLinks()) {
      String productLink = link.getAttribute("href");
      String name = link.getText();
      double salePrice = service.priceToDouble(link.findElement(By.xpath("./.."))
          .findElement(By.xpath("./.."))
          .findElement(By.className("price-item--sale")));
      double regularPrice = service.priceToDouble(link.findElement(By.xpath("./.."))
          .findElement(By.xpath("./.."))
          .findElement(By.className("price__sale"))
          .findElement(By.className("price-item--regular")));
      double standardPrice = service.priceToDouble(link.findElement(By.xpath("./.."))
          .findElement(By.xpath("./.."))
          .findElement(By.className("price__regular")));
      regularPrice = regularPrice < 0 ? standardPrice : regularPrice;
      VaporessoProduct vape = new VaporessoProduct(regularPrice,salePrice,name,productLink);
      vapeList.add(vape);

    }
    return vapeList;
  }


}
