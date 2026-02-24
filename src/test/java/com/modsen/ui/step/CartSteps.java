package com.modsen.ui.step;

import com.modsen.ui.page.CartPage;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;


public class CartSteps {

    private CartPage cartPage;
    private WebDriver driver;
    @Getter
    private String removedProductName;

    public CartSteps(WebDriver driver) {
        this.driver = driver;
        this.cartPage = new CartPage(driver);
    }

    public CartSteps removeItem() {
        cartPage.clickRemoveButton();
        return this;
    }

    public CartSteps verifyCartIsEmpty() {
        Assertions.assertEquals("0", cartPage.getHeader().getCardBadgeValue());
        return this;
    }

    public CartSteps verify3ItemsPresent() {
        Assertions.assertEquals(3, cartPage.getCartItemsCount());
        return this;
    }

    public CartSteps verifyAllItemsDetails() {
        for (WebElement item : cartPage.getCartItems()) {
            Assertions.assertTrue(cartPage.isPriceAndRemoveButtonPresent(item),
                    "Product: " + cartPage.getItemName(item) + " has not price or 'Remove' button");
        }

        return this;
    }

    public CartSteps removeRandomItem() {
        List<WebElement> items = cartPage.getCartItems();
        int randomIndex = new Random().nextInt(items.size());
        WebElement itemToRemove = items.get(randomIndex);

        removedProductName = cartPage.getItemName(itemToRemove);
        itemToRemove.findElement(By.cssSelector("button[id^='remove']")).click();

        return this;
    }

    public CartSteps verifyTwoItemsLeft() {
        Assertions.assertEquals(2, cartPage.getCartItemsCount());
        return this;
    }

    public InventorySteps returnToInventory() {
        driver.findElement(By.id("continue-shopping")).click();
        return new InventorySteps(driver);
    }

    public CheckoutSteps goToCheckout() {
        cartPage.clickCheckoutButton();
        return new CheckoutSteps(driver);
    }

    public List<String> getItemNamesInCart() {
        return cartPage.getCartItems().stream()
                .map(item -> cartPage.getItemName(item))
                .toList();
    }
}
