package SeleniumFramework.framework;

import SeleniumFramework.Pages.ITCLoginPage;
import SeleniumFramework.Pages.SelectAccount;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.apache.commons.lang.StringUtils;


import java.io.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BaseTest {
    WebDriver driver;
    protected ITCLoginPage loginPage;
    protected SelectAccount selectAccount;
    protected String summaryReport;
    public static ExtentTest test;

    @BeforeSuite
    @Parameters({"browser"})
    public void beforeSuite(@Optional String browser, ITestContext context) throws Exception {
        if (!StringUtils.isBlank(System.getProperty("browser"))) {
            browser = System.getProperty("browser");
        }
        else {
            if (browser != null) {
                browser = context.getCurrentXmlTest().getParameter("browser");
            } else {
                browser = ConfigFileReader.readApplicationFile("browser");
            }
        }
        summaryReport = "AutomationSummaryReport_"+browser+".html";
        ExtentManager.generateExtentReport(System.getProperty("user.dir")+ File.separator+"target"+ File.separator+"surefire-reports"+File.separator+"ExtentReports_"+browser+".html");

    }

    @BeforeMethod
    public void beforeMethod(Method m)
    {
        test=ExtentManager.extentReport.createTest("TestCase : "+m.getName());
    }

    @BeforeTest
    @Parameters({"browser"})
    public void setDriver(@Optional String browser, ITestContext context) throws Exception {
        if (!StringUtils.isBlank(System.getProperty("browser"))) {
            browser = System.getProperty("browser");
        }
          else {
            if (browser != null) {
                browser = context.getCurrentXmlTest().getParameter("browser");
            } else {
                browser = ConfigFileReader.readApplicationFile("browser");
            }
        }

          System.out.println("Browser : "+browser);
        if (browser.equalsIgnoreCase("chrome")) {
            DesiredCapabilities capabilitie = new DesiredCapabilities();
            capabilitie.setCapability("browserName", "Chrome");
            if (ConfigFileReader.readApplicationFile("enabledGrid").equals(true)) {
                driver = new RemoteWebDriver(new URL("http://192.168.1.100:4444/grid/console"), capabilitie);
            }
            WebDriverManager.chromedriver().setup();
            String path = System.getProperty("user.dir");
            String downloadFilepath = path + "\\Exported file folder";
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFilepath);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            DesiredCapabilities capabilitie = new DesiredCapabilities();
            capabilitie.setCapability("browserName", "firefox");
            if (ConfigFileReader.readApplicationFile("enabledGrid").equals(true)) {
                driver = new RemoteWebDriver(new URL(ConfigFileReader.readApplicationFile("hubURL")), capabilitie);
            } else {
                driver = new FirefoxDriver();
            }
        }

        if(!StringUtils.isBlank(System.getProperty("URL")))
        {
            driver.get(System.getProperty("URL"));
        }
        else {
            driver.get(ConfigFileReader.readApplicationFile("URL"));
        }
        Thread.sleep(2000);
        loginPage = PageFactory.initElements(driver,ITCLoginPage.class);
        selectAccount = PageFactory.initElements(driver,SelectAccount.class);
    }

    @BeforeMethod
    public void afterMethod()
    {
        ExtentManager.extentReport.flush();
    }

    @AfterTest
    public void closeDriver()
    {
        driver.close();
    }

    @AfterSuite
    public void tearDown()
    {
        ExtentManager.extentReport.flush();
    }

}
