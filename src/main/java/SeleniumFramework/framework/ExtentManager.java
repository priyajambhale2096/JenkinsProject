package SeleniumFramework.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;

public class ExtentManager{
    public static ExtentHtmlReporter htmlReport;
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;

    public static ExtentReports generateExtentReport(String fileName)
    {
        htmlReport=new ExtentHtmlReporter(fileName);
        extentReport=new ExtentReports();
        extentReport.attachReporter(htmlReport);

        htmlReport.config().setReportName("Automation ITC Report");
        htmlReport.config().setDocumentTitle("IntempConnect");

        return extentReport;
    }


}
