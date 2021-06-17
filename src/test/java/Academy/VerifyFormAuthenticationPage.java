package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.FormAuthenticationPage;
import pageObjects.SecurePage;
import resources.base;

public class VerifyFormAuthenticationPage extends base {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	public String validationtext;
	
	
	@Rule
	public ErrorCollector errCollector = new ErrorCollector();

	@BeforeTest
	public void Initialize() throws IOException {
		driver = InitializeDriver();
		
	}

	@DataProvider
	public Object[][] getdata() {
		// Object[][] data = new Object[1][3];
		Object[][] data = new Object[3][3];
		data[0][0] = "tomsmith";
		data[0][1] = "SuperSecretPassword!";
		data[0][2] = "User is allowed";
		data[1][0] = "xyz";
		data[1][1] = "xyz";
		data[1][2] = "User is not allowed";
		data[2][0] = "";
		data[2][1] = "";
		data[2][2] = "User is not allowed";

		return data;
	}

	@Test(dataProvider = "getdata")
	public void validateFormAuthentication(String username, String password, String text) throws InterruptedException {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		LandingPage l = new LandingPage(driver);
		
		l.getLogin().click();
		log.info("Navigated to Form Authentication Page");
		FormAuthenticationPage lp = new FormAuthenticationPage(driver);
		lp.getusername().sendKeys(username);
		lp.getpassword().sendKeys(password);
		lp.getlogin().click();
		log.info(text);
		
		SecurePage sp = new SecurePage(driver);
		String url = driver.getCurrentUrl();

		validationtext = sp.getvalidationmessage().getText();
		if (validationtext.contains("secure area")) {
			System.out.println("Login is successful");
			try {
				Assert.assertEquals(url, "https://the-internet.herokuapp.com/secure");
			} catch (Throwable t) {
				System.out.println("Url not matching.");
				errCollector.addError(t);
			}
		} else if (validationtext.contains("username is invalid")) {
			System.out.println("Login is unsuccessful");
			try {
				Assert.assertEquals(url, "https://the-internet.herokuapp.com/login");
			} catch (Throwable t) {
				System.out.println("Url not matching.");
				errCollector.addError(t);
			}
		}

		Thread.sleep(3000);

	}

	@AfterTest
	public void teardown() {
		driver.close();
	}
}
