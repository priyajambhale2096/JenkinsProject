package TestScript;

import SeleniumFramework.framework.BaseTest;
import org.testng.annotations.Test;

public class TestITCSelectAccount extends BaseTest {
    @Test
    public void testSelectAccount() throws InterruptedException {
        loginPage.loginToTempConnect();
        selectAccount.selectAccount();
    }
}
