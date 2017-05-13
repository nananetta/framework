package com.metis._orgname_._metistemplate_;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

public abstract class AbstractTest {

	private static final String FOLDER = "target/screenshots/";
	protected String CONTEXT = "_metistemplate_";
	protected String baseUrl = "http://localhost:8080/";
	protected WebDriver driver;

	
	public void takeScreenShot() throws IOException {
		String fileName = UUID.randomUUID().toString();
		takeScreenShot(fileName);
	}
	
	public void takeScreenShot(String fileName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(FOLDER + fileName + ".png");
		FileUtils.copyFile(scrFile, targetFile);
	}


}
