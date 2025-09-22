package pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.JavaScriptUtil;

public class RoleSelectionPage {

	public static String product = null;

	private WebDriver driver;

    public RoleSelectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='SUPER_ADMIN']")
    private WebElement superAdminRoleBtn;

//    @FindBy(xpath = "//span[text()='"+product+"']")
//    private WebElement twoWheelerButton;
    
    public void selectProductByText(String product) {
        String dynamicXPath = "//span[text()='" + product + "']";
        WebElement productButton = driver.findElement(By.xpath(dynamicXPath));
        
        JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
        jsUtil.jsClick(productButton);     
    }

    @FindBy(xpath = "//span[normalize-space(text())='Proceed']")
    private WebElement proceedButton;

    public WebElement selectSuperAdminRole() {
    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
    	jsUtil.jsClick(superAdminRoleBtn);
    	return superAdminRoleBtn;
    }

//    public void selectTwoWheeler() {
//    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
//    	jsUtil.jsClick(twoWheelerButton);
//    }

    public WebElement clickProceed() {
    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
        jsUtil.jsClick(proceedButton);
        return proceedButton;
    }
}