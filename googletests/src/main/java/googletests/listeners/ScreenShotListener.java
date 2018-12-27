package googletests.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import googletests.apps.Constants;
import googletests.base.BaseTest;
import googletests.helpers.Log;
import ru.yandex.qatools.allure.annotations.Attachment;

public class ScreenShotListener extends TestListenerAdapter{
	private static Logger log = Log.getLogData(EventListener.class.getName());
	private WebDriver driver;

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("[Results] - Test %s FAILED \n" ,result.getName());
		log.info("Test " + result.getName() + " FAILED");
		System.out.printf("[Information] - Priority of this test is %d \n" ,result.getMethod().getPriority());
		log.info("Priority of this test is " + result.getMethod().getPriority());

		this.driver = BaseTest.driver;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		SimpleDateFormat formaterDate = new SimpleDateFormat("dd_MM_yyyy");
		String methodName = result.getName();
		if (!result.isSuccess()) {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			this.saveScreenshot(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
			try {
				File destFile = new File(Constants.SCREENSHOT_DIRECTORY + "/failure_screenshots/" + formaterDate.format(calendar.getTime()) + "/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
				FileUtils.copyFile(srcFile, destFile);
				System.out.printf("[Information] - Screenshot was taken to %s \n" , destFile.getPath());
				log.info("Screenshot was taken to " + destFile.getPath());
			} catch (IOException e) {
				System.err.println("[Error] - Unable to take screen shot");
				log.error("Unable to take screen shot");
				e.printStackTrace();
			}
		}
		
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshot(byte[] screenShot) {
	    return screenShot;
	}
}

