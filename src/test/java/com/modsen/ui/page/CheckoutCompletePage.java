package com.modsen.ui.page;

import com.modsen.ui.page.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private HeaderComponent header;
    private By completeHeader = By.xpath("//h2[@data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
    }

    public String getHeaderText() {
        return driver.findElement(completeHeader).getText();
    }

    public String getCardBadgeValue() {
        return header.getCardBadgeValue();
    }
}
