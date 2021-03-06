package googletests.tests;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import googletests.base.BaseTest;
import googletests.pages.GoogleHelpPage;
import googletests.pages.GoogleLandingPage;
import googletests.pages.GooglePrivacyPage;
import googletests.pages.GoogleSearchResultsPage;
import googletests.pages.GoogleTermsPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Searching")
@Stories("Search in google")
public class GooglePageTests extends BaseTest {
	GoogleLandingPage googleLandingPage;
	GoogleSearchResultsPage googleSearchResultsPage;
	
	@Description("Check that Google landing page is opened")
	@Test(priority=1, description="Check that Google landing page is opened")
	@Severity(value=SeverityLevel.BLOCKER)
	public void landingPageIsOpened() {
		googleLandingPage = new GoogleLandingPage(getDriver());
		Assert.assertTrue(googleLandingPage.openStartPage().getURL().contains("google.com"), "Google landing page is not opened");
	}
	
	@Description("Check that Google page with search results is opened")
	@Test(priority=2, description="Check that Google page with search results is opened")
	@Severity(value=SeverityLevel.BLOCKER)
	public void searchPageIsOpened() {
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();
		Assert.assertTrue(googleSearchResultsPage.getURL().contains("search"), "Page with search results is not opened");
	}
	
	@Description("Navigate to Google landing by using Google logo on page with search results")
	@Test(priority=4, description="Navigate to Google landing by using Google logo on page with search results")
	@Severity(value=SeverityLevel.NORMAL)
	public void navigateToLandingPageByLogo(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();
		googleLandingPage = googleSearchResultsPage.clickGoogleLogo();
		Assert.assertTrue(googleLandingPage.getURL().contains("webhp"), "Google landing page is not opened");
	}
	
	@Description("Search infromation using Googele with parameters")
	@Parameters(value= {"textToSearch","serchResultsCount"})
	@Test(priority=3, description="Search infromation using Googele with parameters")
	@Severity(value=SeverityLevel.CRITICAL)
	public void searchTextParameters(@Optional("Microsoft") String stringToSearch, @Optional("6") int searchResultsCount) {	
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue(stringToSearch).startSearch();
		
		Assert.assertFalse(googleSearchResultsPage.isSearchResultsEmpty(), "Search results are not empty");
		Assert.assertTrue(googleSearchResultsPage.countSearchResults() == searchResultsCount, "Search results for " + stringToSearch + " contains " + String.valueOf(googleSearchResultsPage.countSearchResults()) + " results");
		Assert.assertTrue(googleSearchResultsPage.containsSearchText(stringToSearch), "Not all search results contains text " + stringToSearch);
	}
	
	@Description("Search infromation using Googele with dataprovider")	
	@Test(priority=5, dataProvider="searchResults", description="Search infromation using Googele with dataprovider")
	@Severity(value=SeverityLevel.CRITICAL)
	public void searchTextDataProvider(String stringToSearch, String searchResultsCount) {	
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue(stringToSearch).startSearch();;
		
		Assert.assertFalse(googleSearchResultsPage.isSearchResultsEmpty(), "Search results are not empty");
		Assert.assertTrue(googleSearchResultsPage.countSearchResults() == (int) Double.parseDouble(searchResultsCount), "Search results for " + stringToSearch + " contains " + String.valueOf(googleSearchResultsPage.countSearchResults()) + " results. Expected " + searchResultsCount.substring(0, searchResultsCount.indexOf(".")) + " search results");
		Assert.assertTrue(googleSearchResultsPage.containsSearchText(stringToSearch), "Not all search results contains text " + stringToSearch);
	}
	
	@Description("Navigate to Google help page")
	@Test(priority=4, description="Navigate to Google help page")
	@Severity(value=SeverityLevel.NORMAL)
	public void navigateToHelpPage(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();
		
		Assert.assertTrue(googleSearchResultsPage.footerMenu().isHelpLinkDisplayed(), "Help link is not displayed in the footer of the page");
		
		GoogleHelpPage googleHelpPage = googleSearchResultsPage.footerMenu().clickHelpLink();
		
		Assert.assertTrue(googleHelpPage.getUrl().contains("support"), "Help page is not opened");
	}
	
	@Description("Navigate to Google privacy page")
	@Test(priority=4, description="Navigate to Google privacy page")
	@Severity(value=SeverityLevel.NORMAL)
	public void navigateToPrivacyPage(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();
		
		Assert.assertTrue(googleSearchResultsPage.footerMenu().isPrivacyLinkDisplayed(), "Privacy link is not displayed in the footer of the page");
		
		GooglePrivacyPage googlePrivacyPage = googleSearchResultsPage.footerMenu().clickPrivacyLink();
		
		Assert.assertTrue(googlePrivacyPage.getUrl().contains("privacy"), "Privacy page is not opened");
	}
	
	@Description("Navigate to Google terms page")
	@Test(priority=4, description="Navigate to Google terms page")
	@Severity(value=SeverityLevel.NORMAL)
	public void navigateToTermsPage(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();
		
		Assert.assertTrue(googleSearchResultsPage.footerMenu().isTermsLinkDisplayed(), "Terms link is not displayed in the footer of the page");
		
		GoogleTermsPage googleTermsPage = googleSearchResultsPage.footerMenu().clickTermsLink();
		
		Assert.assertTrue(googleTermsPage.getUrl().contains("terms"), "Terms page is not opened");
	}
}
