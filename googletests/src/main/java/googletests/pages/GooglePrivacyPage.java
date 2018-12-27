package googletests.pages;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Step;

public class GooglePrivacyPage extends BasePage {
	public GooglePrivacyPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Get url for Privacy page")
	public String getUrl() {
		return driver.getCurrentUrl();
	}
}
