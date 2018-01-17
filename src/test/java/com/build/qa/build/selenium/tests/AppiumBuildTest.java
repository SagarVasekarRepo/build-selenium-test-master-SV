package com.build.qa.build.selenium.tests;

import com.build.qa.build.selenium.framework.AppiumBaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.AppiumHomePage;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class AppiumBuildTest extends AppiumBaseFramework {
    /**
     * Extremely basic test that outlines some basic
     * functionality and page objects as well as assertJ
     *
     * NOTE: Some of the locators could be more refined so that
     * they would always point to exact element and our tests are
     * stable and does not need frequent updates after App changes.
     * We can add more tests here as well.
     */
    @Test
    public void navigateToHomePage() {
        driver.get(getConfiguration("HOMEPAGE"));
        AppiumHomePage homePage = new AppiumHomePage(driver, wait);

        softly.assertThat(homePage.onBuildStart())
                .as("App Login screen should open.")
                .isTrue();

        driver.findElementByXPath("//*[@text='Skip ']").click();

        softly.assertThat(homePage.onBuildHome())
                .as("App home page should open.")
                .isTrue();

        // Test all tabs at the top are present
        assertThat(homePage.homepageShopExists()).isTrue();
        assertThat(homePage.homepageClearanceExists()).isTrue();
        assertThat(homePage.homepageDealsExists()).isTrue();
        assertThat(homePage.homepageRecentlyViewedExists()).isTrue();

        // Test all tabs at the bottom are present
        assertThat(homePage.homepageShopBottomExists()).isTrue();
        assertThat(homePage.homepageCartExists()).isTrue();
        assertThat(homePage.homepageListsExists()).isTrue();
        assertThat(homePage.homepageHelpExists()).isTrue();
        assertThat(homePage.homepageAccountExists()).isTrue();

    }
}

