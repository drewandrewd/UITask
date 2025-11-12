package org.example.tests;

import io.qameta.allure.junit5.AllureJunit5;
import org.example.utilities.ParameterProvider;
import org.example.utilities.WaitHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

@ExtendWith(AllureJunit5.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitHelper waitHelper;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        String url = System.getenv("SELENIUM_URL");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new RemoteWebDriver(new java.net.URL(url), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ParameterProvider.get("explicit.wait.time"))));
        waitHelper = new WaitHelper(wait);
        driver.get(ParameterProvider.get("base.url"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
