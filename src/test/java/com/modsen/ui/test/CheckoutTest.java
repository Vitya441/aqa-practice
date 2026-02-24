package com.modsen.ui.test;

import com.modsen.ui.step.CartSteps;
import com.modsen.ui.step.LoginSteps;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CheckoutTest extends BaseTest {

    // Задание 5
    @Test
    void shouldCompleteCheckoutSuccessfully() {
        CartSteps cartSteps = new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce")
                .add3RandomProducts()
                .goToCart();

        List<String> expectedNames = cartSteps.getItemNamesInCart();

        cartSteps
                .goToCheckout()
                .fillShippingInfoAndClickContinue("John", "Doe", "123")
                .verifyInventoryList(expectedNames)
                .finishOrder()
                .verifyOrderCompletedAndCartEmpty();
    }
}
