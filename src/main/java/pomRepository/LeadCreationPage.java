package pomRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
//import com.github.javafaker.Faker;

import genericLibrary.JavaScriptUtil;
import genericLibrary.PropertyFileReader;
import genericLibrary.SelectDropdownUtility;

public class LeadCreationPage {

    WebDriver driver;
    SelectDropdownUtility dropdownUtil;

    public LeadCreationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        dropdownUtil = new SelectDropdownUtility(driver);
    }
 	
    @FindBy(id = "product_id")
    private WebElement productDropdown;

    @FindBy(id = "sub_product_id")
    private WebElement subProductDropdown;

    @FindBy(id = "cus_first_name")
    private WebElement firstNameField;

    @FindBy(id = "cus_last_name")
    private WebElement lastNameField;

    @FindBy(id = "cus_phone_number")
    private WebElement phoneField;

    @FindBy(id = "cus_email")
    private WebElement emailField;

    @FindBy(id = "lead_sourcing_platform")
    private WebElement sourcingPlatformDropdown;

    @FindBy(id = "lead_sourcing_channel")
    private WebElement sourcingChannelDropdown;

    @FindBy(id = "lead_sourcing_pincode")
    private WebElement sourcePincodeField;

    @FindBy(id = "lead_portfolio")
    private WebElement leadPortfolioDropdown;

    @FindBy(id = "oem_name")
    private WebElement oemNameInput;
    
    @FindBy(id = "oem_name")
    private WebElement oemNameDropdown;

    @FindBy(id = "dealer_name")
    private WebElement dealerNameInput;

    @FindBy(id = "partner_name")
    private WebElement partnerNameInput;

    @FindBy(id = "fos_phone")
    private WebElement fosPhoneNumberField;

    @FindBy(xpath = "//span[text()='Save']")
    private WebElement submitButton;
    
    @FindBy(xpath = "//h1[normalize-space(text())='Lead Creation']") private WebElement leadCreationPageHeader;
    public WebElement getleadCreationPageHeader() {
        return leadCreationPageHeader;
    }
    
    public void fillLeadForm() throws InterruptedException {
        new Select(productDropdown).selectByVisibleText("2W");
        
        String firstName = PropertyFileReader.getValueProperty("first_Name");
        String lastName = PropertyFileReader.getValueProperty("last_Name");
        String custPhoneNumber = PropertyFileReader.getValueProperty("cust_Phone");
        String custEmail = PropertyFileReader.getValueProperty("cust_Email");
        
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        phoneField.sendKeys(custPhoneNumber);
        emailField.sendKeys(custEmail);

        new Select(sourcingPlatformDropdown).selectByVisibleText("Autovert");
        Thread.sleep(1000);
        new Select(sourcingChannelDropdown).selectByVisibleText("Dealer");
        scrollBy(200);
        sourcePincodeField.sendKeys("560001");
        scrollBy(400);
        
        SelectDropdownUtility dropdownUtil = new SelectDropdownUtility(driver);
        dropdownUtil.selectFromChoicesDropdownUsingEnter(oemNameInput, "Ather Energy - AE", 1);
        dropdownUtil.selectFromChoicesDropdownUsingEnter(dealerNameInput, "Ather Dealer", 2);
        
        scrollBy(300);
        fosPhoneNumberField.sendKeys("9734567890");
        Thread.sleep(1000);
        
        scrollToTop();
        JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
        jsUtil.jsClick(submitButton);
    }

    public void scrollBy(int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + y + ")");
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }
}
