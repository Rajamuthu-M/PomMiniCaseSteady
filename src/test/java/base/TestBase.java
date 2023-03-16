package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static Properties prop = null;
	public static WebDriver driver = null;
	public static ExtentReports reports;
	public static ExtentSparkReporter spark;
	public static ExtentTest extentTest;
	
	public TestBase(){
		String path = System.getProperty("user.dir")+"//src//test//resources//configFiles//config.properties";
		prop = new Properties();
		FileInputStream fin;
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\Report.html");
		reports.attachReporter(spark);
		try {
			fin = new FileInputStream(path);
			prop.load(fin);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialize() {
		String strBrowser = prop.getProperty("browser");
		if(strBrowser.equalsIgnoreCase("edge")){
			  WebDriverManager.edgedriver().setup();
			  driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.get(prop.getProperty("Url"));
	}
	
}