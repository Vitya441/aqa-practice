package com.modsen.ui.step;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.page.InventoryPage;
import io.qameta.allure.Step;
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

    @Step("Добавление товара в корзину")
    public InventorySteps addItemToCart() {
        page.clickAddToCart();
        return this;
    }

    @Step("Переход в корзину")
    public CartSteps onCartPage() {
        return new CartSteps(driver);
    }

    @Step("Клик по иконке корзины")
    public InventorySteps clickShoppingCartLink() {
        page.clickShoppingCartLink();
        return this;
    }

    @Step("Добавление 3-х случайных товаров в корзину")
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

    @Step("Сортировка товаров по значению: {sortValue}")
    public InventorySteps sortItems(String sortValue) {
        page.selectSortOption(sortValue);
        return this;
    }

    @Step("Получение модели ProductModel по имени: {productName}")
    public ProductModel getProductDataByName(String productName) {
        return page.getProductDataByName(productName);
    }

    @Step("Клик по имени товара")
    public InventorySteps openProductByName(String productName) {
        page.openProductByName(productName);
        return this;
    }

    @Step("Переход на страницу конкретного товара")
    public InventoryDetailsSteps onInventoryDetailsPage() {
        return new InventoryDetailsSteps(driver);
    }

    @Step("Проверка текста кнопки. Ожидается: {expected}")
    public InventorySteps verifyButtonText(String expected) {
        Assertions.assertEquals(expected, page.getButtonText());
        return this;
    }

    @Step("Проверка счетчика корзины. Ожидается: {expected}")
    public InventorySteps verifyBudgeCount(String expected) {
        Assertions.assertEquals(expected, page.getCardBadgeValue());
        return this;
    }

    @Step("Проверка, что товар '{productName}' доступен для добавления в корзину")
    public InventorySteps verifyProductIsAvailable(String productName) {
        String actualButtonText = page.getButtonTextByProductName(productName);
        Assertions.assertEquals("Add to cart", actualButtonText);

        return this;
    }

    @Step("Проверка отображения заголовка страницы товаров")
    public InventorySteps verifyInventoryPageVisible() {
        String actualTitle = page.getInventoryTitleText();
        Assertions.assertEquals("Products", actualTitle);

        return this;
    }

    @Step("Проверка сортировки имен (A-Z)")
    public InventorySteps verifySortByNameAscending() {
        List<String> actualNames = page.getAllProductNames();
        List<String> expectedNames = actualNames.stream().sorted().toList();
        Assertions.assertEquals(expectedNames, actualNames);

        return this;
    }

    @Step("Проверка сортировки имен (Z-A)")
    public InventorySteps verifySortByNameDescending() {
        List<String> actualNames = page.getAllProductNames();
        List<String> expectedNames = actualNames.stream().sorted(Comparator.reverseOrder()).toList();
        Assertions.assertEquals(expectedNames, actualNames);

        return this;
    }

    @Step("Проверка сортировки цен (Low to High)")
    public InventorySteps verifySortByPriceAscending() {
        List<Double> actualPrices = page.getAllProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted().toList();
        Assertions.assertEquals(expectedPrices, actualPrices);

        return this;
    }

    @Step("Проверка сортировки цен (High to Low)")
    public InventorySteps verifySortByPriceDescending() {
        List<Double> actualPrices = page.getAllProductPrices();
        List<Double> expectedPrices = actualPrices.stream().sorted(Comparator.reverseOrder()).toList();
        Assertions.assertEquals(expectedPrices, actualPrices);

        return this;
    }
}
