package googletests.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import googletests.pages.BasePage;
import googletests.pages.GoogleFeedbackPage;
import googletests.pages.GoogleHelpPage;
import googletests.pages.GooglePrivacyPage;
import googletests.pages.GoogleTermsPage;
import ru.yandex.qatools.allure.annotations.Step;

public class FooterMenu extends BasePage {
	@FindBy(how = How.XPATH, xpath="//span[@id='fsl']/a[contains(@href, 'help')]")
	private WebElement help;
	
	@FindBy(how = How.XPATH, xpath="//span[@id='fsl']/a[@id='dk2qOd']")
	private WebElement feedback;
	
	@FindBy(how = How.XPATH, xpath="//span[@id='fsl']/a[contains(@href, 'privacy')]")
	private WebElement privacy;
	
	@FindBy(how = How.XPATH, xpath="//span[@id='fsl']/a[contains(@href, 'terms')]")
	private WebElement terms;
	
	public FooterMenu(WebDriver driver) {
		super(driver);
	}
	
	@Step("Get visibility of help link")
	public Boolean isHelpLinkDisplayed() {		
		return this.help.isDisplayed();
	}
	
	@Step("Get visibility of feedback link")
	public Boolean isFeedbackLinkDisplayed() {		
		return this.feedback.isDisplayed();
	}
	
	@Step("Get visibility of privacy link")
	public Boolean isPrivacyLinkDisplayed() {		
		return privacy.isDisplayed();
	}
	
	@Step("Get visibility of terms link")
	public Boolean isTermsLinkDisplayed() {		
		return terms.isDisplayed();
	}
	
	@Step("Click on help link")
	public GoogleHelpPage clickHelpLink() {	
		help.click();
		
		return new GoogleHelpPage(driver);
	}
	
	@Step("Click on feedback link")
	public GoogleFeedbackPage clickFeedbackLink() {		
		this.feedback.click();
		
		return new GoogleFeedbackPage(driver);
	}
	
	@Step("Click on privacy link")
	public GooglePrivacyPage clickPrivacyLink() {		
		privacy.click();
		
		return new GooglePrivacyPage(driver);
	}
	
	@Step("Click on terms link")
	public GoogleTermsPage clickTermsLink() {		
		terms.click();
		
		return new GoogleTermsPage(driver);
	}
	
}
