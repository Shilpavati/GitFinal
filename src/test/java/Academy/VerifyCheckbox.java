package Academy;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import resources.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CheckboxesPage;
import pageObjects.LandingPage;

public class VerifyCheckbox extends base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void Initialize() throws IOException {
		driver = InitializeDriver();
		System.out.println("Line 1 added");
		System.out.println("Line 2 added");
		System.out.println("Line 3 added");
		System.out.println("I am the new architect");
	}

	@Test
	public void newArchitect() {
		System.out.println("Lets add some new code");

	}
	@Test
	public void newArchitect1() {
		System.out.println("Lets add some new code");

	}

	@Test
	public void validatecheckbox() {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		LandingPage l = new LandingPage(driver);

		l.getCheckBoxes().click();
		log.info("Navigated to Checkbox Page");
		CheckboxesPage chk = new CheckboxesPage(driver);

		for (WebElement cb : chk.getCheckbox()) {
			cb.click();
		}

		System.out.println("Other checkbox is clicked.");
		log.info("Other checkbox is clicked.");
	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}