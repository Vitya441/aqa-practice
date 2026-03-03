package com.modsen.ui.step;

import com.modsen.ui.page.CartPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartSteps {

    private CartPage page;
    private WebDriver driver;

    public CartSteps(WebDriver driver) {
        this.driver = driver;
        this.page = new CartPage(driver);
    }

    @Step("Удаление первого попавшегося товара")
    public CartSteps removeItem() {
        page.clickRemoveButton();
        return this;
    }

    @Step("Удаление товара по имени: {productName}")
    public CartSteps removeItemByName(String productName) {
        page.clickRemoveButtonByProductName(productName);
        return this;
    }

    @Step("Нажатие кнопки 'Continue Shopping'")
    public CartSteps clickContinueShopping() {
        page.clickContinueShoppingButton();
        return this;
    }

    @Step("Переход на страницу со списком товаров")
    public InventorySteps onInventoryPage() {
        return new InventorySteps(driver);
    }

    @Step("Нажатие кнопки 'Checkout'")
    public CartSteps clickCheckoutButton() {
        page.clickCheckoutButton();
        return this;
    }

    @Step("Переход на страницу для заполнения формы")
    public CheckoutInformationSteps onCheckoutInformationPage() {
        return new CheckoutInformationSteps(driver);
    }

    @Step("Получение списка имен товаров в корзине")
    public List<String> getItemNamesInCart() {
        return page.getCartItems().stream()
                .map(item -> page.getItemName(item))
                .toList();
    }

    @Step("Проверка, что корзина пуста")
    public CartSteps verifyCartIsEmpty() {
        Assertions.assertEquals("0", page.getCardBadgeValue());
        return this;
    }

    @Step("Проверка наличия 3-х товаров в корзине")
    public CartSteps verify3ItemsPresent() {
        Assertions.assertEquals(3, page.getCartItemsCount());
        return this;
    }

    @Step("Проверка, что в корзине осталось 2 товара")
    public CartSteps verifyTwoItemsLeft() {
        Assertions.assertEquals(2, page.getCartItemsCount());
        return this;
    }

    @Step("Проверка детальной информации (цена, кнопка удаления) для всех товаров")
    public CartSteps verifyAllItemsDetails() {
        for (WebElement item : page.getCartItems()) {
            Assertions.assertTrue(page.isPriceAndRemoveButtonPresent(item),
                    "Product: " + page.getItemName(item) + " has not price or 'Remove' button");
        }

        return this;
    }
}
