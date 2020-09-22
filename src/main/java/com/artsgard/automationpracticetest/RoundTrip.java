package com.artsgard.automationpracticetest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author artsgard
 */
public class RoundTrip {
    private FirefoxDriver driver;
    private final String DRIVER_PATH = "C:\\Program Files (x86)\\geckodriver\\geckodriver.exe";
    private final String DRIVER_KEY = "webdriver.gecko.driver";

    //private ChromeDriver driver;
    //private final String DRIVER_KEY = "webdriver.chrome.driver";
    //private final String DRIVER_PATH = "C:\\Program Files (x86)\\chromedriver\\chromedriver.exe";
    
    private final String EXPEDIA_URL = "https://www.expedia.com/?pwaLob=wizard-flight-pwa";
    
    private WebElement oneWayTrip;
    private WebElement fourTraveles;
    private WebElement fourTravelesButton;
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
        //ChromeOptions opt = new ChromeOptions();
        FirefoxOptions opt = new FirefoxOptions();
        opt.merge(dc);
        //driver = new ChromeDriver(opt);
        driver = new FirefoxDriver(opt);
        //driver.manage().window().fullscreen();
    }

    /*
    notting seems to work here (just the fist two lines setting the One-way option works)
    */

    public void setFlightAToB() {
        driver.get(EXPEDIA_URL);

        oneWayTrip = driver.findElement(By.xpath("//a[@href='?flightType=oneway']"));  
        oneWayTrip.click(); 
        
        depCityButton = driver.findElement(By.cssSelector("button[class='is-visually-hidden']"));
        depCityButton.click();
        
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
