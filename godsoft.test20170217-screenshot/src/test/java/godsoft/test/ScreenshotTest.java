package godsoft.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import egovframework.rte.fdl.string.EgovDateUtil;

public class ScreenshotTest {

	@Test
	public void test() {
		// fail("Not yet implemented");

		System.out.println("갓소프트 이백행");

		WebDriver driver = new FirefoxDriver();
		// WebDriver driver = new ChromeDriver();

		driver.get("http://blog.naver.com/dlqorgod");
		// getScreenshotAs(driver);
		//
		// driver.get("http://blog.naver.com/dlqorgod/220564514526");
		// getScreenshotAs(driver);
		//
		// driver.get("http://blog.naver.com/dlqorgod/220563062116");
		// getScreenshotAs(driver);

		driver.quit();
	}

	private void getScreenshotAs(WebDriver driver) {
		System.out.println("Page title is: " + driver.getTitle());

		File srcFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		File destFile = new File(SystemUtils.USER_HOME
				+ "/Desktop/screenshot "
				+ EgovDateUtil.toString(new Date(), "yyyy-MM-dd HH시mm분ss초",
						null) + ".png");

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
