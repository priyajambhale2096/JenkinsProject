package SeleniumFramework.Pages;

import SeleniumFramework.framework.BasePage;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectAccount extends BasePage {

    @FindBy(xpath = "//select[@class='valid-input account ellipsis']")
    public WebElement listAccount;

    public void selectAccount() throws InterruptedException {
        try {
            selectByVisibleText(listAccount,"Test Account");
            System.out.println("Account selected...");
            test.log(Status.PASS,"Account Selected successfully...");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("User don't have 'Test Account'' account");
            test.log(Status.FAIL,"User don't have 'Test Account'' account");
        }
    }

}
