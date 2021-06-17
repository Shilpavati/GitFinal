package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormAuthenticationPage {
	public WebDriver driver;
	By username = By.id("username");
	By password = By.id("password");
	By login = By.xpath("//i[contains(text(),'Login')]");

	public FormAuthenticationPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getusername() {
		return driver.findElement(username);
	}
	
	public WebElement getpassword() {
		return driver.findElement(password);
	}
	
	public WebElement getlogin() {
		return driver.findElement(login);
	}
}
