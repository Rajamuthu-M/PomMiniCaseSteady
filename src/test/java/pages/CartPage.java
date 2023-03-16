package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class CartPage extends TestBase {
	int priceBefore,priceAfter;
	String valueBefore,valueAfter;

	@FindBy(xpath="//a[text()='Add to cart']")
	WebElement addToCart;
	
	@FindBy(xpath="//a[text()='Home ']")
	WebElement home;
	
	@FindBy(xpath="//a[text()='Cart']")
	WebElement toCart;
	
	@FindBy(id="totalp")
	WebElement price;
	
	@FindBy(xpath="//a[contains(text(),'Delete')][1]")
	WebElement delete;
	
	@FindBy(xpath="//button[contains(text(),'Place Order')]")
	WebElement buy;
	
	@FindBy(xpath="//input[@id='name']")
	WebElement name;
	
	@FindBy(xpath="//input[@id='country']")
	WebElement country;
	
	@FindBy(xpath="//input[@id='city']")
	WebElement city;
		
	@FindBy(xpath="//input[@id='card']")
	WebElement card;
	
	@FindBy(xpath="//input[@id='month']")
	WebElement month;
	
	@FindBy(xpath="//input[@id='year']")
	WebElement year;
	
	@FindBy(xpath="//button[contains(text(),'Purchase')]")
	WebElement purchase;
	
	@FindBy(xpath="//h2[(text()='Thank you for your purchase!')]")
	WebElement confirm;
	
	@FindBy(xpath="//button[contains(text(),'OK')]")
	WebElement ok;
	
	public CartPage() {
		PageFactory.initElements(driver,this);
	}
	
	public void deleteItem() throws InterruptedException {
		extentTest = reports.createTest("Delete Item Test");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		toCart.click();
		wait.until(ExpectedConditions.visibilityOf(price));
    	String valueBefore =price.getText();
    	wait.until(ExpectedConditions.elementToBeClickable(delete));
		delete.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(price));
		String valueAfter =price.getText();
	}

	public void purchaseItem(String name1,String country1,String city1,String card1,String month1,String year1) throws InterruptedException {  	
    	extentTest = reports.createTest("Purchase Item Test");
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(buy));
		buy.click();
		name.sendKeys(name1);
		country.sendKeys(country1);
		city.sendKeys(city1);
		card.sendKeys(card1);
		month.sendKeys(month1);
		year.sendKeys(year1);
		wait.until(ExpectedConditions.elementToBeClickable(purchase));
    	purchase.click();
    	wait.until(ExpectedConditions.elementToBeClickable(ok));
    	ok.click();
    }
}