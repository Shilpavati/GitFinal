package Academy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import pageObjects.FormAuthenticationPage;
import pageObjects.SecurePage;
import resources.base;

public class VerifyFormAuthenticationPageWithExcel extends base {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	public String validationtext;

	@Rule
	public ErrorCollector errCollector = new ErrorCollector();

	@BeforeTest
	public void Initialize() throws IOException {
		driver = InitializeDriver();

	}

	@Test
	public void validateFormAuthentication() throws InterruptedException, IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\DemoData.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i <= sheets - 1; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testData")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row r = rows.next();
				r = rows.next();
				while (rows.hasNext()) {
					// r = rows.next();
					Iterator<Cell> ce = r.cellIterator();

					Cell value = ce.next();
					driver.get(prop.getProperty("url"));
					driver.manage().window().maximize();
					LandingPage l = new LandingPage(driver);
					l.getLogin().click();
					log.info("Navigated to Form Authentication Page");

					FormAuthenticationPage lp = new FormAuthenticationPage(driver);
					lp.getusername().sendKeys(value.getStringCellValue().toString());
					value = ce.next();
					lp.getpassword().sendKeys(value.getStringCellValue().toString());
					lp.getlogin().click();

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
					r = rows.next();

				}

			}
		}
		Thread.sleep(3000);

	}

	@AfterTest
	public void teardown() {
		driver.close();
	}
}
