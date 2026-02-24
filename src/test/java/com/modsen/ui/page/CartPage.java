package com.modsen.ui.page;

import com.modsen.ui.page.component.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    @Getter
    private HeaderComponent header;
    private By removeButton = By.className("cart_button");
    private By cartItem = By.className("cart_item");
    private By cartItemName = By.className("inventory_item_name");
    private By cartItemPrice = By.className("inventory_item_price");
    private By cartRemoveButton = By.cssSelector("button[id^='remove']");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver);
    }

    public void clickRemoveButton() {
        driver.findElement(removeButton).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItem).size();
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(cartItem);
    }

    public String getItemName(WebElement item) {
        return item.findElement(cartItemName).getText();
    }

    public boolean isPriceAndRemoveButtonPresent(WebElement item) {
        return item.findElement(cartItemPrice).isDisplayed()
                && item.findElement(cartRemoveButton).isDisplayed();
    }
}
