package com.artsgard.automationpracticetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author artsgard
 */
public class RoundTrip {
    //private FirefoxDriver driver;
    //private final String DRIVER_PATH = "C:\\Program Files (x86)\\geckodriver\\geckodriver.exe";
    //private final String DRIVER_KEY = "webdriver.gecko.driver";

    private ChromeDriver driver;
    private final String DRIVER_KEY = "webdriver.chrome.driver";
    private final String DRIVER_PATH = "C:\\Program Files (x86)\\chromedriver\\chromedriver.exe";

    private final String EXPEDIA_URL = "https://www.expedia.com/?pwaLob=wizard-flight-pwa";

    private WebElement oneWayTrip;
    private WebElement travelersLink;
    private WebElement travelersPlusButton;
    private WebElement travelersDoneButton;

    private WebElement depCity;
    private WebElement depCityButton;
    private WebElement arrCity;
    private WebElement depDate;
    private WebElement arrDate;
    private WebElement depDateButton;
    private WebElement arrDateButton;
    private WebElement search;

    public static void main(String[] args) {
        RoundTrip trip = new RoundTrip();
        trip.SetDriver();
        trip.setFlightAToB();
        trip.closedriver();
    }

    public void SetDriver() {
        System.setProperty(DRIVER_KEY, DRIVER_PATH);
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("marionatte", false);
        ChromeOptions opt = new ChromeOptions();
        //FirefoxOptions opt = new FirefoxOptions();
        opt.merge(dc);
        driver = new ChromeDriver(opt);
        //driver = new FirefoxDriver(opt);
        //driver.manage().window().fullscreen();
    }

    /*
    came until 76 can't learn this stuff in one day!
     */
    public void setFlightAToB() {
        driver.get(EXPEDIA_URL);

        oneWayTrip = driver.findElement(By.xpath("//a[@href='?flightType=oneway']"));
        oneWayTrip.click();

        travelersLink = driver.findElement(By.xpath("//a[@role = 'button']"));
        travelersLink.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        travelersPlusButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("uitk-button uitk-button-small uitk-flex-item uitk-step-input-button")));
        
        travelersPlusButton.click();
 
        //travelersDoneButton = driver.findElement(By.cssSelector("button[class='uitk-button uitk-button-large uitk-button-fullWidth uitk-button-has-text uitk-button-primary uitk-button-floating']"));
        travelersDoneButton = driver.findElement(By.xpath("//*[text() = 'done']"));
        travelersDoneButton.click();
        
        
        depCity = driver.findElement(By.id("location-field-leg1-origin-input"));
        depCity.sendKeys("value", "Luqa (MLA-Malta Intl.)");

        depDate = driver.findElement(By.id("d1"));
        depDate.sendKeys("value", "2020-10-07");

        depDateButton = driver.findElement(By.id("d1-btn"));
        depDateButton.click();

        search = driver.findElement(By.cssSelector("button[class='uitk-button uitk-button-large uitk-button-fullWidth uitk-button-has-text uitk-button-primary']"));
        search.click();
       
    }

    public void setFlightBToC() {
        
    }

    public void setFlightCToA() {

    }

    public void closedriver() {
        driver.close();
    }
}
