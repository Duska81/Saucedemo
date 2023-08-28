package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

    public class CartProductPage {
        private WebDriver driver;
        private By productPriceLabels = By.className("inventory_item_price");

        public CartProductPage(WebDriver driver) {
            this.driver = driver;
        }
        public void addProductInCartCart(WebDriver driver, String productName) {
            List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
            int itemCount = inventoryItems.size();

            for (int i = 0; i < itemCount; i++) {
                WebElement inventoryItem = inventoryItems.get(i);
                WebElement inventoryItemName = inventoryItem.findElement(By.className("inventory_item_name"));

                if (inventoryItemName.getText().equals(productName)) {
                    WebElement addToCartButton = inventoryItem.findElement(By.xpath(".//button"));
                    addToCartButton.click();
                    System.out.println("Proizvod dodat u korpu: " + productName);

                }
            }
        }

        public void clickOnCart() {
            WebElement cart = driver.findElement(By.className("shopping_cart_link"));
            cart.click();


        }

        public void clickOnCheckoutButton() {
            WebElement checkout = driver.findElement(By.id("checkout"));
            checkout.click();
        }
        public void clickOnContinueButton() {
            By continueButtonSelector = By.cssSelector("input[data-test='continue']");
        }

            public void enterValidInformation(String firstName, String lastName, int postalCode) {
            WebElement firstNameInput = driver.findElement(By.id("first-name"));
            WebElement lastNameInput = driver.findElement(By.id("last-name"));
            WebElement postalCodeInput = driver.findElement(By.id("postal-code"));
            WebElement continueButton = driver.findElement(By.id("continue"));
            firstNameInput.sendKeys(firstName);
            lastNameInput.sendKeys(lastName);
            postalCodeInput.sendKeys(String.valueOf(postalCode));


            continueButton.click();

        }

            public String getSummaryTotalLabelText() {
                WebElement totalLabel = driver.findElement(By.className("summary_total_label"));
                return totalLabel.getText();
            }

        public  List<Double> getProductPrices() {
            List<Double> prices = new ArrayList<>();
            List<WebElement> priceElements = driver.findElements(productPriceLabels);

            for (int i = 0; i < priceElements.size(); i++) {
                WebElement element = priceElements.get(i);
                String priceText = element.getText().replaceAll("[^\\d.]", "");
                double price = Double.parseDouble(priceText);
                prices.add(price);
            }

            return prices;
        }

        public double calculateTotalPrice() {
            List<Double> prices = getProductPrices();
            double totalPrice = 0.0;

            for (int i = 0; i < prices.size(); i++) {
                Double price = prices.get(i);
                totalPrice += price;
            }

            return totalPrice;
        }

        public List<Double> getProductPricesInCart() {
            return getProductPrices();
        }
    }


