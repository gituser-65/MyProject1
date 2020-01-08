package org.iit.mmp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PAT005_Tests {
static WebDriver driver;
@Test
public void validateInformationTab() 
{boolean result1,result2,result3;
SoftAssert sa= new SoftAssert();
	lanuchBrowser();
	
    result1=login("ria1","Ria12345");
    sa.assertTrue(result1);
     result2 = navigateToAmodule("Information");
     sa.assertTrue(result2);
    String actual=fetchInformationMessage();
    //actual=actual.trim();
    actual= actual.replaceAll("\\s", "");
    String expected="Manage My Patient (MMP) is a medical practice management solution that boosts"
    		+ "productivity by automating the day-to-day tasks that can slow an office manager down."
    		+ "Central delivers much more than medical billing software. Sure, it has the tools to help"
    		+ "generate cleaner claims and reduce denials, but our easy-to-use practice management"
    		+ "software also streamlines your workflow to deliver seamless handoffs across departments."
    	    +"Manage My Patient (MMP) becomes your practice’s command center, delivering robust,"
    	    +"real-time analytics through customizable reports and dashboards to ensure you know how your"
    	    +"business is performing on the metrics that matter most.";
    //expected=expected.trim();
    expected= expected.replaceAll("\\s", "");
    sa.assertEquals(actual,expected);
    sa.assertAll();
	}
	public void lanuchBrowser() 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\deepa\\Downloads\\chromedriver_win32_v79\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		driver.manage().window().maximize();
		driver.getTitle();
		 
	}
	public Boolean login(String userName,String password) 
	{Boolean result=false;
		System.out.println("inside login");
	driver.findElement(By.id("username")).sendKeys(userName);
	driver.findElement(By.id("password")).sendKeys(password);
	driver.findElement(By.name("submit")).click();
    String user=driver.findElement(By.tagName("h3")).getText();
    if (user.contains(userName)) {
    	result= true;
    }
	return result;
	}
		public boolean navigateToAmodule(String  moduleName) 
		{boolean result=false;
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	    result = driver.findElement(By.xpath("//h3[@class='panel-title']")).getText().contains("Information");

		return result;	
		}
		public String fetchInformationMessage()
		{
			String actual = driver.findElement(By.xpath("//div[@class='panel-title']")).getText();
			System.out.println("The Actual Value" + actual);
			return actual;
		}


}
