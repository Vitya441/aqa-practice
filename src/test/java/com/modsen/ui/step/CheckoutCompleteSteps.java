package com.modsen.ui.step;

import com.modsen.ui.page.CheckoutCompletePage;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutCompleteSteps {

    private WebDriver driver;
    private CheckoutCompletePage page;

    public CheckoutCompleteSteps(WebDriver driver) {
        this.driver = driver;
        this.page = new CheckoutCompletePage(driver);
    }

    public CheckoutCompleteSteps verifyOrderIsCompletedAndCartIsEmpty() {
        String actualHeaderText = page.getHeaderText();
        assertThat("Thank you for your order!").isEqualTo(actualHeaderText);
        assertThat("0").isEqualTo(page.getCardBadgeValue());

        return this;
    }
}
