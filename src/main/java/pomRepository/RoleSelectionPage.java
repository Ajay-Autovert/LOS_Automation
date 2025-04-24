package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.JavaScriptUtil;

public class RoleSelectionPage {

	private WebDriver driver;

    public RoleSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='SALES']")
    private WebElement salesRoleBtn;

    @FindBy(xpath = "//span[text()='2W']")
    private WebElement twoWheelerButton;

    @FindBy(xpath = "//span[normalize-space(text())='Proceed']")
    private WebElement proceedButton;

    public WebElement selectSalesRole() {
    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
    	jsUtil.jsClick(salesRoleBtn);
    	return salesRoleBtn;
    }

    public void selectTwoWheeler() {
    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
    	jsUtil.jsClick(twoWheelerButton);
    }

    public WebElement clickProceed() {
    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
        jsUtil.jsClick(proceedButton);
        return proceedButton;
    }
}