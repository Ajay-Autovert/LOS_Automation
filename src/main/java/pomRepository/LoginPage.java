package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[normalize-space(text())='Sign in']")
    private WebElement signInHeader;
    
    @FindBy(id = "data.email")
    private WebElement EmailTextField;

    @FindBy(id = "data.password") private WebElement PasswordTextField;

    @FindBy(xpath = "//button[@type='submit']") private WebElement SignInButton;
    
    @FindBy(xpath = "//span[text()='2W']")
    private WebElement TwoWheelerButton;
    
    @FindBy(xpath = "//input[@id='data.password']/parent::div/following-sibling::div[1]")
    private WebElement showPasswordIcon;
    
    @FindBy(id = "data.remember")
    private WebElement rememberMeCheckBox;
    
    public WebElement getTwoWheelerButton() {
    	return TwoWheelerButton;
    }
    
    public WebElement getSignInHeader() {
        return signInHeader;
    }

    public void login(String email_Sales, String password_Sales) {
    	EmailTextField.clear();
    	EmailTextField.sendKeys(email_Sales);
    	PasswordTextField.clear();
        PasswordTextField.sendKeys(password_Sales);
    }
    
    public void clickShowPassword() {
        showPasswordIcon.click();
    }
    
    public boolean isPasswordVisible() {
        return PasswordTextField.getAttribute("type").equals("text");
    }
    
    public void clickHidePassword() {
        showPasswordIcon.click();
    }
    
    public void clickRememberMe() {
        rememberMeCheckBox.click();
    }
    
    public void clickSignInBtn() {
    	SignInButton.click();
    }
}
