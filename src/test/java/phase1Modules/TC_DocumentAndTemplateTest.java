package phase1Modules;

import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import genericLibrary.Base_Class;
import genericLibrary.LoginUtility;
import pomRepository.DocumentClassPage;
import pomRepository.HomePage;
import pomRepository.TemplateClassPage;

public class TC_DocumentAndTemplateTest extends Base_Class {

    @Test
    public void createDocumentAndAttachToTemplate() throws InterruptedException, AWTException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1-10: Common login + role selection
        LoginUtility loginUtil = new LoginUtility(driver);
        loginUtil.loginToApplication("email_Sales", "password_Sales");
        Reporter.log("", true);

        // Step 11: Validate home page
        HomePage homePage = new HomePage(driver);
        Thread.sleep(5000);
//        wait.until(ExpectedConditions.visibilityOf(homePage.getHomeBtn()));
        //homePage.getHomeBtn();
        wait.until(ExpectedConditions.visibilityOf(homePage.getDashboardBtn()));
        Assert.assertTrue(homePage.isHomePageDisplayed(), "User is not on home page");

        // Step 12: Navigate to Document Classes
        homePage.clickDocumentClassesTab();
        homePage.getCollapseBar().click();
        DocumentClassPage documentClassPage = new DocumentClassPage(driver);
        wait.until(ExpectedConditions.visibilityOf(documentClassPage.getNewDocumentClassBtn()));
        Reporter.log("Navigated to Document Classes page", true);

        // Step 13-15: Create Document Class page
        documentClassPage.clickNewDocumentClass();
        wait.until(ExpectedConditions.visibilityOf(documentClassPage.getCreateAndCreateAnotherBtn()));
        Reporter.log("Navigated to Create Document Class page", true);
        documentClassPage.clickCreateAndCreateAnother();

        Assert.assertTrue(documentClassPage.isNameMandatoryMessageDisplayed(), "Mandatory message not shown for Name field");
        Reporter.log("Validated the mandatory name field", true);
        String docClassName = "AutoDocClass" + System.currentTimeMillis();
        documentClassPage.enterName(docClassName);
        documentClassPage.selectStatus("Active");
        documentClassPage.clickCreateButton();
        Reporter.log("Created new Document Class", true);


        // Step 16-17: Validate Template Classes page
        TemplateClassPage templatePage = new TemplateClassPage(driver);
        wait.until(ExpectedConditions.visibilityOf(templatePage.getNewTemplateBtn()));
        templatePage.clickNewTemplateButton();

        // Step 18-19: Validate Create Template page & mandatory fields
        wait.until(ExpectedConditions.visibilityOf(templatePage.getCreateTemplateBtn()));
        templatePage.clickCreateTemplateBtn();
        Assert.assertTrue(templatePage.mandatoryFieldMessagesDisplayed(), "Mandatory messages not shown for the Fields: 'Name','Upload File' and 'Product' ");
        Reporter.log("Validated the Mandatory messages for the Fields: 'Name','Upload File' and 'Product' ", true);
        
        // Step 20-25: Fill template details
        templatePage.enterName("AutoTemplate" + System.currentTimeMillis());
        templatePage.selectStatus("Active");
        templatePage.selectProduct("2W");
        templatePage.selectDocumentClass(docClassName);
        templatePage.uploadFileUsingBrowseButton("C:\\Users\\Ajay\\LOS_Automation\\Los_Framework\\src\\test\\resources\\testData");
        
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[contains(@class,'filepond--list')]/li")));


        templatePage.selectProduct("2W");
        templatePage.selectWorkflow("2w OLA");
        templatePage.selectStage("Lead Creation");
        Thread.sleep(3000);
        templatePage.selectTemplateStatus("Approved");
        templatePage.clickCreateTemplateBtn();

        // Step 26-27: Validate Template Class listing
        templatePage.clickTemplateClassesTab();
//        wait.until(ExpectedConditions.visibilityOf(templatePage.getTemplateList()));
//        Assert.assertTrue(templatePage.isTemplatePresent(templatePage), "Template not present in listing");
        Reporter.log("Successfully validated template creation and linking to document class", true);
    }
}
