package com.modsen.ui.page;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.page.component.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InventoryPage extends BasePage {

    private HeaderComponent header;
    private By inventoryButtons = By.xpath("//button[contains(@class, 'btn_inventory')]");
    private By inventoryItems = By.xpath("//div[contains(@class, 'inventory_list')]/div");
    private By itemNames = By.xpath("//div[contains(@class, 'inventory_item_name')]");
    private By sortContainer = By.xpath("//select");
    private By productPrices = By.xpath("//div[contains(@class, 'inventory_list')]/div//div[contains(@class, 'inventory_item_price')]");
    private By pageTitle = By.xpath("//span[contains(@class, 'title')]");

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

        String name = container.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
        String description = container.findElement(By.xpath(".//div[@data-test='inventory-item-desc']")).getText();
        String price = container.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText();

        return new ProductModel(name, description, price);
    }

    public void openProductByName(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']")).click();
    }

    public String getButtonTextByProductName(String productName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String getInventoryTitleText() {
        return driver.findElement(pageTitle).getText();
    }

    public String getCardBadgeValue() {
        return header.getCardBadgeValue();
    }

    public void clickShoppingCartLink() {
        header.clickShoppingCartLink();
    }
}
