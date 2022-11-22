package com.frizzer.pageobject.page;

import com.frizzer.pageobject.service.PageService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.frizzer.pageobject.constant.PageNaming.PRODUCT_PAGE;

public class ProductPage {

    private WebDriver driver;
    private PageService service = new PageService();

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        if (!PRODUCT_PAGE.equals(driver.getTitle())) {
            throw new IllegalStateException("This is not product page");
        }
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "price-item--sale")
    @CacheLookup
    List<WebElement> salePrices;

    @FindBy(className = "price-item--regular")
    @CacheLookup
    List<WebElement> regularPrices;

    public List<WebElement> findSalePrices(){
        return salePrices;
    }

    public List<WebElement> findRegularPrices(){
        return regularPrices;
    }

    public List<Double> findSalePricesDouble(){
        List<Double> salePricesDouble = new ArrayList<>();
        Double emptyElementPrice = -1.0;
        for (var price : salePrices){
            if(service.notBlank(price)){
                salePricesDouble.add(service.priceToDouble(price));
            } else {
                salePricesDouble.add(emptyElementPrice);
            }
        }
        return salePricesDouble;
    }

    public List<Double> findRegularPricesDouble(){
        List<Double> regularPricesDouble = new ArrayList<>();
        for (var price : regularPrices){
            if(service.notBlank(price)){
                regularPricesDouble.add(service.priceToDouble(price));
            }
        }
        return regularPricesDouble;
    }


}
