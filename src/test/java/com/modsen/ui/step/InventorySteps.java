package com.modsen.ui.step;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.page.InventoryPage;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventorySteps {

    private WebDriver driver;
    @Getter
    private InventoryPage inventoryPage;
    private List<String> addedProducts;

    public InventorySteps(WebDriver driver) {
        this.driver = driver;
        inventoryPage = new InventoryPage(driver);
        addedProducts = new ArrayList<>();
    }

    public InventorySteps addItemToCart() {
        inventoryPage.clickAddToCart();
        return this;
    }

    public InventorySteps verifyButtonText(String expected) {
        Assertions.assertEquals(expected, inventoryPage.getButtonText());
        return this;
    }

    public InventorySteps verifyBudgeCount(String expected) {
        Assertions.assertEquals(expected, inventoryPage.getHeader().getCardBadgeValue());
        return this;
    }

    public CartSteps goToCart() {
        inventoryPage.getHeader().clickShoppingCartLink();
        return new CartSteps(driver);
    }

    public InventorySteps add3RandomProducts() {
        List<String> allNames = new ArrayList<>(inventoryPage.getAllProductNames());
        Collections.shuffle(allNames);

        for (int i = 0; i < 3; i++) {
            String name = allNames.get(i);
            inventoryPage.clickButtonByProductName(name);
            addedProducts.add(name);

            verifyBudgeCount(String.valueOf(i + 1));
        }

        return this;
    }

    public InventorySteps verifyProductIsAvailable(String name) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", name);
        String btnText = driver.findElement(By.xpath(xpath)).getText();
        Assertions.assertEquals("Add to cart", btnText);

        return this;
    }

    public InventorySteps sortItems(String sortValue) {
        inventoryPage.selectSortOption(sortValue);
        return this;
    }

    public InventorySteps verifySortByNameAscending() {
        List<String> actualNames = inventoryPage.getAllProductNames();
        List<String> expectedNames = actualNames.stream().sorted().toList();
        Assertions.assertEquals(expectedNames, actualNames);

        return this;
    }

    public InventorySteps verifySortByNameDescending() {
        List<String> actualNames = inventoryPage.getAllProductNames();
        List<String> expectedNames = actualNames.stream().sorted(Comparator.reverseOrder()).toList();
        Assertions.assertEquals(expectedNames, actualNames);

        return this;
    }

    public InventorySteps verifySortByPriceAscending() {
        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted().toList();
        Assertions.assertEquals(expectedPrices, actualPrices);

        return this;
    }

    public InventorySteps verifySortByPriceDescending() {
        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted(Comparator.reverseOrder()).toList();
        Assertions.assertEquals(expectedPrices, actualPrices);

        return this;
    }

    // TODO: Не нравиться создавать методы, которые просто делегируют вызов другому слою (Page)
    //       Такая ситуация не только в этом месте
    public ProductModel getProductDataByName(String productName) {
        return inventoryPage.getProductDataByName(productName);
    }

    public InventoryDetailsSteps openProductByName(String productName) {
        inventoryPage.openProductByName(productName);
        return new InventoryDetailsSteps(driver);
    }

    public InventorySteps verifyInventoryPageVisible() {
        String actualTitle = inventoryPage.getInventoryTitleText();
        Assertions.assertEquals("Products", actualTitle);

        return this;
    }
}
