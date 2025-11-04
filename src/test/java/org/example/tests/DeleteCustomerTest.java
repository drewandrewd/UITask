package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.example.pages.ListCustomerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Epic("Customers")
@Feature("Delete Customer")
@Owner("Andrei")
@Severity(SeverityLevel.CRITICAL)
@Story("Delete customer with name length closest to average")
@DisplayName("Delete Customer Test")
public class DeleteCustomerTest extends BaseTest {

    @Test
    void deleteCustomerCheck() {
        ListCustomerPage listCustomerPage = new ListCustomerPage(driver, waitHelper);
        listCustomerPage.clickCustomersTab();
        List<String> initialList = listCustomerPage.getFirstNameCells();
        Assertions.assertTrue(initialList.size() >= 3, "Less than 3");

        String mediumName = listCustomerPage.getMediumLengthOfFirstNameCell();
        Assertions.assertNotNull(mediumName, "Not found");

        Assertions.assertTrue(listCustomerPage.deleteCustomerByName(mediumName), "Delete failed");

    }
}
