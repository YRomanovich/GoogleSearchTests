package googletests.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import googletests.apps.Constants;
import googletests.helpers.ConfigFileReader;
import googletests.helpers.Log;
import googletests.helpers.Utils;
import googletests.listeners.EventListener;

@Listeners({googletests.listeners.ScreenShotListener.class, googletests.listeners.TestListener.class})
public class BaseTest {
	public static WebDriver driver;
	private static Logger log = Log.getLogData(BaseTest.class.getName());
	
	protected WebDriver getDriver() {
		return driver;
	}
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public static void beforeMethod(Method method, @Optional("firefox") String browser) {
		Utils.closeBrowserInstances(); 
		driver = setUpDriver(browser);
		driver = setUpEvenListener(driver);
		
		driver.manage().timeouts().implicitlyWait(new ConfigFileReader().getImplicitlyWait(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(new ConfigFileReader().getPageLoadWait(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("[Configuration] - Driver configuration and initialization is done");
		log.info("Driver configuration and initialization is done");
		
		Log.startTestCase(method.getName());
		driver.manage().deleteAllCookies();
		System.out.println("[Configuration] - All cookies was deleted before test");
		log.info("All cookies was deleted before test");
	}
	
	@AfterMethod(alwaysRun = true)
	public static void afterMethod() {
		driver.manage().deleteAllCookies();
		
		driver.close();
		System.out.println("[Configuration] - Browser was closed");
		System.out.println("-----------------------------------------------------------------------------");
		log.info("Browser was closed");
		
		System.out.println("[Configuration] - All cookies was deleted after test");
		log.info("All cookies was deleted after test");
		Log.endTestCase();
	}
	
	@DataProvider(parallel = false)
	public static Object[][] searchResults(){
		Object[][] searchResults = Utils.getTestData("TestData");
		System.out.println("[Configuration] - Data provider was set up");
		log.info("Data provider was set up");
		
		return searchResults;
	}
	
	private static WebDriver setUpEvenListener(WebDriver driver) {
		EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
		EventListener eventListener = new EventListener();
		eventDriver.register(eventListener);
		
		return eventDriver;
	}
	
	private static WebDriver setUpDriver(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", Constants.DRIVERS_DIRECTORY + "geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("[Configuration] - FireFox driver is used");
			log.info("FireFox driver is used");
		}else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", Constants.DRIVERS_DIRECTORY + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			System.out.println("[Configuration] - IE driver is used");
			log.info("IE driver is used");
		}else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.DRIVERS_DIRECTORY + "chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("[Configuration] - Chrome driver is used");
			log.info("Chrome driver is used");
		}else if (browser.equalsIgnoreCase("opera")) {
			System.setProperty("webdriver.opera.driver", Constants.DRIVERS_DIRECTORY + "operadriver.exe");
			driver = new OperaDriver();
			System.out.println("[Configuration] - Opera driver is used");
			log.info("Opera driver is used");
		}	
		return driver;
	}
}