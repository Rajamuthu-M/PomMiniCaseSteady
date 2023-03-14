package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(id="loginusername")
	WebElement usrName;
		
	@FindBy(id="loginpassword")
	WebElement usrPwd;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement logBtn;
	
	public LoginPage() {
		PageFactory.initElements(driver,this);
	}
	
	public AddItemPage toCategories(String userName, String userPewd){
		extentTest = reports.createTest("Login Test");
		usrName.sendKeys(userName);
		usrPwd.sendKeys(userPewd);
		logBtn.click();
		return new AddItemPage();
	}
}