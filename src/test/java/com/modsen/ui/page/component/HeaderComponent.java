package com.modsen.ui.page.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HeaderComponent {

    private WebDriver driver;
    private By shoppingCartLink = By.className("shopping_cart_link");
    private By shoppingCartBadge = By.className("shopping_cart_badge");

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void clickShoppingCartLink() {
        driver.findElement(shoppingCartLink).click();
    }

    public String getCardBadgeValue() {
        List<WebElement> badges = driver.findElements(shoppingCartBadge);
        if (badges.isEmpty()) {
            return "0";
        }
        return badges.getFirst().getText();
    }

    public boolean isBadgeMissing() {
        return driver.findElements(shoppingCartBadge).isEmpty();
    }
}
