package testScripts;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import base.TestBase;
import commonUtils.Utilitys;
import pages.CartPage;
import pages.HomePage;

public class PlaceOrderTest extends TestBase {
	
	@BeforeTest
	public void setup() {
		initialize();
	}
	
    @Test(priority=1)
    public void login(){
    	HomePage home = new HomePage();
    	home.toLogin("RajaM", "123456");
    	boolean isDisp = driver.findElement(By.xpath("//a[text()='Welcome RajaM']")).isDisplayed();
    	Assert.assertTrue(isDisp);
    }
    
    @Test(priority=2,dataProvider = "items")
    public void addItems(String category, String itemName){
    	HomePage add_Items = new HomePage();
    	add_Items.addItem(category, itemName);
    }
    
    @Test(priority=3)
    public void deleteItem() throws InterruptedException {
    	CartPage delete_Item = new CartPage();
    	delete_Item.deleteItem();
		Assert.assertNotEquals("valueBefore", "valueAfter");
    }
    
    @Test(dependsOnMethods="deleteItem")
    public void purchaseItems() throws InterruptedException {
    	CartPage purchase_Items = new CartPage();
    	purchase_Items.purchaseItem("Raja", "India", "Salem", "76875785", "April", "2024");
		boolean isDisp = driver.findElement(By.xpath("//h2[(text()='Thank you for your purchase!')]")).isDisplayed();
		Assert.assertTrue(isDisp);
    }
    
    @DataProvider(name="items")								
    public Object[][] getData() throws CsvValidationException, IOException{
  	  String path = System.getProperty("user.dir")+"//src//test//resources//configFiles//items.csv";
  	  String[] cols;									
  	  CSVReader reader = new CSVReader(new FileReader(path));
  	  ArrayList<Object> dataList = new ArrayList<Object>();
  	  while((cols = reader.readNext())!=null){
  		  Object[] record = {cols[0],cols[1]};
  		  dataList.add(record);
  	  }
  	  return dataList.toArray(new Object[dataList.size()][]);
    }
    
     @AfterMethod()
	 public void tearDown(ITestResult result) {
		 if(ITestResult.FAILURE == result.getStatus()) {
			 extentTest.log(Status.FAIL, result.getThrowable().getMessage());
			 String strPath = Utilitys.getScreenShotPath(driver);
			 extentTest.addScreenCaptureFromPath(strPath);
		 }
	 }

     @AfterTest
     public void closeApp() {
    	 driver.close();
    	 reports.flush();
     }
}