package Page;


import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;


public class PriceTotalPage {
    private WebDriver driver;

    public PriceTotalPage(WebDriver driver) {
        this.driver = driver;
        productPrices.add(7.99);
        productPrices.add(15.99);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private List<Double> productPrices = new ArrayList<>();

    }
