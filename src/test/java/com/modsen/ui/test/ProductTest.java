package com.modsen.ui.test;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.step.CartSteps;
import com.modsen.ui.step.InventorySteps;
import com.modsen.ui.step.LoginSteps;
import org.junit.jupiter.api.Test;

public class ProductTest extends BaseTest {

    // Задание 3
    @Test
    void shouldAddItemToCartThenRemoveItWithChecks() {
        new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce")
                .addItemToCart()
                .verifyButtonText("Remove")
                .verifyBudgeCount("1")
                .goToCart()
                .removeItem()
                .verifyCartIsEmpty();
    }

    // Задание 4
    @Test
    void shouldAdd3RandomProductsToCartThenRemoveRandomProductFromCart() {
        InventorySteps inventorySteps = new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce");

        CartSteps cartSteps = inventorySteps
                .add3RandomProducts()
                .goToCart()
                .verify3ItemsPresent()
                .verifyAllItemsDetails()
                .removeRandomItem()
                .verifyTwoItemsLeft();

        String removedProductName = cartSteps.getRemovedProductName();

        cartSteps.returnToInventory()
                .verifyProductIsAvailable(removedProductName);
    }

    // Задание 7
    @Test
    void shouldCompareProductFromGeneralPageAndDetailsPage() {
        InventorySteps inventorySteps = new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce");

        String targetName = "Sauce Labs Backpack";
        ProductModel expectedProduct = inventorySteps.getProductDataByName(targetName);

        inventorySteps
                .openProductByName(targetName)
                .verifyProductData(expectedProduct);
    }

    // Задание 8
    @Test
    void shouldAddProductToCartAndTryToCheckoutWithoutFillInformation() {
        InventorySteps inventorySteps = new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce");

        inventorySteps
                .addItemToCart()
                .goToCart()
                .goToCheckout()
                .clickContinueButton()
                .verifyErrorMessage("Error: First Name is required");
    }
}
