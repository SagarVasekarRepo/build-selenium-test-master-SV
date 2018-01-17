package com.build.qa.build.selenium.tests;

import org.junit.Test;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class BuildTest extends BaseFramework { 
	
	/** 
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Test
	public void navigateToHomePage() { 
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);
		
		softly.assertThat(homePage.onBuildTheme())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/** 
	 * Search for the Quoizel MY1613 from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product title
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {

		String searchString = "Quoizel MY1613";
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onBuildTheme())
				.as("The website should load up with the Build.com desktop theme.")
				.isTrue();

		//clear text
		homePage.getSearchTextBox().sendKeys(Keys.ESCAPE);
		//enter search string
		homePage.getSearchTextBox().sendKeys(searchString);
		homePage.getSearchTextBox().sendKeys(Keys.ENTER);
		//verify page title
		assertThat(homePage.getHeading().getText()).contains(searchString);
	}
	
	/** 
	 * Go to the Bathroom Sinks category directly (https://www.build.com/bathroom-sinks/c108504) 
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop() {
		HomePage homePage = new HomePage(driver, wait);
		driver.get("https://www.build.com/bathroom-sinks/c108504");
		softly.assertThat(homePage.onSearchResults())
				.as("The website should load up with the Build.com search results.")
				.isTrue();

		//click on second search result
		homePage.getSearchResults().get(1).click();
		//add to cart
		new WebDriverWait(driver, 20);
		homePage.getAddtoCart().click();
        homePage.getAddtoCart().sendKeys(Keys.ENTER);
        if( homePage.getAddtoCart().isDisplayed())
            homePage.getAddtoCart().sendKeys(Keys.ENTER);

		//For recommended extra item, add that to cart too.
        if( homePage.getContinueWithoutItems().isDisplayed()) {
            new WebDriverWait(driver, 20);
            homePage.getContinueWithoutItems().click();
        }

		//Verify just added cart item
		assertThat(homePage.getJustAdded().getText()).contains("Just Added to your Cart");
		assertThat(homePage.getJustAddedItem().getText()).contains("Archer 19-5/8\" Undermount Bathroom Sink with Overflow");
	}
	
	/** 
	 * Add a product to the cart and email the cart to yourself, also to my email address: jgilmore+SeleniumTest@build.com
	 * Include this message in the "message field" of the email form: "This is {yourName}, sending you a cart from my automation!"
	 * @assert that the "Cart Sent" success message is displayed after emailing the cart
	 * @difficulty Medium-Hard
	 */
	@Test
	public void addProductToCartAndEmailIt() {

		HomePage homePage = new HomePage(driver, wait);
		addProductToCartFromCategoryDrop();

		//Click on proceed to cart
		homePage.getProceedToCart().click();

		//Verify cart details
		assertThat(homePage.getCartItemsTitle().get(1).getText()).contains("Kohler K-2355");
		assertThat(homePage.getCartItemsTitle().get(1).getText()).contains("Archer 19-5/8\" Undermount Bathroom Sink with Overflow");
		assertThat(homePage.getCartItemsQuantity().getAttribute("value")).matches(".");

        driver.navigate().refresh();

		//Click on Email Cart
		homePage.getEmailCart().click();
        homePage.getEmailCart().sendKeys(Keys.ENTER);
        if( homePage.getEmailCart().isDisplayed())
            homePage.getEmailCart().sendKeys(Keys.ENTER);

        //Enter details
        homePage.getYourName().sendKeys("Sagar");
        homePage.getYourEmail().sendKeys("Sagar.vasekar007@gmail.com");
        homePage.getRecipientName().sendKeys("Sagar");
        homePage.getRecipientEmail().sendKeys("Sagar.vasekar007@gmail.com");
        homePage.getQuoteMessage().sendKeys("This is Sagar, sending you a cart from my automation!");
        homePage.getRecipientName().sendKeys(Keys.ENTER);

        assertThat(homePage.getEmailSentMessage().getText()).contains("Cart Sent!");
	}
	
	/** 
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Theme=Modern
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
        driver.get(getConfiguration("HOMEPAGE"));
        HomePage homePage = new HomePage(driver, wait);

        softly.assertThat(homePage.onBuildTheme())
                .as("The website should load up with the Build.com desktop theme.")
                .isTrue();

        //Navigate to Bathroom Faucets
        homePage.getHeaderMenuBathroom().click();
        homePage.getSubMenuBathroomFaucets().click();

        int totalResults = Integer.parseInt(homePage.getTotalResultsCount().getText().replace(",",""));

        //Apply filters
        homePage.getBrandFilterDelta().click();
        assertThat(Integer.parseInt(homePage.getTotalResultsCount().getText().replace(",",""))).isLessThanOrEqualTo(totalResults);

        homePage.getColorFilterChrome().click();
        assertThat(Integer.parseInt(homePage.getTotalResultsCount().getText().replace(",",""))).isLessThanOrEqualTo(totalResults);

        assertThat(homePage.getSelectedFilters().get(0).getText()).contains("Delta");
        assertThat(homePage.getSelectedFilters().get(1).getText()).contains("Chrome");

	}
}
