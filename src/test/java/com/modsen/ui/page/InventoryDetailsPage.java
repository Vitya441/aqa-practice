package com.modsen.ui.page;

import com.modsen.ui.model.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryDetailsPage extends BasePage {

    private By productName = By.className("inventory_details_name");
    private By productPrice = By.className("inventory_details_price");
    private By productDescription = By.className("inventory_details_desc");
    private By backButton = By.id("back-to-products");

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
