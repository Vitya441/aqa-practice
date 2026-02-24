package com.modsen.ui.test;

import com.modsen.ui.step.LoginSteps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FullScenarioTest extends BaseTest {

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
                .addItemToCart()
                .goToCart()
                .goToCheckout()
                .fillShippingInfoAndClickContinue("John", "Doe", "12345")
                .finishOrder()
                .verifyOrderCompletedAndCartEmpty();
    }
}
