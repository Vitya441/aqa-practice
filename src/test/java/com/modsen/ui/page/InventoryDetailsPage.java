package com.modsen.ui.page;

import com.modsen.ui.model.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryDetailsPage extends BasePage {

    private By productName = By.xpath("//div[@data-test='inventory-item-name']");
    private By productPrice = By.xpath("//div[@data-test='inventory-item-price']");
    private By productDescription = By.xpath("//div[@data-test='inventory-item-desc']");
    private By backButton = By.xpath("//button[@name='back-to-products']");

    public InventoryDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ProductModel getProductData() {
        return new ProductModel(
                driver.findElement(productName).getText(),
                driver.findElement(productDescription).getText(),
                driver.findElement(productPrice).getText()
        );
    }
}
