package pomRepository;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TemplateClassPage {

	private WebDriver driver;
    public TemplateClassPage(WebDriver driver) {
    	this.driver = driver; 
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Template Classes']")
    private WebElement templateClassesTab;

    @FindBy(xpath = "//span[normalize-space()='New template class']") private WebElement newTemplateBtn;
    public WebElement getNewTemplateBtn() 
    {
    	return newTemplateBtn;
    }
    public void clickNewTemplateButton() {
    	newTemplateBtn.click();
    }

    @FindBy(xpath = "//h1[contains(text(),'Create Template Class')]")
    private WebElement createTemplateHeader;

    @FindBy(xpath = "//button[normalize-space()='Create Templates']")
    private WebElement createTemplateBtn;
    public WebElement getCreateTemplateBtn() 
    {
    	return createTemplateBtn;
    }
    public void clickCreateTemplateBtn() {
        createTemplateBtn.click();
    }

    @FindBy(xpath = "//p[normalize-space()='The name field is required.']")
    private WebElement nameMandatoryMsg;

    @FindBy(xpath = "//p[normalize-space()='The upload File field is required.']")
    private WebElement uploadFileMandatoryMsg;

    @FindBy(xpath = "//p[normalize-space()='The product field is required.']")
    private WebElement productMandatoryMsg;
    public boolean mandatoryFieldMessagesDisplayed() {
        return nameMandatoryMsg.isDisplayed()
        	&& uploadFileMandatoryMsg.isDisplayed()
        	&& productMandatoryMsg.isDisplayed();
    }

    @FindBy(id = "data.name")
    private WebElement nameTextField;
    public void enterName(String name) {
        nameTextField.sendKeys(name);
    }

    @FindBy(id = "data.template_status")
    private WebElement statusDropdown;
    public void selectStatus(String status) {
        statusDropdown.sendKeys(status);
    }

    @FindBy(id = "data.template_product")
    private WebElement productDropdown;
    public void selectProduct(String product) {
        productDropdown.sendKeys(product);
    }

    @FindBy(id = "data.workflow")
    private WebElement workflowDropdown;

    @FindBy(id = "data.stage")
    private WebElement stageDropdown;

    @FindBy(id = "data.status")
    private WebElement templateStatusDropdown;

    @FindBy(id = "data.document_class")
    private WebElement documentClassDropdown;
    public void selectDocumentClass(String docClassName) {
        documentClassDropdown.sendKeys(docClassName);
    }

    //@FindBy(id = "input.filepond--browser") private WebElement uploadFile;
    @FindBy(xpath = "//label[contains(@id,'filepond--drop-label')]/span[text()=' Browse ']") private WebElement browseButton;
    public WebElement getUploadFile() 
    {
    	return browseButton;
    }
    public void uploadFileUsingBrowseButton(String filePath) throws AWTException, InterruptedException {
    	
    		browseButton.click();
    	    Robot robot = new Robot();

    	    // Step 1: Wait for the file chooser dialog to open
    	    robot.delay(2000); // Optional: adjust as needed

    	    // Step 2: Press TAB 5 times to focus the address bar
    	    for (int i = 0; i < 5; i++) {
    	        robot.keyPress(KeyEvent.VK_TAB);
    	        robot.keyRelease(KeyEvent.VK_TAB);
    	        robot.delay(300);
    	    }

    	    // Step 3: Press SPACE to activate the path input
    	    robot.keyPress(KeyEvent.VK_SPACE);
    	    robot.keyRelease(KeyEvent.VK_SPACE);
    	    robot.delay(500);

    	    // Step 4: Paste the folder path
    	    //String folderPath = "C:\\Users\\Ajay\\LOS_Automation\\Los_Framework\\src\\test\\resources\\testData";
    	    StringSelection selection = new StringSelection(filePath);
    	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

    	    robot.keyPress(KeyEvent.VK_CONTROL);
    	    robot.keyPress(KeyEvent.VK_V);
    	    robot.keyRelease(KeyEvent.VK_V);
    	    robot.keyRelease(KeyEvent.VK_CONTROL);
    	    robot.delay(500);

    	    // Step 5: Press ENTER to open the folder
    	    robot.keyPress(KeyEvent.VK_ENTER);
    	    robot.keyRelease(KeyEvent.VK_ENTER);
    	    robot.delay(1000);

    	    // Step 6: TAB 5 times to navigate to file
    	    for (int i = 0; i < 5; i++) {
    	        robot.keyPress(KeyEvent.VK_TAB);
    	        robot.keyRelease(KeyEvent.VK_TAB);
    	        robot.delay(300);
    	    }

    	    // Step 7: Press SPACE to select the file
    	    robot.keyPress(KeyEvent.VK_SPACE);
    	    robot.keyRelease(KeyEvent.VK_SPACE);
    	    robot.delay(500);

    	    // Step 8: Press ENTER to confirm upload
    	    robot.keyPress(KeyEvent.VK_ENTER);
    	    robot.keyRelease(KeyEvent.VK_ENTER);
    	}

//    public void uploadTemplateFileBtn(String filePath) {
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", uploadFile);
//        uploadFile.sendKeys(new File(filePath).getAbsolutePath());
//        jsExecutor.executeScript("arguments[0].style.display = 'none'; arguments[0].style.visibility = 'hidden';", uploadFile);
//    }

//    public void uploadTemplateFileBtn(String filePath) {
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//
//        // Force visibility of the file input element
//        jsExecutor.executeScript("arguments[0].classList.remove('filepond--browser');", uploadFile);
//        jsExecutor.executeScript("arguments[0].style.display = 'block'; arguments[0].style.opacity = 1; arguments[0].style.visibility = 'visible';", uploadFile);
//
//        uploadFile.sendKeys(new File(filePath).getAbsolutePath());
//        //jsExecutor.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", uploadFile);
//
//        // Optional: hide again if needed
//        jsExecutor.executeScript("arguments[0].style.display = 'none'; arguments[0].style.visibility = 'hidden';", uploadFile);
//    }

    public void clickTemplateClassesTab() {
        templateClassesTab.click();
    }

    public boolean isCreateTemplateHeaderDisplayed() {
        return createTemplateHeader.isDisplayed();
    }

    public void selectWorkflow(String workflow) {
        workflowDropdown.sendKeys(workflow);
    }

    public void selectStage(String stage) {
        stageDropdown.sendKeys(stage);
    }

    public void selectTemplateStatus(String status) {
        templateStatusDropdown.sendKeys(status);
    }
}
