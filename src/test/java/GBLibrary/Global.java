package GBLibrary;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class Global {
	private WebDriver driver;

	public Global(WebDriver _driver) {
		driver = _driver;
	}

	public WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}

	public void GoToWebsite(String websiteURL) {
		try {
			driver.get(websiteURL);
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}

	}

	public void findAndClick(By by) {
		try {
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}

	}

	public void findAndSendKeys(By by, String textYouWantToEnter) {
		try {
			driver.findElement(by).sendKeys(textYouWantToEnter);
		} catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}

	}

	public WebDriver GetTitle(String WebpageTitle) {
		try {
		String Title = driver.getTitle();
		assertEquals(Title, WebpageTitle);
		}catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}
		return driver;

	}

	public WebDriver getAndAssertText(By by, String expectedText) {
		try {
		WebElement elem = driver.findElement(by);
		String actualtext = elem.getText();
		String _expectedText = expectedText;
		assertEquals(actualtext, _expectedText);
		}catch (Exception e) {
			System.out.println("Error: " + e.getStackTrace());
		}
		return driver;
	}

	public String getCurrentTime() {
		String currentTime = null;
		try {
			Date date = new Date();
			String tempDate = new Timestamp(date.getTime()).toString();
			currentTime = tempDate.replace(" ", "_").replace(":", "_").replace(".", "_").replace("-", "_");
			// System.out.println("date string: '" +currentTime +"'");
		} catch (Exception e) {
			assertTrue(false);
		}
		return currentTime;
	}

	public void checkDirectory(String inputPath) {
		File file = new File(inputPath);
		String abPath = file.getAbsolutePath();
		File file2 = new File(abPath);
		if (!file2.exists()) {
			if (file2.mkdirs()) {
				System.out.println("folders created...");
			} else {
				System.out.println("folders not created...");
			}
		}
	}

	public String captureScreenshotForExtent(String screenshotFileName, String filePath) {

		String screenshotPath = null;
		String timestamp = getCurrentTime();
		try {
			if (!filePath.isEmpty()) {
				checkDirectory(filePath);
				screenshotPath = filePath + screenshotFileName + ".png";
			} else {
				checkDirectory("target/screenshots/");
				screenshotPath = "target/screenshots/" + screenshotFileName + timestamp + ".png";
			}

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File(screenshotPath));
		} catch (Exception e) {
			assertTrue(false);
		}
		System.out.println("Screenshot Captured: " + screenshotPath);
		return screenshotPath;
	}

}