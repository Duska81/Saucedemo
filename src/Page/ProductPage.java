package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }


    public Integer returnItemNumber(WebDriver driver) {
        Integer toReturn = null;
        WebElement linkShoppingCart = driver.findElement(By.className("shopping_cart_link"));
        List<WebElement> spanItemNumber = linkShoppingCart.findElements(By.className("shopping_cart_badge"));
        if (spanItemNumber.size() == 0) {
            toReturn = 0;

        } else {
            toReturn = Integer.parseInt(spanItemNumber.get(0).getText());

        }
        return toReturn;

    }


    public void addProductToCart(WebDriver driver, String productName) {
        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
        int itemCount = inventoryItems.size();

        for (int i = 0; i < itemCount; i++) {
            WebElement inventoryItem = inventoryItems.get(i);
            WebElement inventoryItemName = inventoryItem.findElement(By.className("inventory_item_name"));

            if (inventoryItemName.getText().equals(productName)) {
                WebElement addToCartButton = inventoryItem.findElement(By.xpath(".//button"));
                addToCartButton.click();
                break;
            }
        }
    }

    public boolean verifyProductPrice(WebDriver driver, String expectedPrice) {
        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
        int itemCount = inventoryItems.size();

        for (int i = 0; i < itemCount; i++) {
            WebElement inventoryItem = inventoryItems.get(i);
            WebElement itemPrice = inventoryItem.findElement(By.className("inventory_item_price"));

            if (itemPrice.getText().equals(expectedPrice)) {
                System.out.println("Cena proizvoda je tačna.");
                return true;
            }
        }

        System.out.println("Cena proizvoda nije tačna.");
        return false;
    }


    public String getProductDescription(String productName) {
        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
        int itemCount = inventoryItems.size();

        for (int i = 0; i < itemCount; i++) {
            WebElement inventoryItem = inventoryItems.get(i);
            WebElement inventoryItemName = inventoryItem.findElement(By.className("inventory_item_name"));

            if (inventoryItemName.getText().equals(productName)) {
                WebElement itemDescription = inventoryItem.findElement(By.className("inventory_item_desc"));
                String descriptionText = itemDescription.getText();

                return descriptionText;

            }
        }

        System.out.println("Product not found: " + productName);
        return null;
    }

    public boolean verifyProductInCart(WebDriver driver, String productName) {
        WebElement shoppingCart = driver.findElement(By.className("shopping_cart_link"));
        shoppingCart.click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        for (int i = 0; i < cartItems.size(); i++) {
            WebElement cartItem = cartItems.get(i);
            WebElement itemName = cartItem.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                System.out.println("Proizvod " + productName + " se nalazi u korpi.");
                return true;
            }
        }

        System.out.println("Proizvod " + productName + " se ne nalazi u korpi.");
        return false;
    }


    public void openCart() {
    }

    public boolean isCartEmpty() {
        return true;
    }
}

