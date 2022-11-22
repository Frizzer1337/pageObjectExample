import com.frizzer.pageobject.page.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTest {

    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setupBrowser(){
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testSortAscendingByPrice(){
        driver.get("https://store.vaporesso.com/collections/collections?sort_by=price-ascending");
        ProductPage productPage = new ProductPage(driver);
        List<Double> regularPrices = productPage.findRegularPricesDouble();
        List<Double> salePrices = productPage.findSalePricesDouble();

        List<Double> pricesCombined = new ArrayList<>();

        for(int i = 0; i < regularPrices.size(); i++){
            if(salePrices.get(i) != -1){
                pricesCombined.add(salePrices.get(i));
            } else {
                pricesCombined.add(regularPrices.get(i));
            }
        }
        Assert.assertEquals(pricesCombined,pricesCombined.stream().sorted().collect(Collectors.toList()));
    }


    @AfterMethod(alwaysRun = true)
    public void quitBrowser(){
        driver.quit();
        driver = null;
    }

}
