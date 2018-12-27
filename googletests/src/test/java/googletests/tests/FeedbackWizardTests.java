package googletests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import googletests.base.BaseTest;
import googletests.pages.GoogleFeedbackPage;
import googletests.pages.GoogleLandingPage;
import googletests.pages.GoogleSearchResultsPage;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Features("Feedback")
@Stories("Send feedback")
public class FeedbackWizardTests extends BaseTest {
	GoogleLandingPage googleLandingPage;
	
	@Description("Open Google feedback wizard")
	@Test(priority=1, description="Open Google feedback wizaed")
	@Severity(value=SeverityLevel.BLOCKER)
	public void openFeedbackWizard(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();
		
		Assert.assertTrue(googleSearchResultsPage.footerMenu().isFeedbackLinkDisplayed(), "Feedback link is not displayed in the footer of the page");
		
		GoogleFeedbackPage googleFeedbackPage = googleSearchResultsPage.footerMenu().clickFeedbackLink();
		
		Assert.assertTrue(googleFeedbackPage.isWizardOpened(), "Feedback wizard is not opened");
	}
	
	@Description("Send feedback with attachment")
	@Test(priority=2, description="Send feedback with attachment")
	@Severity(value=SeverityLevel.CRITICAL)
	public void sendFeedbackWithAttachment(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();		
		GoogleFeedbackPage googleFeedbackPage = googleSearchResultsPage.footerMenu().clickFeedbackLink();
		
		Assert.assertTrue(googleFeedbackPage.isAddScreenshotSelected(), "Screenshot is not added to the feedback by default");
		
		googleSearchResultsPage = googleFeedbackPage.enterFeedback("Test").sendFeedback();
		
		Assert.assertTrue(googleSearchResultsPage.getURL().contains("search"), "Page with search results is not opened. Something went wrong during feedback sending.");
	}
	
	@Description("Send feedback without attachment")
	@Test(priority=3, description="Send feedback without attachment")
	@Severity(value=SeverityLevel.NORMAL)
	public void sendFeedbackWithoutAttachment(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();		
		GoogleFeedbackPage googleFeedbackPage = googleSearchResultsPage.footerMenu().clickFeedbackLink();
		
		Assert.assertTrue(googleFeedbackPage.isAddScreenshotSelected(), "Screenshot is not added to the feedback by default");
		
		googleSearchResultsPage = googleFeedbackPage.unselectkScreenshotOption().enterFeedback("Test").sendFeedback();
		
		Assert.assertTrue(googleSearchResultsPage.getURL().contains("search"), "Page with search results is not opened. Something went wrong during feedback sending.");
	}
	
	@Description("Cancel feedback sending")
	@Test(priority=3, description="Calcel feedback sending")
	@Severity(value=SeverityLevel.NORMAL)
	public void cancelFeedback(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();		
		GoogleFeedbackPage googleFeedbackPage = googleSearchResultsPage.footerMenu().clickFeedbackLink();
		
		googleSearchResultsPage = googleFeedbackPage.enterFeedback("Test").cancelFeedback();
		
		Assert.assertTrue(googleSearchResultsPage.getURL().contains("search"), "Page with search results is not opened. Something went wrong during feedback cancelation.");
	}
	
	@Description("Try to send feedback without text")
	@Test(priority=3, description="Try to send feedback without text")
	@Severity(value=SeverityLevel.NORMAL)
	public void sendFeedbackNoText(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();		
		GoogleFeedbackPage googleFeedbackPage = googleSearchResultsPage.footerMenu().clickFeedbackLink();
		
		Assert.assertTrue(googleFeedbackPage.enterFeedback("").submitFeedback().isWizardOpened(), "Feedback wizard is not displayed");
		Assert.assertTrue(googleFeedbackPage.isEmptyFieldErrorMessageDisplayed(), "Error message about empty feedback field is not displayed");
	}
	
	@Description("Check wizard UI after unkecking of Add Screenshot")
	@Test(priority=4, description="Check wizard UI after unkecking of Add Screenshot")
	@Severity(value=SeverityLevel.MINOR)
	public void unckeckScreenshotAdding(){
		googleLandingPage = new GoogleLandingPage(getDriver());
		GoogleSearchResultsPage googleSearchResultsPage = googleLandingPage.openStartPage().setSearchValue("Testing").startSearch();		
		GoogleFeedbackPage googleFeedbackPage = googleSearchResultsPage.footerMenu().clickFeedbackLink();
		
		googleFeedbackPage.unselectkScreenshotOption();
		
		Assert.assertFalse(googleFeedbackPage.isAddScreenshotSelected(), "Add Screenshot option is selected in feedback wizard");
		Assert.assertFalse(googleFeedbackPage.isScreenshotEditorDisplayed(), "Screenshot editor is displayed in feedback wizard when for not selected Add SCreenshot option");
	}
}
