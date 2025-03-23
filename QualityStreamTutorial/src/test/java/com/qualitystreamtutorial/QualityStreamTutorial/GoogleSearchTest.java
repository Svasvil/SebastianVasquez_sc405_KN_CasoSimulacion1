package com.qualitystreamtutorial.QualityStreamTutorial;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {
    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebastian\\OneDrive\\Escritorio\\GoogleDrivers\\driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }
    
    @Test
    public void TestGooglePage() {
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.clear();
        searchbox.sendKeys("Quality-Stream Introduccion a la automatizacion de pruebas de software");
        searchbox.submit();
        
        assertEquals("Quality-Stream Introduccion a la automatizacion de pruebas de software - Google Search", driver.getTitle());
    }
    
   /* @After
    public void TearDown() {
        driver.quit();
    }
    */
}