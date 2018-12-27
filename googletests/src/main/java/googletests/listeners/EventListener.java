package googletests.listeners;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import googletests.helpers.Log;

public class EventListener implements WebDriverEventListener {
	private static Logger log = Log.getLogData(EventListener.class.getName());

	@Override
	public void afterAlertAccept(WebDriver driver) {
		System.out.println("[Action] - Alert dialog was accepted");	
		log.info("Alert dialog was accepted");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("[Action] - Alert dialog was dismissed");
		log.info("Alert dialog was dismissed");
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] chars) {
		if (chars == null) {
			System.out.println("[Action] - Value for element was changed");
			log.info("Value for element was changed");
		} else {
			System.out.printf("[Action] - Value for element was changed to \n" ,chars[0].toString());
			log.info("Value for element was changed" + chars[0].toString());
		}
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("[Action] - Click on found element");
		log.info("Click on found element");
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.printf("[Action] - Element was found by %s selector \n" ,by.toString());
		log.info("Element was found by " + by.toString() + " selector");
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> type, X screenshot) {
		System.out.println("[Action] - Screenshot was taken");
		log.info("Screenshot was taken");
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		System.out.printf("[Action] - Text %s was taken from the found element \n" ,text);	
		log.info("Text " + text + " was taken from the found element");
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		System.out.println("[Action] - Back navigation was executed");
		log.info("Back navigation was executed");
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		System.out.println("[Action] - Navigation forth was executed");
		log.info("Navigation forth was executed");
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("[Action] - Page was refreshed");
		log.info("Page was refreshed");
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.printf("[Action] - Navigation was done to %s \n" ,url);
		log.info("Navigation was done to " + url);
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		System.out.printf("[Action] - Script %s was executed \n" ,script);
		log.info("Script " + script + " was executed");
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		System.out.printf("[Action] - Switched to %s window \n" ,windowName);
		log.info("Switched to " + windowName + " window");
	}

	@Override
	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("[Action] - Try to accept alert dialog");
		log.info("Try to accept alert dialog");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("[Action] - Try to dismiss alert dialog");
		log.info("Try to dismiss alert dialog");
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] chars) {
		if (chars == null) {
			System.out.println("[Action] - Try to changes value for element");
			log.info("Try to changes value for element");
		} else {
			System.out.printf("[Action] - Try to changes value for element to %s \n" ,chars[0].toString());
			log.info("Try to changes value for element to " + chars[0].toString());
		}
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("[Action] - Try to click on found element");
		log.info("Try to click on found element");
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.printf("[Action] - Try to find element by %s selector \n" ,by.toString());
		log.info("Try to find element by " + by.toString() + " selector");
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> type) {
		System.out.println("[Action] - Try to tacke a screenshot");	
		log.info("Try to take a screenshot");
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		System.out.println("[Action] - Try to get text from found element");
		log.info("Try to get text from found element");
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("[Action] - Try to navigate back");
		log.info("Try to navigate back");
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("[Action] - Try to navigate forth");
		log.info("Try to navigate forth");
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("[Action] - Try to refresh a page");
		log.info("Try to refresh a page");
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.printf("[Action] - Try to navigata to %s \n" ,url);
		log.info("Try to navigata to " + url);
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		System.out.printf("[Action] - Try to execute script %s \n" ,script);
		log.info("Try to execute script " + script);
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		System.out.printf("[Action] - Try to switch to %s window \n", windowName);
		log.info("Try to switch to " + windowName + " window");
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		System.out.printf("[Action] - Exception was thrown %s \n", throwable.toString());
		log.info("Exception was thrown " + throwable.toString());
	}
}
