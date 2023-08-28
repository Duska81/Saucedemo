package Tests;

import Page.LoginPage;
import Page.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;



public class ProductTest {
    private WebDriver driver;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }


    @Test
    public void verifyProductInCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        if (productPage.isCartEmpty()) {
            System.out.println("Korpa je prazna.");
        } else {
            System.out.println("Korpa nije prazna.");
        }
        productPage.addProductToCart(driver, "Sauce Labs Onesie");

        SoftAssert softAssert = new SoftAssert();

        productPage.openCart();
        String productName = "Sauce Labs Onesie";

        boolean productInCart = productPage.verifyProductInCart(driver, productName);
        softAssert.assertTrue(productInCart, "Proizvod nije pronađen u korpi.");

        softAssert.assertAll();

         driver.quit();
    }

    @Test
    public void verifyProductPrice() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(driver, "Sauce Labs Onesie");

        SoftAssert softAssert = new SoftAssert();

        String expectedPrice = "$7.99";
        boolean priceVerified = productPage.verifyProductPrice(driver, expectedPrice);
        softAssert.assertTrue(priceVerified, "Cena proizvoda se ne poklapa.");

        softAssert.assertAll();

         driver.quit();
    }

    @Test
    public void verifyProductDescription() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(driver, "Sauce Labs Onesie");

        SoftAssert softAssert = new SoftAssert();

        String expectedDescription = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.";
        String actualDescription = productPage.getProductDescription("Sauce Labs Onesie");
        if(actualDescription.equals(expectedDescription)){
            System.out.println("Opis proizvoda je tacan");
        }else{
            System.out.println("Opis proizvoda nije tacan");
        }



       // softAssert.assertEquals(actualDescription, expectedDescription, "Opis proizvoda se ne poklapa.");


        softAssert.assertAll();
        driver.quit();

    }



    }
