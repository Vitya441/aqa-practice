package com.modsen.ui.step;

import com.modsen.ui.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private LoginPage loginPage;
    private WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public LoginSteps openMainPage() {
        driver.get("https://www.saucedemo.com");
        return this;
    }

    public LoginSteps login(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        return this;
    }

    public InventorySteps onInventoryPage() {
        return new InventorySteps(driver);
    }
}
