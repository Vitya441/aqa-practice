package com.modsen.ui.step;

import com.modsen.ui.page.CheckoutInformationPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutInformationSteps {

    private WebDriver driver;
    private CheckoutInformationPage page;

    public CheckoutInformationSteps(WebDriver driver) {
        this.driver = driver;
        page = new CheckoutInformationPage(driver);
    }

    @Step("Заполнение данных доставки: {firstName}, {lastName}, {zipCode} и нажатие 'Continue'")
    public CheckoutInformationSteps fillShippingInfoAndClickContinue(String firstName, String lastName, String zipCode) {
        page.fillInformation(firstName, lastName, zipCode);
        page.clickContinueButton();
        return this;
    }

    @Step("Нажатие кнопки 'Continue'")
    public CheckoutInformationSteps clickContinueButton() {
        page.clickContinueButton();
        return this;
    }

    @Step("Проверка сообщения об ошибке. Ожидается: '{expectedError}'")
    public CheckoutInformationSteps verifyErrorMessage(String expectedError) {
        String actualMessage = page.getErrorMessageText();
        assertThat(expectedError).isEqualTo(actualMessage);

        return this;
    }

    public CheckoutOverviewSteps onCheckoutOverviewPage() {
        return new CheckoutOverviewSteps(driver);
    }
}
