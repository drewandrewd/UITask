package org.example.tests;

import io.qameta.allure.Description;
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
@Feature("List and sorting")
@Owner("Andrei")
public class SortCustomerTest extends BaseTest {

    @Story("Sort by first name")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Check ascending and descending orders")
    @Description("Verify that customer list sorting by first name works correctly")
    @Test
    void sortCustomerByFirstNameCheck() {
        ListCustomerPage listCustomerPage = new ListCustomerPage(driver, waitHelper);
                listCustomerPage
                .clickCustomersTab()
                .clickFirstNameColumn();
        List<String> initialList = listCustomerPage.getFirstNameCells();
        Assertions.assertTrue(listCustomerPage.isSortedDesc(),
                "First name column is not descending");
        listCustomerPage.clickFirstNameColumn();
        Assertions.assertTrue(listCustomerPage.isSortedAsc(),
                "First name column is not ascending");
        Assertions.assertEquals(initialList.size(), listCustomerPage.getFirstNameCells().size(),
                "The length is not the same");
    }
}
