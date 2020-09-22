package com.artsgard.automationpracticetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author artsgard
 */
public class AutomationPracticeChromeTest {
    private ChromeDriver driver;
    private final String DRIVER_KEY = "webdriver.chrome.driver";
    private final String DRIVER_PATH = "C:\\Program Files (x86)\\chromedriver\\chromedriver.exe";
    private final String LOGGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private WebElement email;
    private WebElement password;
    private WebElement login;
    private final String AUTHENTICATION_URL = "http://automationpractice.com/index.php?controller=authentication";
    private final String AUTHENTICATION_SUCCES_URL = "http://automationpractice.com/index.php?controller=my-account";
    private final String ERROR_MESSAGE_BAD_CREDENTIALS = "Authentication failed.";
    private final String ERROR_MESSAGE_EMPTY_CREDENTIALS = "An email address required.";

    @BeforeTest
    public void SetDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver\\chromedriver.exe");
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("marionatte", false);
        ChromeOptions opt = new ChromeOptions();
        opt.merge(dc);
        driver = new ChromeDriver();
        //driver.manage().window().fullscreen();
    }

   
     @Test
    public void login_succes() {
        driver.get(LOGGIN_URL);
        email = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("passwd"));
        login = driver.findElement(By.id("SubmitLogin"));
        email.sendKeys("willemfritsdragstra@gmail.com");
        password.sendKeys("Ali12345");
        login.click();
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, AUTHENTICATION_SUCCES_URL);
    }

    @Test
    public void login_bad_password() { // alert alert-danger
        driver.get(LOGGIN_URL);
        email = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("passwd"));
        login = driver.findElement(By.id("SubmitLogin"));

        email.sendKeys("willemfritsdragstra@gmail.com");
        password.sendKeys("bad-password");
        login.click();
        String expectedUrl = driver.getCurrentUrl();
        WebElement errorMessage = driver.findElement(By.className("alert-danger"));
        String errorMessageText = errorMessage.findElement(By.tagName("li")).getText();
        Assert.assertEquals(errorMessageText, ERROR_MESSAGE_BAD_CREDENTIALS);
        Assert.assertEquals(expectedUrl, AUTHENTICATION_URL);
    }

    @Test
    public void login_no_fields_set() {
        driver.get(LOGGIN_URL);
        email = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("passwd"));
        login = driver.findElement(By.id("SubmitLogin"));
        email.sendKeys("");
        password.sendKeys("");
        login.click();
        String expectedUrl = driver.getCurrentUrl();
        WebElement errorMessage = driver.findElement(By.className("alert-danger"));
        String errorMessageText = errorMessage.findElement(By.tagName("li")).getText();
        Assert.assertEquals(errorMessageText, ERROR_MESSAGE_EMPTY_CREDENTIALS);
        Assert.assertEquals(expectedUrl, AUTHENTICATION_URL);
    }

    @AfterTest
    public void closedriver() {
        driver.close();
    }
}
