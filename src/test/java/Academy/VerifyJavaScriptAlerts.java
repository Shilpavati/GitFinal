package Academy;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.JavaScriptAlerts;
import pageObjects.LandingPage;
import resources.base;

public class VerifyJavaScriptAlerts extends base {

	public WebDriver driver;
	public String text;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@BeforeTest
	public void Initialize() throws IOException {
		driver = InitializeDriver();
		System.out.println("New line added");
	}

	@Test
	public void validateJavaScriptAlerts() throws InterruptedException {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		
		LandingPage l = new LandingPage(driver);
		l.getjsalertlink().click();
		log.info("Navigated to Java Script Alerts page");

		JavaScriptAlerts jsa = new JavaScriptAlerts(driver);
		
		jsa.getJSAlert().click();
		driver.switchTo().alert().accept();
		text=driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(text,"You successfully clicked an alert  ");
		log.info("Successfully validated Text Message");
		Thread.sleep(2000);
		
		jsa.getJSConfirm().click();
		driver.switchTo().alert().dismiss();
		text=driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(text,"You clicked: Cancel");
		
		Thread.sleep(2000);
		
		jsa.getJSConfirm().click();
		driver.switchTo().alert().accept();
		text=driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(text,"You clicked: Ok");
		Thread.sleep(2000);
		
		jsa.getJSPrompt().click();
		driver.switchTo().alert().sendKeys("Hello World!!!");
		driver.switchTo().alert().accept();		
		text=driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(text,"You entered: Hello World!!!");
		Thread.sleep(2000);
		
		jsa.getJSPrompt().click();
		driver.switchTo().alert().dismiss();		
		text="You entered: null";
		Assert.assertEquals(text,"You entered: null");
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void teardown(){
		driver.close();
	}

}
