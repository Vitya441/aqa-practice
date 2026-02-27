package com.modsen.ui.test;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.step.CartSteps;
import com.modsen.ui.step.InventorySteps;
import com.modsen.ui.step.LoginSteps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Random;

public class MainTest extends BaseTest {

    // Задание 2
    @Test
    void shouldSuccessfullyLoginWithValidCredentials() {
        new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .verifyInventoryPageVisible();
    }

    // Задание 3
    @Test
    void shouldAddItemToCartThenRemoveItWithChecks() {
        new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .addItemToCart()
                    .verifyButtonText("Remove")
                    .verifyBudgeCount("1")
                    .clickShoppingCartLink()
                .onCartPage()
                    .removeItem()
                    .verifyCartIsEmpty();
    }

    // Задание 4
    @Test
    void shouldAdd3RandomProductsToCartThenRemoveRandomProductFromCart() {
        InventorySteps inventorySteps = new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage();

        CartSteps cartSteps = inventorySteps
                    .add3RandomProducts()
                    .clickShoppingCartLink()
                .onCartPage()
                    .verify3ItemsPresent()
                    .verifyAllItemsDetails();

        List<String> productsInCart = cartSteps.getItemNamesInCart();
        String productToRemove = productsInCart.get(new Random().nextInt(productsInCart.size()));

        cartSteps.removeItemByName(productToRemove)
                    .verifyTwoItemsLeft()
                    .clickContinueShopping()
                .onInventoryPage()
                    .verifyProductIsAvailable(productToRemove);
    }

    // Задание 5
    @Test
    void shouldCompleteCheckoutSuccessfully() {
        CartSteps cartSteps = new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .add3RandomProducts()
                    .clickShoppingCartLink()
                .onCartPage();

        List<String> namesInCart = cartSteps.getItemNamesInCart();

        cartSteps.clickCheckoutButton()
                .onCheckoutInformationPage()
                .   fillShippingInfoAndClickContinue("John", "Doe", "123")
                .onCheckoutOverviewPage()
                    .verifyInventoryList(namesInCart)
                    .clickFinishOrder()
                .onCheckoutCompletePage()
                    .verifyOrderIsCompletedAndCartIsEmpty();
    }

    // Задание 6
    @Test
    void shouldSortProductsByNameAscending() {
        new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .sortItems("az")
                    .verifySortByNameAscending();
    }

    @Test
    void shouldSortProductsByNameDescending() {
        new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .sortItems("za")
                    .verifySortByNameDescending();
    }

    @Test
    void shouldSortProductsByPriceAscending() {
        new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .sortItems("lohi")
                    .verifySortByPriceAscending();
    }

    @Test
    void shouldSortProductsByPriceDescending() {
        new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .sortItems("hilo")
                    .verifySortByPriceDescending();
    }

    // Задание 7
    @Test
    void shouldCompareProductFromGeneralPageAndDetailsPage() {
        InventorySteps inventorySteps = new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage();

        String targetName = "Sauce Labs Backpack";
        ProductModel expectedProduct = inventorySteps.getProductDataByName(targetName);

        inventorySteps
                .openProductByName(targetName)
                .verifyProductData(expectedProduct);
    }

    // Задание 8
    @Test
    void shouldAddProductToCartAndTryToCheckoutWithoutFillInformation() {
        new LoginSteps(driver)
                    .openMainPage()
                    .login("standard_user", "secret_sauce")
                .onInventoryPage()
                    .addItemToCart()
                    .clickShoppingCartLink()
                .onCartPage()
                    .clickCheckoutButton()
                .onCheckoutInformationPage()
                    .clickContinueButton()
                    .verifyErrorMessage("Error: First Name is required");
    }

    // Задание 9
    @ParameterizedTest
    @CsvSource({
            "standard_user, secret_sauce",
            "performance_glitch_user, secret_sauce"
    })
    void shouldSuccessfullyExecuteFullBusinessScenarioForBothUsers(String username, String password) {
        new LoginSteps(driver)
                .openMainPage()
                .login(username, password)
                .onInventoryPage()
                .addItemToCart()
                .clickShoppingCartLink()
                .onCartPage()
                .clickCheckoutButton()
                .onCheckoutInformationPage()
                .fillShippingInfoAndClickContinue("John", "Doe", "12345")
                .onCheckoutOverviewPage()
                .clickFinishOrder()
                .onCheckoutCompletePage()
                .verifyOrderIsCompletedAndCartIsEmpty();
    }
}
