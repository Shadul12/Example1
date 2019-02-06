package GBLibrary;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
	public static WebDriver driver;

	public static Global libray;

	@BeforeMethod
	public void beforeEachTest() {
		libray = new Global(driver);
		driver = libray.startChromeBrowser();
		libray.GoToWebsite("https://www.facebook.com/");

	}

	@AfterMethod
	public void afterEachTest() throws InterruptedException {
		Thread.sleep(3500);
		if (driver  != null) {
			driver.quit();
		}
	}

}
