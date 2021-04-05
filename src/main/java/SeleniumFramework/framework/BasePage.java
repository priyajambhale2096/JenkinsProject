package SeleniumFramework.framework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends BaseTest{

    public void clickOn(WebElement element) throws InterruptedException {
        waitForElement(element);
        element.click();
    }

    public void waitForElement(WebElement element) throws InterruptedException {
        Thread.sleep(2000);
    }

    public void selectByVisibleText(WebElement element,String value) throws InterruptedException {
        waitForElement(element);
        Select select=new Select(element);
        clickOn(element);
        select.selectByVisibleText(value);
    }
}
