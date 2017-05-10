package com.metis._orgname_._metistemplate_;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class LoginAdminTest extends AbstractTest {
	
  private static final String FOLDER = "target/screenshots/";
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
	}
  
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080";
    String path = new File(".").getCanonicalPath();
    System.out.println("PATH:: "+path);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLoginAdmin() throws Exception {
    driver.get(baseUrl + "/"+CONTEXT+"/login.html");
    driver.findElement(By.id("Username")).clear();
    driver.findElement(By.id("Username")).sendKeys("admin");
    driver.findElement(By.id("Password")).clear();
    driver.findElement(By.id("Password")).sendKeys("12345");
    takeScreenShot();
    driver.findElement(By.cssSelector("button.btn.btn-login")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.id("lbtnUser"))) break; } catch (Exception e) {}
    	Thread.sleep(3000);
    }
    takeScreenShot();
//    assertEquals("admin", driver.findElement(By.id("lbtnUser")).getText());
    driver.findElement(By.id("lbtnLogout")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("button.btn.btn-login"))) break; } catch (Exception e) {}
    	Thread.sleep(3000);
    }

    assertTrue(isElementPresent(By.cssSelector("button.btn.btn-login")));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
