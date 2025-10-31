package org.example.pages;

import org.example.utilities.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public AddCustomerPage clickAddCustomerTab() {
        waitHelper.untilToBeVisible(this.addCustomerTab);
        this.addCustomerTab.click();
        return this;
    }

    public AddCustomerPage sendFirstName(String firstName) {
        waitHelper.untilToBeVisible(this.firstName);
        this.firstName.sendKeys(firstName);
        return this;
    }

    public AddCustomerPage sendLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public AddCustomerPage sendPostCode(String postCode) {
        this.postCode.sendKeys(postCode);
        return this;
    }

    public AddCustomerPage clickAddCustomerBtn() {
        this.addCustomerBtn.click();
        return this;
    }
}
