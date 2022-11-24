import com.frizzer.pageobject.page.ProductPage;
import com.frizzer.pageobject.page.SingleProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CartTest {

    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setupBrowser() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testSortAscendingByPrice(){
        driver.get("https://store.vaporesso.com/collections/collections");
        for (int i = 0; i < 5; i++) {
            ProductPage productPage = new ProductPage(driver);
            List<WebElement> productLinks = productPage.findProductLinks();
            WebElement productLink = productLinks.get(i);
            productPage.openSingleProductPage(productLink);
            SingleProductPage singleProductPage = new SingleProductPage(driver).addToCart();
            driver.get("https://store.vaporesso.com/collections/collections");
        }
        driver.get("https://store.vaporesso.com/collections/collections");
        ProductPage productPage = new ProductPage(driver).openCart();
        Assert.assertEquals(productPage.countItemsInCart(),5);

    }


    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
//        driver.quit();
//        driver = null;
    }


}
