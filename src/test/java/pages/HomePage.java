package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//a[text()='Home ']")
	WebElement homeBtn;
		
	@FindBy(xpath="//a[text()='Log in']")
	WebElement login;
	
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	public LoginPage toLogin(){
		homeBtn.click();
		login.click();
		return new LoginPage();
	}
}