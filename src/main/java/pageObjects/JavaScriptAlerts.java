package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptAlerts {
	public WebDriver driver;
	By btnJSAlert = By.xpath("//button[contains(text(),'Click for JS Alert')]");
	By btnJSConfirm = By.xpath("//button[contains(text(),'Click for JS Confirm')]");
	By btnJSPrompt = By.xpath("//button[contains(text(),'Click for JS Prompt')]");
	By JSresult = By.xpath("//p[@id='result']");

	public JavaScriptAlerts(WebDriver driver) {
		this.driver = driver;
	}
	public WebElement getJSAlert() {
		return driver.findElement(btnJSAlert);
	}

	public WebElement getJSConfirm() {
		return driver.findElement(btnJSConfirm);
	}

	public WebElement getJSPrompt() {
		return driver.findElement(btnJSPrompt);
	}

	public WebElement getResult() {
		return driver.findElement(JSresult);
	}
}
