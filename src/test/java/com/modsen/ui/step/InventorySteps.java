package com.modsen.ui.step;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.page.InventoryPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InventorySteps {

    private WebDriver driver;
    private InventoryPage page;

    public InventorySteps(WebDriver driver) {
        this.driver = driver;
        page = new InventoryPage(driver);
    }

    public InventorySteps addItemToCart() {
        page.clickAddToCart();
        return this;
    }

    public InventorySteps verifyButtonText(String expected) {
        Assertions.assertEquals(expected, page.getButtonText());
        return this;
    }

    public InventorySteps verifyBudgeCount(String expected) {
        Assertions.assertEquals(expected, page.getCardBadgeValue());
        return this;
    }

    public CartSteps onCartPage() {
        return new CartSteps(driver);
    }

    public InventorySteps clickShoppingCartLink() {
        page.clickShoppingCartLink();
        return this;
    }

    public InventorySteps add3RandomProducts() {
        List<String> allNames = new ArrayList<>(page.getAllProductNames());
        Collections.shuffle(allNames);

        for (int i = 0; i < 3; i++) {
            String name = allNames.get(i);
            page.clickButtonByProductName(name);
            verifyBudgeCount(String.valueOf(i + 1));
        }

        return this;
    }

    public InventorySteps sortItems(String sortValue) {
        page.selectSortOption(sortValue);
        return this;
    }

    public ProductModel getProductDataByName(String productName) {
        return page.getProductDataByName(productName);
    }

    public InventoryDetailsSteps openProductByName(String productName) {
        page.openProductByName(productName);
        return new InventoryDetailsSteps(driver);
    }

    public InventorySteps verifyProductIsAvailable(String productName) {
        String actualButtonText = page.getButtonTextByProductName(productName);
        Assertions.assertEquals("Add to cart", actualButtonText);

        return this;
    }

    public InventorySteps verifyInventoryPageVisible() {
        String actualTitle = page.getInventoryTitleText();
        Assertions.assertEquals("Products", actualTitle);

        return this;
    }

    public InventorySteps verifySortByNameAscending() {
        List<String> actualNames = page.getAllProductNames();
        List<String> expectedNames = actualNames.stream().sorted().toList();
        Assertions.assertEquals(expectedNames, actualNames);

        return this;
    }

    public InventorySteps verifySortByNameDescending() {
        List<String> actualNames = page.getAllProductNames();
        List<String> expectedNames = actualNames.stream().sorted(Comparator.reverseOrder()).toList();
        Assertions.assertEquals(expectedNames, actualNames);

        return this;
    }

    public InventorySteps verifySortByPriceAscending() {
        List<Double> actualPrices = page.getAllProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted().toList();
        Assertions.assertEquals(expectedPrices, actualPrices);

        return this;
    }

    public InventorySteps verifySortByPriceDescending() {
        List<Double> actualPrices = page.getAllProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted(Comparator.reverseOrder()).toList();
        Assertions.assertEquals(expectedPrices, actualPrices);

        return this;
    }
}
