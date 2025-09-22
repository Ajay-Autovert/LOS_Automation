package pomRepository;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import genericLibrary.JavaScriptUtil;
import genericLibrary.SelectDropdownUtility;

public class LeadDedupe {

	WebDriver driver;
    SelectDropdownUtility dropdownUtil;
    WebDriverWait wait;
    WebDriverWait shortWait;

    public LeadDedupe(WebDriver driver) {
        this.driver = driver; // set driver first
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // now it's safe
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
        dropdownUtil = new SelectDropdownUtility(driver);
    }

    @FindBy(id = "dedupe_action")
    private WebElement dedupeActionDropdown;
    
    @FindBy(xpath = "//button[normalize-space()='Lead Dedupe']") private WebElement leadDedupeHeader;
    public WebElement LeadDedupeHeader() {
        return leadDedupeHeader;
    }
    
    @FindBy(xpath = "(//span[normalize-space()='Submit'])[2]") private WebElement leadDedupeSubmitBtn;
    public WebElement LeadDedupeSubmitBtnHeader() {
        return leadDedupeHeader;
    }
    
    public void LeadDedupeSubmitBtn() throws InterruptedException {
    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
    	Thread.sleep(1000);
        jsUtil.jsClick(leadDedupeSubmitBtn);
    }
    
    public void leadDedupeAction() throws InterruptedException {
    new Select(dedupeActionDropdown).selectByVisibleText("Release");}
    
    @SuppressWarnings("unused")
	private void scrollBy(int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + y + ")");
    }

    @SuppressWarnings("unused")
	private void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }
    
    public void DuplicateLeadDedupeValidations(LeadCreationPage leadCreationPage) {
        try {
            if (shortWait.until(ExpectedConditions.visibilityOf(LeadDedupeHeader())).isDisplayed()) {
                Reporter.log("Lead Dedupe section is visible. Proceeding with Manual Release.", true);
                LeadDedupeHeader().click();
                leadDedupeAction();
                leadCreationPage.scrollBy(100);
                wait.until(ExpectedConditions.visibilityOf(LeadDedupeSubmitBtnHeader()));
                LeadDedupeSubmitBtn();
                Thread.sleep(1000);
                leadCreationPage.scrollToTop();
            }
        } catch (Exception e) {
            Reporter.log("Lead Dedupe section not present or not visible. Skipping Manual Release.", true);
        }
    }
}
