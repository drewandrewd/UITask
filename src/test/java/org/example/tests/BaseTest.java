package org.example.tests;

import io.qameta.allure.junit5.AllureJunit5;
import org.example.utilities.ParameterProvider;
import org.example.utilities.WaitHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitHelper waitHelper;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("explicit.wait.time"))));
        waitHelper = new WaitHelper(wait);
        driver.get(ParameterProvider.get("base.url"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
