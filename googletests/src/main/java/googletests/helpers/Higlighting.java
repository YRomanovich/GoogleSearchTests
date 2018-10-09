package googletests.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Higlighting {
	public void higlightElemnt(WebElement element, WebDriver driver) {
	   if (element !=null || driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
	   }
	}
	
	public void unhighlightElement(WebElement element, WebDriver driver) {
		if (element !=null || driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", element);
	   }
	}
}
