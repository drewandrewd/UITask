package org.example.pages;

import org.example.utilities.WaitHelper;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    WebDriver driver;
    WaitHelper waitHelper;

    public BasePage(WebDriver driver, WaitHelper waitHelper) {
        this.driver = driver;
        this.waitHelper = waitHelper;
    }
}
