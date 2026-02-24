package com.modsen.ui.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        switch (browser) {
            case "chrome" -> {
                final Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

                final ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(options);
            }
            case "edge" -> {
                driver = new EdgeDriver();
            }
            default -> {
                throw new RuntimeException("Browser " + browser + " is not supported");
            }
        }
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
