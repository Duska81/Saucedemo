package Tests;

import Page.LoginPage;
import Page.PriceTotalPage;
import Page.CartProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Data.SaucedemoProduct;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CartProductAndPrice {
    private WebDriver driver;

    @BeforeMethod
    public void pre() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @AfterMethod
    public void posle() {
        driver.quit();
    }

    @Test
    public void testCartAndPriceTotal() {
        PriceTotalPage priceTotalPage = new PriceTotalPage(driver);
        CartProductPage cartProductPage = new CartProductPage(driver);
        SoftAssert softAssert = new SoftAssert();

        List<String> expectedProducts = SaucedemoProduct.returnListProducts();
        List<Double> prices = SaucedemoProduct.returnListPrices();


        double stvarnaUkupnaCena = SaucedemoProduct.calculateTotalPrice();
        double tolerance = 0.00;
        double ocekivanaCena =57.98;


        softAssert.assertEquals(stvarnaUkupnaCena, ocekivanaCena , tolerance);

    }

    @Test
    public void proveraUkupneCeneUKorpi() {

        CartProductPage cartProductPage = new CartProductPage(driver);

        double cenaOnesie = 7.99;
        double cenaFleeceJacket = 49.99;


        cartProductPage.addProductInCartCart(driver, "Sauce Labs Onesie");
        cartProductPage.addProductInCartCart(driver, "Sauce Labs Fleece Jacket");


        cartProductPage.clickOnCart();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        cartProductPage.clickOnCheckoutButton();
        List<Double> prices = cartProductPage.getProductPricesInCart();


       cartProductPage.enterValidInformation("Dusanka","Mirkovic",21000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        cartProductPage.clickOnContinueButton();


        WebElement totalLabel = driver.findElement(By.className("summary_total_label"));
        String totalLabelText = totalLabel.getText();
        double ukupnaCena = Double.parseDouble(totalLabelText.split(": ")[1].replace("$", ""));

        double stvarnaUkupnaCena=cartProductPage.calculateTotalPrice();

        double ocekivanaUkupnaCena = cenaOnesie + cenaFleeceJacket;


        System.out.println("Stvarna ukupna cena: " + Math.round(stvarnaUkupnaCena * 100.0) / 100.0);
        System.out.println("Očekivana ukupna cena: " + Math.round(ocekivanaUkupnaCena * 100.0) / 100.0);


        if (Math.round(stvarnaUkupnaCena * 100.0) / 100.0 == Math.round(ocekivanaUkupnaCena * 100.0) / 100.0) {
            System.out.println("Ukupne cene su jednake.");
        } else {
            System.out.println("Ukupne cene nisu jednake.");
        }
        System.out.println(cartProductPage.getSummaryTotalLabelText());


    }
}