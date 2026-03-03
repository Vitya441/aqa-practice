package com.modsen.ui.step;

import com.modsen.ui.page.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private LoginPage loginPage;
    private WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    @Step("Открытие главной страницы приложения")
    public LoginSteps openMainPage() {
        driver.get("https://www.saucedemo.com");
        return this;
    }

    @Step("Авторизация под пользователем: {username}")
    public LoginSteps login(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        return this;
    }

    @Step("Переход на страницу со списком товаров")
    public InventorySteps onInventoryPage() {
        return new InventorySteps(driver);
    }
}
