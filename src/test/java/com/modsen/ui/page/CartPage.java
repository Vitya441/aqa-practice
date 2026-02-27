package com.modsen.ui.page;

import com.modsen.ui.page.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private HeaderComponent header;
    private By removeButton = By.xpath("//button[contains(@class, 'cart_button')]");
    private By cartItems = By.xpath("//div[@data-test='inventory-item']");
    private By cartItemName = By.xpath(".//div[contains(@class, 'inventory_item_name')]");
    private By cartItemPrice = By.xpath("//div[contains(@class, 'inventory_item_price')]");
    private By cartRemoveButton = By.xpath("//button[contains(@class, 'cart_button')]");
    private By checkoutButton = By.xpath("//button[contains(@class, 'checkout_button')]");
    private By continueShoppingButton = By.xpath("//button[@data-test='continue-shopping']");

    public CartPage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver);
    }

    public void clickRemoveButton() {
        driver.findElement(removeButton).click();
    }

    public void clickRemoveButtonByProductName(String productName) {
        String specifiedButton = String.format(
                "//div[@data-test='inventory-item' and .//div[text()='%s']]//button[contains(@class, 'cart_button')]",
                productName
        );
        driver.findElement(By.xpath(specifiedButton)).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public void clickContinueShoppingButton() {
        driver.findElement(continueShoppingButton).click();
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    public String getItemName(WebElement item) {
        return item.findElement(cartItemName).getText();
    }

    public boolean isPriceAndRemoveButtonPresent(WebElement item) {
        return item.findElement(cartItemPrice).isDisplayed()
                && item.findElement(cartRemoveButton).isDisplayed();
    }

    public String getCardBadgeValue() {
        return header.getCardBadgeValue();
    }
}
