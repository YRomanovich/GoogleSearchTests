package googletests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import googletests.helpers.ConfigFileReader;
import ru.yandex.qatools.allure.annotations.Step;

public class GoogleLandingPage extends BasePage {
	@FindBy(id="lst-ib")
	@CacheLookup
	private WebElement searchField;
	
	@FindBy(name="btnK")
	@CacheLookup
	private WebElement searchButton;
	
	String url = new ConfigFileReader().getBaseUrl();
	
	public GoogleLandingPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Open Google landing page")
	public GoogleLandingPage openStartPage() {
		driver.navigate().to(url);
		return this;
	}
	
	@Step("Enter {0} in search field")
	public GoogleLandingPage setSearchValue(String textToSearch) {
		searchField.clear();
		searchField.sendKeys(textToSearch);
		return this;
	}
	
	@Step("Start search")
	public GoogleSearchResultsPage startSearch() {
		this.searchButton.click();
		return new GoogleSearchResultsPage(driver);
	}
	
	@Step("Get current URL")
	public String getURL() {
		return driver.getCurrentUrl();
	}
}