package SeleniumFramework.Pages;

import SeleniumFramework.framework.BasePage;
import SeleniumFramework.framework.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ITCLoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='text']")
    public WebElement usernameField;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordField;

    @FindBy(xpath = "//span[contains(.,'Log in')]")
    public WebElement LoginButton;

    public void loginToTempConnect() throws InterruptedException {
            waitForElement(usernameField);
            usernameField.clear();
            usernameField.sendKeys("sqa.onset@gmail.com");
            waitForElement(passwordField);
            passwordField.clear();
            passwordField.sendKeys("Sqaonset@123");
            waitForElement(LoginButton);
            clickOn(LoginButton);
            System.out.println("Login successfully...");
            test.log(Status.PASS,"Login successfully....");
    }
}
