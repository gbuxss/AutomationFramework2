package gbuxssCreation.Resources;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports getReport() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String filePath= System.getProperty("user.dir")+"/Reports/Report"+timestamp+".html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(new File(filePath));
		reporter.config().setDocumentTitle("Automation Test Results");
		reporter.config().setReportName("Automation Test");


		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		return report;

	}
}
