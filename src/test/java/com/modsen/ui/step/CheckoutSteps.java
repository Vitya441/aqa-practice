package com.modsen.ui.step;

import com.modsen.ui.page.CheckoutCompletePage;
import com.modsen.ui.page.CheckoutInformationPage;
import com.modsen.ui.page.CheckoutOverviewPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// TODO:
//      1) Разбить не несколько Step-классов
//      2) Исользовать одинаковые локаторы а не разные, Xpath советовал Кирилл
//      3) Перемещение между страницами - явная передача состояния или 'introduce local variable'
//      4) Много тестовых классов у меня (сделать один)
@Deprecated
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

    /**
     * Перенесено в {@link com.modsen.ui.step.CheckoutInformationSteps}
     */
    @Deprecated
    public CheckoutSteps fillShippingInfoAndClickContinue(String firstName, String lastName, String zipCode) {
        informationPage.fillInformation(firstName, lastName, zipCode);
        informationPage.clickContinueButton();
        return this;
    }

    /**
     * Перенесено в {@link com.modsen.ui.step.CheckoutInformationSteps}
     */
    @Deprecated
    public CheckoutSteps clickContinueButton() {
        informationPage.clickContinueButton();
        return this;
    }

    /**
     * Перенесено в {@link com.modsen.ui.step.CheckoutOverviewSteps}
     */
    @Deprecated
    public CheckoutSteps verifyInventoryList(List<String> expectedNames) {
        List<String> actualNames = overviewPage.getItemNames();
        assertThat(expectedNames).isEqualTo(actualNames);

        return this;
    }

    /**
     * Перенесено в {@link com.modsen.ui.step.CheckoutOverviewSteps}
     */
    @Deprecated
    public CheckoutSteps finishOrder() {
        overviewPage.clickFinish();
        return this;
    }

    /**
     * Перенесено в {@link com.modsen.ui.step.CheckoutCompleteSteps}
     */
    @Deprecated
    public CheckoutSteps verifyOrderCompletedAndCartEmpty() {
        String actualHeaderText = completePage.getHeaderText();
        assertThat("Thank you for your order!").isEqualTo(actualHeaderText);
        assertThat("0").isEqualTo(completePage.getCardBadgeValue());

        return this;
    }

    /**
     * Перенесено в {@link com.modsen.ui.step.CheckoutInformationSteps}
     */
    @Deprecated
    public CheckoutSteps verifyErrorMessage(String expectedError) {
        String actualError = informationPage.getErrorMessageText();
        Assertions.assertThat(actualError).contains(expectedError);

        return this;
    }
}
