package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space(text())='Dashboard']") private WebElement dashboardBtn;
    public WebElement getDashboardBtn() {
        return dashboardBtn;
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
}