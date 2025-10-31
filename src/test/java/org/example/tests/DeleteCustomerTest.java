package org.example.tests;

import org.example.pages.ListCustomerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeleteCustomerTest extends BaseTest {

    @Test
    void deleteCustomerCheck() {
        ListCustomerPage listCustomerPage = new ListCustomerPage(driver, waitHelper);
        listCustomerPage.clickCustomersTab();
        List<String> initialList = listCustomerPage.getFirstNameCells();
        Assertions.assertTrue(initialList.size() >= 3, "Less than 3");

        String mediumName = listCustomerPage.getMediumLengthOfFirstNameCell();
        Assertions.assertNotNull(mediumName, "Not found");

        listCustomerPage.deleteCustomerByName(mediumName);
        List<String> afterDeletingList = listCustomerPage.getFirstNameCells();

        Assertions.assertEquals(initialList.size() - 1, afterDeletingList.size(), "Not found");
        Assertions.assertFalse(afterDeletingList.contains(mediumName), "Medium name is still here");
        initialList.stream()
                .filter(name -> !name.equals(mediumName))
                .forEach(name ->
                        Assertions.assertTrue(afterDeletingList.contains(name),
                                "Name " + name + " is still there")
                );
    }
}
