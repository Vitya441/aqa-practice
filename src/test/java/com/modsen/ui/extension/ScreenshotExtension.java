package com.modsen.ui.extension;

import com.modsen.ui.test.BaseTest;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            Object testInstance = context.getRequiredTestInstance();
            WebDriver driver = ((BaseTest) testInstance).getDriver();

            if (driver != null) {
                String testName = context.getDisplayName();
                captureScreenshotLocal(driver, testName);
                captureScreenshotAllure(driver);
            }
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    private byte[] captureScreenshotAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void captureScreenshotLocal(WebDriver driver, String testName) {
        try {
            Path path = Paths.get("target/surefire-reports");
            Files.createDirectories(path);
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(path.resolve("screenshot-" + testName + ".png"), screenshot);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении локального скриншота: " + e.getMessage());
        }
    }
}
