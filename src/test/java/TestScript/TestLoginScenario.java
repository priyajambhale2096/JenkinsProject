package TestScript;

import SeleniumFramework.framework.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.logging.impl.Log4JLogger;
import org.testng.annotations.Test;

public class TestLoginScenario extends BaseTest {
        @Test
        public void verifyLoginPage() throws InterruptedException {
            test.log(Status.INFO,"Verify login ITC");
            loginPage.loginToTempConnect();
            //test.log()
        }
}
