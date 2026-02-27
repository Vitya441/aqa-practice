package com.modsen.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private By cartItems = By.xpath("//div[@data-test='cart-list']");
    private By itemNames = By.xpath("//div[@data-test='inventory-item-name']");
    private By finishButton = By.xpath("//button[@data-test='finish']");

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
