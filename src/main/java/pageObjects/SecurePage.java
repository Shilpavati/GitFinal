package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecurePage {
	public WebDriver driver;
	// By validationmessage = By.xpath("//div[contains(text(),'You logged into a
	// secure area!')]");
	By validationmessage = By.xpath("//div[@id='flash']");

	public SecurePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getvalidationmessage() {
		return driver.findElement(validationmessage);
	}
}
