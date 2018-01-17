package com.build.qa.build.selenium.pageobjects.homepage;

import com.build.qa.build.selenium.pageobjects.AppiumBasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class AppiumHomePage extends AppiumBasePage {

    private By buildSkip;

    private By buildShop;
    private By buildClearance;
    private By buildDeals;
    private By buildRecentlyViewed;

    private By buildAccount;
    private By buildLists;
    private By buildCart;
    private By buildHelp;
    private By buildShopBottom;

    public AppiumHomePage(AppiumDriver driver, Wait<AppiumDriver> wait) {
        super(driver, wait);
        buildSkip = By.xpath("//*[@text='Skip ']");

        buildShop = By.xpath("//*[@text='Shop']");
        buildClearance = By.xpath("//*[@text='Clearance']");
        buildDeals = By.xpath("//*[@text='Deals']");
        buildRecentlyViewed = By.xpath("//*[@text='Recently Viewed']");

        buildAccount = By.xpath("//*[@text='Account']");
        buildShopBottom = By.xpath("//*[@text='Shop'][2]");
        buildLists = By.xpath("//*[@text='Lists']");
        buildHelp = By.xpath("//*[@text='Help']");
        buildCart = By.xpath("//*[@text='Cart']");
    }

    public boolean onBuildStart() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(buildSkip)) != null;
    }

    public boolean onBuildHome() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(buildAccount)) != null;
    }

    public boolean homepageShopExists() {
        return  buildShop != null  ?  true: false;
    }

    public boolean homepageClearanceExists() {
        return  buildClearance != null  ?  true: false;
    }

    public boolean homepageDealsExists() {
        return  buildDeals != null  ?  true: false;
    }

    public boolean homepageRecentlyViewedExists() {
        return  buildRecentlyViewed != null  ?  true: false;
    }

    public boolean homepageShopBottomExists() {
        return  buildShopBottom != null  ?  true: false;
    }

    public boolean homepageCartExists() {
        return  buildCart != null  ?  true: false;
    }

    public boolean homepageListsExists() {
        return  buildLists != null  ?  true: false;
    }

    public boolean homepageHelpExists() {
        return  buildHelp != null  ?  true: false;
    }

    public boolean homepageAccountExists() {
        return  buildAccount != null  ?  true: false;
    }

}
