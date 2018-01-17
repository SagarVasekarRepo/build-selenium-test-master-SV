package com.build.qa.build.selenium.pageobjects;

import com.build.qa.build.selenium.framework.AppiumBaseFramework;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public abstract class AppiumBasePage extends AppiumBaseFramework {

    public AppiumBasePage(AppiumDriver driver, Wait<AppiumDriver> wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }
}
