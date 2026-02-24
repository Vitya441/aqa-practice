package com.modsen.ui.page;

import com.modsen.ui.page.component.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    @Getter
    private HeaderComponent header;
    private By completeHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
    }

    public String getHeaderText() {
        return driver.findElement(completeHeader).getText();
    }
}
