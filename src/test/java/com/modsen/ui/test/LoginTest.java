package com.modsen.ui.test;

import com.modsen.ui.step.LoginSteps;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    // Задание 2
    @Test
    void shouldSuccessfullyLoginWithValidCredentials() {
        new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce")
                .verifyInventoryPageVisible();
    }
}
