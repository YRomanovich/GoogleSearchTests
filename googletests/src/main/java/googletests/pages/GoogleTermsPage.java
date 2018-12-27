package googletests.pages;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Step;

public class GoogleTermsPage extends BasePage {
	
	public GoogleTermsPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Get url for Terms page")
	public String getUrl() {
		return driver.getCurrentUrl();
	}
}
