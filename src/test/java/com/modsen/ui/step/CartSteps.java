package com.modsen.ui.step;

import com.modsen.ui.page.CartPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class CartSteps {

    private CartPage page;
    private WebDriver driver;

    public CartSteps(WebDriver driver) {
        this.driver = driver;
        this.page = new CartPage(driver);
    }

    public CartSteps removeItem() {
        page.clickRemoveButton();
        return this;
    }

    public CartSteps removeItemByName(String productName) {
        page.clickRemoveButtonByProductName(productName);
        return this;
    }


    public CartSteps clickContinueShopping() {
        page.clickContinueShoppingButton();
        return this;
    }

    public InventorySteps onInventoryPage() {
        return new InventorySteps(driver);
    }

    public CartSteps clickCheckoutButton() {
        page.clickCheckoutButton();
        return this;
    }

    public CheckoutInformationSteps onCheckoutInformationPage() {
        return new CheckoutInformationSteps(driver);
    }

    public List<String> getItemNamesInCart() {
        return page.getCartItems().stream()
                .map(item -> page.getItemName(item))
                .toList();
    }

    public CartSteps verifyCartIsEmpty() {
        Assertions.assertEquals("0", page.getCardBadgeValue());
        return this;
    }

    public CartSteps verify3ItemsPresent() {
        Assertions.assertEquals(3, page.getCartItemsCount());
        return this;
    }

    public CartSteps verifyTwoItemsLeft() {
        Assertions.assertEquals(2, page.getCartItemsCount());
        return this;
    }

    public CartSteps verifyAllItemsDetails() {
        for (WebElement item : page.getCartItems()) {
            Assertions.assertTrue(page.isPriceAndRemoveButtonPresent(item),
                    "Product: " + page.getItemName(item) + " has not price or 'Remove' button");
        }

        return this;
    }
}
