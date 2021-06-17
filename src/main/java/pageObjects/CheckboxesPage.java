package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage {

	public WebDriver driver;
	By allCheckbox = By.xpath("//input[@type='checkbox']");

	public CheckboxesPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<WebElement> getCheckbox() {
		return driver.findElements(allCheckbox);
	}

}
