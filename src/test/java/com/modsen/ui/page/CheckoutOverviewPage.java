package com.modsen.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private By cartItems = By.cssSelector(".cart_list .cart_item");
    private By itemNames = By.cssSelector(".inventory_item_name");
    private By finishButton = By.cssSelector("[data-test='finish']");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickFinish() {
        waitClickableAndClick(finishButton);
    }
}
