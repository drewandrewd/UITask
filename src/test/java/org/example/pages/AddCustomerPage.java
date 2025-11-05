package org.example.pages;

import io.qameta.allure.Step;
import org.example.utilities.WaitHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCustomerPage extends BasePage {

    @FindBy(css = "button[ng-click='addCust()']")
    private WebElement addCustomerTab;

    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstName;

    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastName;

    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postCode;

    @FindBy(css = "button.btn.btn-default")
    private WebElement addCustomerBtn;

    public AddCustomerPage(WebDriver driver, WaitHelper waitHelper) {
        super(driver, waitHelper);
        PageFactory.initElements(driver, this);
    }

    @Step("Open Add Customer tab")
    public AddCustomerPage clickAddCustomerTab() {
        waitHelper.untilToBeVisible(this.addCustomerTab);
        this.addCustomerTab.click();
        return this;
    }

    @Step("Enter first name")
    public AddCustomerPage sendFirstName(String firstName) {
        waitHelper.untilToBeVisible(this.firstName);
        this.firstName.sendKeys(firstName);
        return this;
    }

    @Step("Enter last name")
    public AddCustomerPage sendLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    @Step("Enter post code")
    public AddCustomerPage sendPostCode(String postCode) {
        this.postCode.sendKeys(postCode);
        return this;
    }

    @Step("Click Add customer button")
    public AddCustomerPage clickAddCustomerBtn() {
        this.addCustomerBtn.click();
        return this;
    }

    @Step("Get alert message text")
    public String getAlertText() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText.substring(0, alertText.length() - 1);
    }
}
