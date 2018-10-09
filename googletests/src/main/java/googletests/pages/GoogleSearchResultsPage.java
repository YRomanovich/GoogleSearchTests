package googletests.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Step;

public class GoogleSearchResultsPage extends BasePage {
	
	@FindBy(id="lst-ib")
	@CacheLookup
	private WebElement searchField;
	
	@FindBy(how = How.XPATH, xpath="//*[@class='bkWMgd']//div[@class='rc']/h3[@class='r']/a | //*[@class='bkWMgd']//div[@class='rc']//a//h3")
	@CacheLookup
	private List<WebElement> searchResults;
	
	@FindBy(id="logo")
	@CacheLookup
	private WebElement googleLogo;
	
	WebDriverWait wait = new WebDriverWait(driver,10);
	
	public GoogleSearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Get current URL")
	public String getURL() {
		return driver.getCurrentUrl();
	}
	
	@Step("Click on Google logo")
	public GoogleLandingPage clickGoogleLogo() {
		googleLogo.click();
		return new GoogleLandingPage(driver);
	}
	
	public List<String> getSearchResults(){
		List<String> results = new ArrayList<String>();
		
		for (WebElement element: searchResults) {
			results.add(element.getText());
		}
		
		return results;
	}
	
	@Step("Check search results is empty")
	public boolean isSearchResultsEmpty() {	
		return searchResults.isEmpty();
	}
	
	@Step("Get search results count")
	public int countSearchResults() {
		return searchResults.size();
	}
	
	@Step("Check that search results contain {0}")
	public Boolean containsSearchText(String textToSearch) {
		Boolean contains = false;
		wait.until(ExpectedConditions.visibilityOf(googleLogo));
		List<String> results = new ArrayList<String>(this.getSearchResults());
		
		for(String result : results) {
			contains = result.toLowerCase().contains(textToSearch.toLowerCase());
			
			if (contains == false) 
				break;
		}
		
		return contains;
	}
	
	@Step("Get value from search field")
	public String getSearchValue() {
		String resultValue = searchField.getAttribute("value");
		return resultValue;
	}
}