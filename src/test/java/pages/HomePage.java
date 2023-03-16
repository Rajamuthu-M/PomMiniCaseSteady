package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//a[text()='Home ']")
	WebElement homeBtn;
	
	@FindBy(xpath="//a[text()='Log in']")
	WebElement login;
	
	@FindBy(xpath="//a[text()='Cart']")
	WebElement cart;
	
	@FindBy(xpath="//a[text()='About us']")
	WebElement aboutUs;
	
	@FindBy(xpath="//a[text()='Contact']")
	WebElement contact;
	
	@FindBy(id="loginusername")
	WebElement usrName;
	
	@FindBy(id="loginpassword")
	WebElement usrPwd;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement logBtn;
	
	@FindBy(xpath="//a[text()='Add to cart']")
	WebElement addToCart;
	
	@FindBy(xpath="//input[@id='recipient-email']")
	WebElement recipientEmail;
	
	@FindBy(xpath="//input[@id='recipient-name']")
	WebElement recipientName;
	
	@FindBy(xpath="//textarea[@id='message-text']")
	WebElement message;
	
	@FindBy(xpath="//button[text()='Send message']")
	WebElement sendMessage;
	
	@FindBy(xpath="//button[@class='vjs-big-play-button']")
	WebElement vidPlay;
	
	@FindBy(xpath="(//button[@class='btn btn-secondary'])[4]")
	WebElement aboutClose;
	
	@FindBy(xpath="//input[@id='sign-username']")
	WebElement sUserName;
	
	@FindBy(xpath="//input[@id='sign-password']")
	WebElement sPassword;
	
	@FindBy(xpath="//button[text()='Sign up']")
	WebElement signUp;
	
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	public void contact() {
		contact.click();
		recipientEmail.sendKeys("rajam@gmail.com");
		recipientName.sendKeys("Raja M");
		message.sendKeys("Hai....");
		sendMessage.click();
	}
	
	public void aboutUs() {
		aboutUs.click();
		vidPlay.click();
		aboutClose.click();
	}
	
	public void toLogin(String userName, String userPwd){
		extentTest = reports.createTest("Login Test");
		login.click();
		usrName.sendKeys(userName);
		usrPwd.sendKeys(userPwd);
		logBtn.click();
	}
	
	public void signUp() {
		sUserName.sendKeys("RajanM");
		sPassword.sendKeys("RajanM123");
		signUp.click();
	}
	
	public void addItem(String category, String itemName){
		extentTest = reports.createTest("Add Item Test");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	homeBtn.click();
    	String currentCategory = "//a[text()='"+category+"']";
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(currentCategory)));
    	driver.findElement(By.xpath(currentCategory)).click();
    	String currentItem = "//a[text()='"+itemName+"']";
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(currentItem)));
    	driver.findElement(By.xpath(currentItem)).click();
    	WebElement btn = addToCart;
		wait.until(ExpectedConditions.elementToBeClickable(btn));
		btn.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(itemName+","+alert.getText());
		alert.accept();
		homeBtn.click();
	}
}