package com.modsen.ui.step;

import com.modsen.ui.model.ProductModel;
import com.modsen.ui.page.InventoryDetailsPage;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class InventoryDetailsSteps {

    private WebDriver driver;
    private InventoryDetailsPage detailsPage;

    public InventoryDetailsSteps(WebDriver driver) {
        this.driver = driver;
        this.detailsPage = new InventoryDetailsPage(driver);
    }

    public InventoryDetailsSteps verifyProductData(ProductModel expectedData) {
        ProductModel actualData = detailsPage.getProductData();
        Assertions.assertThat(expectedData).isEqualTo(actualData);

        return this;
    }
}
