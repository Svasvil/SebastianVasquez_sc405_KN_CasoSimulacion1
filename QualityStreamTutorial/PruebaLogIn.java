package com.qualitystreamtutorial.QualityStreamTutorial;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PruebaLogIn {

	    private WebDriver driver;
	    private WebDriverWait wait;

	    @Before
	    public void setup() {
	    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebastian\\OneDrive\\Escritorio\\GoogleDrivers\\driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	        
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        wait = new WebDriverWait(driver, 10);
	    }
	    
	    @Test
	    public void testLoginSuccessful() {
	        // 1. Navegar a la página de login
	        driver.get("https://www.saucedemo.com/");

	        // 2. Localizar elementos del formulario
	        WebElement usernameField = driver.findElement(By.id("user-name"));
	        WebElement passwordField = driver.findElement(By.id("password"));
	        WebElement loginButton = driver.findElement(By.id("login-button"));

	        // 3. Introducir credenciales
	        usernameField.clear();
	        usernameField.sendKeys("standard_user"); // Usando credenciales válidas para saucedemo
	        passwordField.clear();
	        passwordField.sendKeys("secret_sauce"); // Usando credenciales válidas para saucedemo

	        // 4. Enviar el formulario
	        loginButton.click();

	        // 5. Esperar a que se cargue la página después del login
	        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/inventory.html"));

	        // 6. Verificar que el login fue exitoso
	        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

	        // 7. (Opcional) Verificar un elemento único en la página
	        WebElement inventoryTitle = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.className("title"))
	        );
	        assertTrue(inventoryTitle.isDisplayed());
	        assertEquals("Products", inventoryTitle.getText());
	    }
	    @Test
	    public void testLoginFailed() {
	        // 1. Navegar a la página de login
	        driver.get("https://www.saucedemo.com/");
	        
	        // 2. Localizar elementos del formulario
	        WebElement usernameField = driver.findElement(By.id("username"));
	        WebElement passwordField = driver.findElement(By.id("password"));
	        WebElement loginButton = driver.findElement(By.id("login-button"));
	        
	        // 3. Introducir credenciales incorrectas
	        usernameField.clear();
	        usernameField.sendKeys("usuario_incorrecto");
	        
	        passwordField.clear();
	        passwordField.sendKeys("contraseña_incorrecta");
	        
	        // 4. Enviar el formulario
	        loginButton.click();
	        
	        // 5. Verificar que aparece un mensaje de error
	        WebElement errorMessage = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))
	        );
	        
	        assertTrue(errorMessage.isDisplayed());
	        assertTrue(errorMessage.getText().contains("Credenciales inválidas"));
	    }
	    
	    /*@After
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	    */
	}

