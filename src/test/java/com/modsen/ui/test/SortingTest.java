package com.modsen.ui.test;

import com.modsen.ui.step.LoginSteps;
import org.junit.jupiter.api.Test;

// Задание 6
public class SortingTest extends BaseTest {

    @Test
    void shouldSortProductsByNameAscending() {
        new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce")
                .sortItems("az")
                .verifySortByNameAscending();
    }

    @Test
    void shouldSortProductsByNameDescending() {
        new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce")
                .sortItems("za")
                .verifySortByNameDescending();
    }

    @Test
    void shouldSortProductsByPriceAscending() {
        new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce")
                .sortItems("lohi")
                .verifySortByPriceAscending();
    }

    @Test
    void shouldSortProductsByPriceDescending() {
        new LoginSteps(driver)
                .openMainPage()
                .login("standard_user", "secret_sauce")
                .sortItems("hilo")
                .verifySortByPriceDescending();
    }
}
