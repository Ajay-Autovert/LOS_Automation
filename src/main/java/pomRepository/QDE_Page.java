package pomRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import genericLibrary.SelectDropdownUtility;

public class QDE_Page {

    WebDriver driver;
    SelectDropdownUtility dropdownUtil;

    public QDE_Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        dropdownUtil = new SelectDropdownUtility(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'I agree to the')]/preceding-sibling::input[@type='checkbox']") private WebElement phoneNumberCheckBox;
    public void PhoneNumberCheckBox() {
        phoneNumberCheckBox.click();
    }
    
    @FindBy(xpath = "(//button[contains(@class, 'fi-icon-btn') and contains(@class, 'text-custom-500') and contains(@class, 'fi-color-primary')])[1]") 
    private WebElement sendOtpBtn;
    public WebElement SendOtpBtn() {
        return sendOtpBtn;
    }
    
    @FindBy(xpath = "//input[@label='Enter OTP' and @type='text' and @maxlength='4']") private WebElement phoneOtpInputField;
    public WebElement getPhoneOtpInputField() {
        return phoneOtpInputField;
    }
    
    @FindBy(xpath = "(//input[@type='text' and @maxlength='4'])[2]") private WebElement emailOtpInputField;
    public WebElement getEmailOtpInputField() {
        return emailOtpInputField;
    }
    
    @FindBy(xpath = "(//span[normalize-space(text())='Verify OTP'])[3]") private WebElement emailOtpVerifyBtn;
    public WebElement getEmailOtpVerifyBtn() {
        return emailOtpVerifyBtn;
    }
    @FindBy(xpath = "(//span[normalize-space()='Verify OTP'])[1]") private WebElement verifyOtpBtn;
    public WebElement getVerifyOtpBtn() {
        return verifyOtpBtn;
    }
    @FindBy(xpath = "//input[@placeholder='Enter PAN Number']") private WebElement txtPanTextfield;
    public WebElement getTxtPanTextfield() {
        return txtPanTextfield;
    }
    @FindBy(xpath = "(//button[@title='Verify' and contains(@class, 'fi-icon-btn')])") private WebElement btnVerifyPan;
    public WebElement getVerifyPanButton() {
        return btnVerifyPan;
    }
    @FindBy(xpath = "//button[@type='button' and contains(@class,'fi-icon-btn') and contains(@class,'blink')]") private WebElement verifyEmailButton;
    public WebElement getVerifyEmailButton() {
        return verifyEmailButton;
    }
    @FindBy(xpath = "//button[contains(normalize-space(.),'QDE')]") private WebElement btnQdeHeader;
    public WebElement getQdeHeaderButton() {
        return btnQdeHeader;
    }
    public WebElement clickQdeHeaderButton() {
         return btnQdeHeader;
    }
    
    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }
}
