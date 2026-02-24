package com.modsen.ui.step;

import com.modsen.ui.page.CheckoutCompletePage;
import com.modsen.ui.page.CheckoutInformationPage;
import com.modsen.ui.page.CheckoutOverviewPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutSteps {

    private WebDriver driver;
    private CheckoutInformationPage informationPage;
    private CheckoutOverviewPage overviewPage;
    private CheckoutCompletePage completePage;

    public CheckoutSteps(WebDriver driver) {
        this.driver = driver;
        informationPage = new CheckoutInformationPage(driver);
        overviewPage = new CheckoutOverviewPage(driver);
        completePage = new CheckoutCompletePage(driver);
    }

    public CheckoutSteps fillShippingInfoAndClickContinue(String firstName, String lastName, String zipCode) {
        informationPage.fillInformation(firstName, lastName, zipCode);
        informationPage.clickContinueButton();
        return this;
    }

    public CheckoutSteps clickContinueButton() {
        informationPage.clickContinueButton();
        return this;
    }

    public CheckoutSteps verifyInventoryList(List<String> expectedNames) {
        List<String> actualNames = overviewPage.getItemNames();
        assertThat(expectedNames).isEqualTo(actualNames);

        return this;
    }

    public CheckoutSteps finishOrder() {
        overviewPage.clickFinish();
        return this;
    }

    public CheckoutSteps verifyOrderCompletedAndCartEmpty() {
        String actualHeaderText = completePage.getHeaderText();
        assertThat("Thank you for your order!").isEqualTo(actualHeaderText);
        assertThat("0").isEqualTo(completePage.getHeader().getCardBadgeValue());

        return this;
    }

    public CheckoutSteps verifyErrorMessage(String expectedError) {
        String actualError = informationPage.getErrorMessageText();
        Assertions.assertThat(actualError).contains(expectedError);

        return this;
    }
}
