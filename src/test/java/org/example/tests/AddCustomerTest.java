package org.example.tests;


import org.example.pages.AddCustomerPage;
import org.example.utilities.FieldGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AddCustomerTest extends BaseTest {

    @Test
    void registrationCustomerCheck() {
        FieldGenerator fieldGenerator = new FieldGenerator();
        new AddCustomerPage(driver, waitHelper)
                .clickAddCustomerTab()
                .sendFirstName(fieldGenerator.getFirstName())
                .sendLastName(fieldGenerator.getLastName())
                .sendPostCode(fieldGenerator.getPostCode())
                .clickAddCustomerBtn();

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        Assert.assertEquals(alertText.substring(0, alertText.length() - 1),
                "Customer added successfully with customer id :", "ошибка!");
        alert.accept();
    }
}
