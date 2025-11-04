package org.example.tests;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.pages.AddCustomerPage;
import org.example.utilities.FieldGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Customers")
@Feature("Add Customer")
@Owner("Andrei")
@Severity(SeverityLevel.CRITICAL)
@Story("Create new customer and verify success alert")
@DisplayName("Add Customer Test")
public class AddCustomerTest extends BaseTest {

    @Test
    void registrationCustomerCheck() {
        FieldGenerator fieldGenerator = new FieldGenerator();
       AddCustomerPage page = new AddCustomerPage(driver, waitHelper);
       page.clickAddCustomerTab()
               .sendFirstName(fieldGenerator.getFirstName())
               .sendLastName(fieldGenerator.getLastName())
               .sendPostCode(fieldGenerator.getPostCode())
                .clickAddCustomerBtn();

        String alertText = page.getAlertText();
        Assertions.assertTrue(alertText.contains("Customer added successfully with customer id :"),
                "ошибка!");
    }
}
