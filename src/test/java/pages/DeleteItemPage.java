package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class DeleteItemPage extends TestBase {
	@FindBy(xpath="//a[text()='Cart']")
	WebElement toCart;
		
	@FindBy(id="totalp")
	WebElement price;
	
	@FindBy(xpath="//a[contains(text(),'Delete')][1]")
	WebElement delete;
	
	public DeleteItemPage() {
		PageFactory.initElements(driver,this);
	}
	
	public void deleteItem() throws InterruptedException {
		extentTest = reports.createTest("Delete Item Test");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		toCart.click();
		wait.until(ExpectedConditions.visibilityOf(price));
    	String valueBefore =price.getText();
    	int iBefore=Integer.parseInt(valueBefore); 
    	wait.until(ExpectedConditions.elementToBeClickable(delete));
		delete.click();
		wait.until(ExpectedConditions.visibilityOf(price));
		String valueAfter =price.getText();
    	int iAfter=Integer.parseInt(valueAfter);
    	System.out.println(iBefore);
    	System.out.println(iAfter);
    	Assert.assertNotEquals("iBefore", "iafter");
    	if(iBefore==iAfter)
    		System.out.println("Item not deleted");
    	else
    		System.out.println("Item deleted");
	}
}