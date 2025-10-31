package org.example.pages;

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

    public ListCustomerPage clickCustomersTab() {
        waitHelper.untilToBeVisible(this.customersTab);
        this.customersTab.click();
        return this;
    }

    public ListCustomerPage clickFirstNameColumn() {
        waitHelper.untilToBeVisible(this.firstNameColumn);
        this.firstNameColumn.click();
        return this;
    }

    public List<String> getFirstNameCells() {
        waitHelper.untilToBeVisible(this.firstNameColumn);
        return firstNameCells.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isSortedAsc() {
        List<String> firstNames = getFirstNameCells();
        List<String>  sorted = new ArrayList<>(firstNames);
        sorted.sort(String::compareToIgnoreCase);
        return firstNames.equals(sorted);
    }

    public boolean isSortedDesc() {
        List<String> firstNames = getFirstNameCells();
        List<String>  sorted = new ArrayList<>(firstNames);
        sorted.sort((a, b) -> b.compareToIgnoreCase(a));
        return firstNames.equals(sorted);
    }

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

    public WebElement getDeleteBtn(String name) {
        return driver.findElement(By.xpath(
                "//tr[td[normalize-space(text())='" + name + "']]//button[contains(text(),'Delete')]"));
    }

    public ListCustomerPage deleteCustomerByName(String name) {
        WebElement button = getDeleteBtn(name);
        button.click();
        return this;
    }
}
