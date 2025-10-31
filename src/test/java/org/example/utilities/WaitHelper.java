package org.example.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

    WebDriverWait wait;

    public WaitHelper(WebDriverWait wait) {
        this.wait = wait;
    }

    public void untilToBeSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void untilToBeVisible(WebElement element) {
        wait.until(driver -> element.isDisplayed());
    }
}
