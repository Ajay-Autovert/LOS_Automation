package genericLibrary;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectDropdownUtility {

    WebDriver driver;

    public SelectDropdownUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFromChoicesDropdown(WebElement inputField, String optionText) {
        inputField.click();
        inputField.sendKeys(optionText);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String xpath = "//div[contains(@class,'choices__item--selectable') and normalize-space(text())='" + optionText + "']";

        WebElement dropdownOption = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
        );

        dropdownOption.click();
    }
    
    public void selectFromChoicesDropdownUsingEnter(WebElement inputField, String optionText, int dropdownIndex) throws InterruptedException {
        JavaScriptUtil jsUtil = new JavaScriptUtil(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click the outer wrapper (so drop-down opens)
        Thread.sleep(1000);
        jsUtil.jsClick(inputField);
        Thread.sleep(500);
        
        // Step 2: Locate the dynamic visible input field
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
        		By.xpath("(//div[contains(@class,'choices__list--dropdown')]//input[contains(@class,'choices__input--cloned')])["+dropdownIndex+"]")
        ));
        
        // Step 3: Clear & Send Keys to the visible input
        jsUtil.jsClear(searchInput);
        jsUtil.jsSendkeys(optionText, searchInput);
        Thread.sleep(500);
        
        // Step 4: Click the actual matching option
        WebElement targetOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'choices__item--choice') and normalize-space(text())='"+optionText+"']")
        ));
        
        Actions act = new Actions(driver);
        act.moveToElement(targetOption).click().perform();
    }
}