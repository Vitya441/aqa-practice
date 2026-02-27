package com.modsen.ui.step;

import com.modsen.ui.page.CheckoutOverviewPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutOverviewSteps {

    private WebDriver driver;
    private CheckoutOverviewPage page;

    public CheckoutOverviewSteps(WebDriver driver) {
        this.driver = driver;
        this.page = new CheckoutOverviewPage(driver);
    }

    public CheckoutOverviewSteps verifyInventoryList(List<String> expectedNames) {
        List<String> actualNames = page.getItemNames();
        assertThat(expectedNames).isEqualTo(actualNames);

        return this;
    }

    public CheckoutOverviewSteps clickFinishOrder() {
        page.clickFinish();
        return this;
    }

    public CheckoutCompleteSteps onCheckoutCompletePage() {
        return new CheckoutCompleteSteps(driver);
    }
}
