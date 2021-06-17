package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	public WebDriver driver;
	By formauthenticationlink = By.xpath("//a[@href='/login']");
	By checkboxeslink = By.xpath("//a[@href='/checkboxes']");
	By jsalertlink = By.xpath("//a[contains(text(),'JavaScript Alerts')]");

	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getLogin() {
		return driver.findElement(formauthenticationlink);
	}

	public WebElement getCheckBoxes() {
		return driver.findElement(checkboxeslink);
	}

	public WebElement getjsalertlink() {
		return driver.findElement(jsalertlink);
	}
}
