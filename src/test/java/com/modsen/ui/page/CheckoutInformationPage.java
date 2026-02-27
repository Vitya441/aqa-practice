package com.modsen.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {

    private By firstNameField = By.xpath("//input[@placeholder='First Name']");
    private By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    private By zipCodeField = By.xpath("//input[@placeholder='Zip/Postal Code']");
    private By continueButton = By.xpath("//input[contains(@class, 'submit-button')]");
    private By errorContainer = By.xpath("//div[contains(@class, 'error')]");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String firstName, String lastName, String zipCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public String getErrorMessageText() {
        return waitVisible(errorContainer).getText();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorContainer).isDisplayed();
    }
}
