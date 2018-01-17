package com.build.qa.build.selenium.pageobjects.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

import java.util.List;

public class HomePage extends BasePage {
	
	private By buildThemeBody;
	private By popUp;
	private By searchInput;
	private By heading;
	private By searchResultsNotation;
	private By searchResults;
	private By addtoCart;
	private By cartItemsTitle;
	private By proceedToCart;
	private By cartItemsQuantity;
	private By justAdded;
	private By justAddedItem;
	private By emailCart;
	private By continueWithoutItems;
	private By yourName;
	private By yourEmail;
	private By recipientName;
	private By recipientEmail;
	private By quoteMessage;
	private By emailCartPopup;
	private By emailSentMessage;
	private By headerMenuBathroom;
	private By subMenuBathroomFaucets;
	private By colorFilterChrome;
	private By brandFilterDelta;
	private By totalResultsCount;
	private By selectedFilters;

	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
		popUp = By.id("newsletterPopupEmail");
		searchInput = By.id("search_txt");
		heading = By.id("heading");
		searchResultsNotation = By.id("results-per-page");
		searchResults = By.className("product-tile");
		addtoCart = By.xpath("id(\"configure-product-wrap\")/button[1]");
		continueWithoutItems = By.id("continue-without-items");
		cartItemsTitle = By.xpath("//tbody/tr/td/a");
		cartItemsQuantity = By.xpath("//tbody/tr/td/div/div/input");
		proceedToCart = By.linkText("Proceed to Cart");
		justAdded =  By.xpath("//h3[text() ='Just Added to your Cart']");
		justAddedItem = By.linkText("Archer 19-5/8\" Undermount Bathroom Sink with Overflow");
		emailCart = By.xpath("//button[@title='Email your cart']");
		emailCartPopup = By.xpath("//button[contains(text(),'Email Cart')]");
		yourName = By.id("yourName");
		yourEmail = By.id("yourEmail");
		recipientName = By.id("recipientName");
		recipientEmail = By.id("recipientEmail");
		quoteMessage = By.id("quoteMessage");
		emailSentMessage = By.xpath("//li[contains(text(),'Cart Sent!')]");
		headerMenuBathroom = By.xpath("//li[@class='header-menu-style']/a[@data-tracking = 'nav:menu:category:Bathroom']");
		subMenuBathroomFaucets = By.linkText("Bathroom Faucets");
		colorFilterChrome = By.xpath("//label[@data-facet-group='Colors' and @data-facet-value= 'Chromes']/input");
		brandFilterDelta = By.xpath("//label[@data-facet-group='Brand' and @data-facet-value= 'Delta']/input");
		totalResultsCount = By.xpath("//span[@class='js-num-results']");
		selectedFilters = By.xpath("//div[@class= 'limit-facet-item']/span[2]");
	}

	public boolean onBuildTheme() {
		wait.until(ExpectedConditions.elementToBeClickable(popUp));
		driver.findElement(popUp).sendKeys(Keys.ESCAPE);
		driver.navigate().refresh();
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}

	public boolean onSearchResults() {
		wait.until(ExpectedConditions.elementToBeClickable(popUp));
		driver.findElement(popUp).sendKeys(Keys.ESCAPE);
		driver.navigate().refresh();
		return wait.until(ExpectedConditions.presenceOfElementLocated(searchResultsNotation)) != null;
	}

	public WebElement getSearchTextBox(){
		wait.until(ExpectedConditions.elementToBeClickable(searchInput));
		return driver.findElement(searchInput);
	}

	public WebElement getHeading(){
		wait.until(ExpectedConditions.elementToBeClickable(heading));
		return driver.findElement(heading);
	}

	public List<WebElement> getSearchResults(){
		wait.until(ExpectedConditions.elementToBeClickable(searchResults));
		return driver.findElements(searchResults);
	}

	public WebElement getAddtoCart(){
		wait.until(ExpectedConditions.elementToBeClickable(addtoCart));
			return driver.findElement(addtoCart);
	}

	public List<WebElement> getCartItemsTitle(){
		wait.until(ExpectedConditions.elementToBeClickable(cartItemsTitle));
		return driver.findElements(cartItemsTitle);
	}

	public WebElement getCartItemsQuantity(){
		wait.until(ExpectedConditions.elementToBeClickable(cartItemsQuantity));
		return driver.findElement(cartItemsQuantity);
	}

	public WebElement getProceedToCart(){
		wait.until(ExpectedConditions.elementToBeClickable(proceedToCart));
		return driver.findElement(proceedToCart);
	}

	public WebElement getJustAdded(){
		wait.until(ExpectedConditions.elementToBeClickable(justAdded));
		return driver.findElement(justAdded);
	}

	public WebElement getJustAddedItem(){
		wait.until(ExpectedConditions.elementToBeClickable(justAddedItem));
		return driver.findElement(justAddedItem);
	}

	public WebElement getEmailCart(){
		wait.until(ExpectedConditions.elementToBeClickable(emailCart));
		return driver.findElement(emailCart);
	}

	public WebElement getContinueWithoutItems(){
		wait.until(ExpectedConditions.elementToBeClickable(continueWithoutItems));
		return driver.findElement(continueWithoutItems);
	}

	public WebElement getYourName(){
		wait.until(ExpectedConditions.elementToBeClickable(yourName));
		return driver.findElement(yourName);
	}

	public WebElement getYourEmail(){
		wait.until(ExpectedConditions.elementToBeClickable(yourEmail));
		return driver.findElement(yourEmail);
	}

	public WebElement getRecipientName(){
		wait.until(ExpectedConditions.elementToBeClickable(recipientName));
		return driver.findElement(recipientName);
	}

	public WebElement getRecipientEmail(){
		wait.until(ExpectedConditions.elementToBeClickable(recipientEmail));
		return driver.findElement(recipientEmail);
	}

	public WebElement getEmailCartPopup(){
		return driver.findElement(emailCartPopup);
	}

	public WebElement getQuoteMessage(){
		wait.until(ExpectedConditions.elementToBeClickable(quoteMessage));
		return driver.findElement(quoteMessage);
	}

	public WebElement getEmailSentMessage(){
		wait.until(ExpectedConditions.elementToBeClickable(emailSentMessage));
		return driver.findElement(emailSentMessage);
	}

	public WebElement getHeaderMenuBathroom(){
		wait.until(ExpectedConditions.elementToBeClickable(headerMenuBathroom));
		return driver.findElement(headerMenuBathroom);
	}

	public WebElement getSubMenuBathroomFaucets(){
		wait.until(ExpectedConditions.elementToBeClickable(subMenuBathroomFaucets));
		return driver.findElement(subMenuBathroomFaucets);
	}

	public WebElement getColorFilterChrome(){
		wait.until(ExpectedConditions.elementToBeClickable(colorFilterChrome));
		return driver.findElement(colorFilterChrome);
	}

	public WebElement getBrandFilterDelta(){
		wait.until(ExpectedConditions.elementToBeClickable(brandFilterDelta));
		return driver.findElement(brandFilterDelta);
	}

	public List<WebElement> getSelectedFilters(){
		wait.until(ExpectedConditions.elementToBeClickable(selectedFilters));
		return driver.findElements(selectedFilters);
	}

	public WebElement getTotalResultsCount(){
		wait.until(ExpectedConditions.elementToBeClickable(totalResultsCount));
		return driver.findElement(totalResultsCount);
	}

}