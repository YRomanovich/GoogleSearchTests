package googletests.pages;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Step;

public class GoogleHelpPage extends BasePage {
	
	public GoogleHelpPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Get url for Help page")
	public String getUrl() {
		return driver.getCurrentUrl();
	}
}
