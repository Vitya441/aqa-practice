package com.modsen.ui.step;

import com.modsen.ui.page.CheckoutInformationPage;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutInformationSteps {

    private WebDriver driver;
    private CheckoutInformationPage page;

    public CheckoutInformationSteps(WebDriver driver) {
        this.driver = driver;
        page = new CheckoutInformationPage(driver);
    }

    public CheckoutInformationSteps fillShippingInfoAndClickContinue(String firstName, String lastName, String zipCode) {
        page.fillInformation(firstName, lastName, zipCode);
        page.clickContinueButton();
        return this;
    }

    public CheckoutInformationSteps clickContinueButton() {
        page.clickContinueButton();
        return this;
    }

    public CheckoutInformationSteps verifyErrorMessage(String expectedError) {
        String actualMessage = page.getErrorMessageText();
        assertThat(expectedError).isEqualTo(actualMessage);

        return this;
    }

    public CheckoutOverviewSteps onCheckoutOverviewPage() {
        return new CheckoutOverviewSteps(driver);
    }
}
