package pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentClassPage {

    public DocumentClassPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Document Classes']") private WebElement documentClassTab;

    @FindBy(xpath = "//span[normalize-space()='New document class']") private WebElement newDocumentClassBtn;
    public WebElement getNewDocumentClassBtn() 
    {
    	return newDocumentClassBtn;
    }
    public void clickNewDocumentClass() {
    	newDocumentClassBtn.click();
    }

    @FindBy(xpath = "//h1[contains(text(),'Create Document Class')]") private WebElement createDocClassHeader;

    @FindBy(xpath = "//span[normalize-space()='Create & create another']") private WebElement createAndCreateAnotherButton;
    public WebElement getCreateAndCreateAnotherBtn() 
    {
    	return createAndCreateAnotherButton;
    }
    public void clickCreateAndCreateAnother() {
        createAndCreateAnotherButton.click();
    }

    @FindBy(xpath = "//p[normalize-space()='The name field is required.']") private WebElement nameMandatoryMsg;
    public boolean isNameMandatoryMessageDisplayed() {
        return nameMandatoryMsg.isDisplayed();
    }

    @FindBy(id = "data.name") private WebElement nameTextField;
    public void enterName(String name) {
        nameTextField.sendKeys(name);
    }

    @FindBy(id = "data.status") private WebElement statusDropdown;
    public void selectStatus(String status) {
        statusDropdown.sendKeys(status); // assuming it's a select input
    }

    @FindBy(xpath = "//button[normalize-space()='Create']") private WebElement createButton;
    public void clickCreateButton() {
        createButton.click();
    }	

    // Getters and Actions

    public void clickDocumentClassTab() {
        documentClassTab.click();
    }

    public boolean isCreateDocumentClassPageDisplayed() {
        return createDocClassHeader.isDisplayed();
    }
}
