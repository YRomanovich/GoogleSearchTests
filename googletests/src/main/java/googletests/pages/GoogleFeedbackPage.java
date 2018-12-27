package googletests.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ru.yandex.qatools.allure.annotations.Step;

public class GoogleFeedbackPage extends BasePage {
	@FindBy(id="google-feedback-wizard")
	private WebElement feedbackWizard;
	
	@FindBy(how = How.TAG_NAME, using="textarea")
	private WebElement feedbackField;
	
	@FindBy(how = How.XPATH, xpath="//input[@type='checkbox']")
	private WebElement addScreenshot;
	
	@FindBy(how = How.XPATH, xpath="//button[@key='annotate']")
	private WebElement screenshotEditor;
	
	@FindBy(how = How.XPATH, xpath="//button[@key='send']")
	private WebElement submitButton;
	
	@FindBy(how = How.XPATH, xpath="//button[@key='cancel']")
	private WebElement cancelButton;
	
	@FindBy(how = How.XPATH, xpath="//div[@key='description-error']")
	private WebElement emptyFieldMessage;
	
	public GoogleFeedbackPage (WebDriver driver) {
		super(driver);
	}
	
	@Step("Check that feedback wizard is opened")
	public Boolean isWizardOpened() {
		return feedbackWizard.isDisplayed();
	}
	
	@Step("Enter feedback text: {0}")
	public GoogleFeedbackPage enterFeedback(String feedback) {
		driver.switchTo().frame("google-feedback-wizard");
		feedbackField.sendKeys(feedback);
		driver.switchTo().defaultContent();
		
		return this;
	}
	
	@Step("Clear feedback field")
	public GoogleFeedbackPage clearFeedbackField() {
		driver.switchTo().frame("google-feedback-wizard");
		feedbackField.clear();
		driver.switchTo().defaultContent();
		
		return this;
	}
	
	@Step("Send feedback")
	public GoogleSearchResultsPage sendFeedback() {
		driver.switchTo().frame("google-feedback-wizard");
		submitButton.click();
		driver.switchTo().defaultContent();
		
		return new GoogleSearchResultsPage(driver);
	}
	
	@Step("Cancel feedback")
	public GoogleSearchResultsPage cancelFeedback() {
		driver.switchTo().frame("google-feedback-wizard");
		cancelButton.click();
		driver.switchTo().defaultContent();
		
		return new GoogleSearchResultsPage(driver);
	}
	
	@Step("Check if Add Screenshot option is selected")
	public Boolean isAddScreenshotSelected() {
		driver.switchTo().frame("google-feedback-wizard");
		Boolean isSelected = addScreenshot.isSelected();
		driver.switchTo().defaultContent();
		
		return isSelected;
	}
	
	@Step("Check if Screenshot Editor is displayed")
	public Boolean isScreenshotEditorDisplayed() {
		Boolean isDisplayed = true;
		driver.switchTo().frame("google-feedback-wizard");
		try {
			screenshotEditor.isDisplayed();
		} catch (NoSuchElementException e) {
			isDisplayed = false;
		}
		driver.switchTo().defaultContent();
		
		return isDisplayed;
	}
	
	@Step("Check if error message about empty field is displayed")
	public Boolean isEmptyFieldErrorMessageDisplayed() {
		driver.switchTo().frame("google-feedback-wizard");
		Boolean isDisplayed = emptyFieldMessage.isDisplayed();
		driver.switchTo().defaultContent();
		
		return isDisplayed;
	}
	
	@Step("Unselect Add Screenshot option")
	public GoogleFeedbackPage unselectkScreenshotOption() {
		driver.switchTo().frame("google-feedback-wizard");
		if(addScreenshot.isSelected()) {
			addScreenshot.click();
		}
		driver.switchTo().defaultContent();
		
		return this;
	}
	
	@Step("Click on Submit button")	
	public GoogleFeedbackPage submitFeedback() {
		driver.switchTo().frame("google-feedback-wizard");
		submitButton.click();
		driver.switchTo().defaultContent();
		
		return this;
	}
	
	@Step("Select Add Screenshot option")
	public GoogleFeedbackPage selectScreenshotOption() {
		driver.switchTo().frame("google-feedback-wizard");
		if(addScreenshot.isSelected() != true) {
			addScreenshot.click();
		}
		driver.switchTo().defaultContent();
		
		return this;
	}
}
