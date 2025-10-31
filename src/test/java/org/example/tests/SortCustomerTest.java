package org.example.tests;

import org.example.pages.ListCustomerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortCustomerTest extends BaseTest {

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
