package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.JavaScriptUtil;

public class HomePage {

	private WebDriver driver;
	public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "(//span[normalize-space(text())='Collapse sidebar'])[2]") private WebElement collapseSideBar;
    public WebElement getCollapseBarValidation() {
    	return collapseSideBar;
    }
    public WebElement getCollapseBar() {
    	JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
    	jsUtil.jsClick(collapseSideBar);
    	return collapseSideBar;
    }
    
    @FindBy(xpath = "//span[normalize-space(text())='Dashboard']") private WebElement dashboardBtn;
    public WebElement getDashboardBtn() {
        return dashboardBtn;
    }
    @FindBy(xpath = "(//span[normalize-space(text())='Create New'])[1]") private WebElement createNewLeadBtn;
    public WebElement getCreateNewLeadBtn() {
        return createNewLeadBtn;
    }
    
    @FindBy(xpath = "//span[normalize-space(text())='Document Classes']") private WebElement documentClassesTabBtn;
    public WebElement getDocumentClassesTabBtn() {
        return documentClassesTabBtn;
    }
    public void clickDocumentClassesTab() {
    	documentClassesTabBtn.click();
    }

    @FindBy(xpath = "//span[normalize-space(text())='Template Classes']") private WebElement templateClassesTabBtn;
    public WebElement getTemplateClassesTabBtn() {
        return templateClassesTabBtn;
    }
    public void clickTemplateClassesTab() {
    	templateClassesTabBtn.click();
    }
    
    //Not used any where but this logic can be applied in other places this more dynamic than validating the url's
    public boolean isHomePageDisplayed() {
        return dashboardBtn.isDisplayed();
    }
    
    @FindBy(xpath = "//span[normalize-space(text())='App Creation']") private WebElement btnAppCreationTray;
    public WebElement clickAppCreationTrayButton() {
        return btnAppCreationTray;
    }
}