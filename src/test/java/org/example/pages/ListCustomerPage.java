package org.example.pages;

import io.qameta.allure.Step;
import org.example.utilities.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListCustomerPage extends BasePage {

    @FindBy(css = "button[ng-click='showCust()']")
    private WebElement customersTab;

    @FindBy(css = "table.table thead a[ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    private WebElement firstNameColumn;

    @FindBy(css = "table.table tbody tr td:first-child")
    private List<WebElement> firstNameCells;

    public ListCustomerPage(WebDriver driver, WaitHelper waitHelper) {
        super(driver, waitHelper);
        PageFactory.initElements(driver, this);
    }

    @Step("Open Customers tab")
    public ListCustomerPage clickCustomersTab() {
        waitHelper.untilToBeVisible(this.customersTab);
        this.customersTab.click();
        return this;
    }

    @Step("Click First Name column")
    public ListCustomerPage clickFirstNameColumn() {
        waitHelper.untilToBeVisible(this.firstNameColumn);
        this.firstNameColumn.click();
        return this;
    }

    @Step("Get list of first names")
    public List<String> getFirstNameCells() {
        waitHelper.untilToBeVisible(this.firstNameColumn);
        return firstNameCells.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Check if names are sorted ascending")
    public boolean isSortedAsc() {
        List<String> firstNames = getFirstNameCells();
        List<String>  sorted = new ArrayList<>(firstNames);
        sorted.sort(String::compareToIgnoreCase);
        return firstNames.equals(sorted);
    }

    @Step("Check if names are sorted descending")
    public boolean isSortedDesc() {
        List<String> firstNames = getFirstNameCells();
        List<String>  sorted = new ArrayList<>(firstNames);
        sorted.sort((a, b) -> b.compareToIgnoreCase(a));
        return firstNames.equals(sorted);
    }

    @Step("Find name closest to average length")
    public String getMediumLengthOfFirstNameCell() {
        List<String> firstNames = getFirstNameCells();
        if (firstNames.isEmpty()) return null;
        double average = firstNames.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);

        String closestName = firstNames.stream()
                .min(Comparator.comparingDouble(n -> Math.abs(n.length() - average)))
                .orElse(null);
        return closestName;
    }

    @Step("Find delete button by first name")
    public WebElement getDeleteBtn(String name) {
        return driver.findElement(By.xpath(
                "//tr[td[normalize-space(text())='" + name + "']]//button[contains(text(),'Delete')]"));
    }

    @Step("delete customer by first name")
    public boolean deleteCustomerByName(String name) {
        List<String> before = getFirstNameCells();
        int beforeSize = before.size();

        WebElement button = getDeleteBtn(name);
        button.click();
        waitHelper.untilToBeDeleted(beforeSize);
        List<String> after = getFirstNameCells();

        boolean sizeOk = after.size() == before.size() - 1;
        boolean removedOk = !after.contains(name);
        boolean othersOk = before.stream()
                .filter(n -> !n.equals(name))
                .allMatch(after::contains);
        return sizeOk && removedOk && othersOk;
    }
}
