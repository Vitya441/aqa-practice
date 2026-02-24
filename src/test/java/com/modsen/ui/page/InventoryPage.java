package com.modsen.ui.page;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.page.component.HeaderComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {

    @Getter
    private HeaderComponent header;

    private By inventoryButtons = By.className("btn_inventory");
    private By inventoryItems = By.className("inventory_item");
    private By itemNames = By.className("inventory_item_name");
    private By sortContainer = By.className("product_sort_container");
    private By productPrices = By.className("inventory_item_price");
    private By pageTitle = By.className("title");

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
    }

    public void clickAddToCart() {
        waitClickableAndClick(inventoryButtons);
    }

    public List<WebElement> getAllInventoryButtons() {
        return driver.findElements(inventoryButtons);
    }

    public String getButtonText() {
        return waitVisible(inventoryButtons).getText();
    }

    public List<String> getAllProductNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickButtonByProductName(String productName) {
        String buttonXpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        waitClickableAndClick(By.xpath(buttonXpath));
    }

    public void selectSortOption(String value) {
        Select select = new Select(driver.findElement(sortContainer));
        select.selectByValue(value);
    }

    public List<Double> getAllProductPrices() {
        return driver.findElements(productPrices).stream()
                .map(WebElement::getText)
                .map(text -> Double.parseDouble(text.replace("$", "")))
                .toList();
    }

    public ProductModel getProductDataByName(String productName) {
        String containerXpath = String.format("//div[@class='inventory_item'][.//div[text()='%s']]", productName);
        WebElement container = driver.findElement(By.xpath(containerXpath));

        String name = container.findElement(By.className("inventory_item_name")).getText();
        String description = container.findElement(By.className("inventory_item_desc")).getText();
        String price = container.findElement(By.className("inventory_item_price")).getText();

        return new ProductModel(name, description, price);
    }

    public void openProductByName(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']")).click();
    }

    public String getInventoryTitleText() {
        return driver.findElement(pageTitle).getText();
    }
}
