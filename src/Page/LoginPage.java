package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    public void login(String username, String password) {
        WebElement inputUserName = driver.findElement(By.id("user-name"));
        inputUserName.clear();
        inputUserName.sendKeys(username);

        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys(password);

        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        buttonLogin.click();
    }
}