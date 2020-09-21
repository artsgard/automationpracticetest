package com.artsgard.automationpracticetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationPracticeFireFoxTest {

    FirefoxDriver driver;
    WebElement email;
    WebElement password;
    WebElement login;
    String errorUrl = "http://automationpractice.com/index.php?controller=authentication";
    String succesUrl = "http://automationpractice.com/index.php?controller=my-account";

    @BeforeTest
    public void SetDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\geckodriver\\geckodriver.exe");
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("marionatte", false);
        FirefoxOptions opt = new FirefoxOptions();
        opt.merge(dc);
        driver = new FirefoxDriver(opt);
    }

    @Test
    public void login_succes() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        email = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("passwd"));
        login = driver.findElement(By.id("SubmitLogin"));
        email.sendKeys("willemfritsdragstra@gmail.com");
        password.sendKeys("Ali12345");
        login.click();
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, succesUrl);
    }

    @Test
    public void login_bad_password() { // alert alert-danger
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        email = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("passwd"));
        login = driver.findElement(By.id("SubmitLogin"));

        email.sendKeys("willemfritsdragstra@gmail.com");
        password.sendKeys("bad-password");
        login.click();
        String expectedUrl = driver.getCurrentUrl();
        WebElement errorMessage = driver.findElement(By.className("alert-danger"));
        String errorMessageText = errorMessage.findElement(By.tagName("li")).getText();
        Assert.assertEquals(errorMessageText, "Authentication failed.");
        Assert.assertEquals(expectedUrl, errorUrl);
    }

    @Test
    public void login_no_fields_set() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        email = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("passwd"));
        login = driver.findElement(By.id("SubmitLogin"));
        email.sendKeys("");
        password.sendKeys("");
        login.click();
        String expectedUrl = driver.getCurrentUrl();
        WebElement errorMessage = driver.findElement(By.className("alert-danger"));
        String errorMessageText = errorMessage.findElement(By.tagName("li")).getText();
        Assert.assertEquals(errorMessageText, "An email address required.");
        Assert.assertEquals(expectedUrl, errorUrl);
    }

    @AfterTest
    public void closedriver() {
        driver.close();
    }
}
