package Tests;

import Page.CheckoutPage;
import Page.LoginPage;
import Page.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CheckoutTest {
    private WebDriver driver;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void verifyCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Integer beforeAdd = productPage.returnItemNumber(driver);
        SoftAssert softAssert = new SoftAssert();


        checkoutPage.AddProductInCart(driver, "Sauce Labs Onesie");
        softAssert.assertTrue(true, "incorrect item");


        checkoutPage.AddProductInCart(driver, "Sauce Labs Fleece Jacket");
        Integer afterAdd = productPage.returnItemNumber(driver);

        checkoutPage.clickOnCart();


        if (afterAdd == beforeAdd + 2) {
            System.out.println("Test passed, number of items in cart are correct");

        } else {
            System.out.println("Test failed, this is incorrect number items in cart");
        }

        checkoutPage.clickOnCart();

        driver.quit();
    }

    @Test
    public void verifyloginWithValidInformations() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        SoftAssert softAssert = new SoftAssert();
        loginPage.login("standard_user", "secret_sauce");

        checkoutPage.AddProductInCart(driver, "Sauce Labs Onesie");
        checkoutPage.AddProductInCart(driver, "Sauce Labs Fleece Jacket");
        checkoutPage.clickOnCart();

        checkoutPage.clickOnCheckoutButton();


        checkoutPage.enterValidInformation("Dusanka", "Mirkovic", 21000);
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("https://www.saucedemo.com/checkout-step-two.html")) {
            System.out.println("hooray, order is almost finished");
        } else {
            System.out.println("This is not good");
            checkoutPage.clickOnCheckoutButton();



            softAssert.assertEquals(false, false, "Test is failed, we manage to order without basic information");
        }
        driver.quit();
    }

    @Test
    public void verifyBlankFirstNameAndLastNameField() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        SoftAssert softAssert = new SoftAssert();

        loginPage.login("standard_user", "secret_sauce");

        checkoutPage.AddProductInCart(driver, "Sauce Labs Onesie");
        checkoutPage.AddProductInCart(driver, "Sauce Labs Fleece Jacket");
        checkoutPage.clickOnCart();

        checkoutPage.clickOnCheckoutButton();


        checkoutPage.enterBlankFirstNameAndLastNameField("", "", 11000);

        List<WebElement> finishButon = driver.findElements(By.id(("finish")));
        if (finishButon.size() == 0) {//ovde proveravamo da li smo prosli dalje od login page
            WebElement h3 = driver.findElement(By.xpath("//h3[@data-test='error']"));
            System.out.println(h3.getText());
            if (h3.getText().equals("Error: First Name is required")) {
                System.out.println("Test je prosao, error message");

            } else {
                System.out.println("test je pao, prosli smo na sledecu stranicu");
            }
        } else {
            System.out.println("Test je pao, prosli smo na sledecu stranicu");
        }

        softAssert.assertAll();
            driver.quit();
        }


    }








