package com.qualitystreamtutorial.QualityStreamTutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class PruebaLogIn {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sebastian\\OneDrive\\Escritorio\\GoogleDrivers\\driver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }
    
    @Test
    public void testLoginCorrecto() {
    	//Creamos las "variables" para entrar
        driver.get("https://www.saucedemo.com/v1/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));//wait for page to load.
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement contra = driver.findElement(By.id("password"));
        WebElement BtnLogin = driver.findElement(By.id("login-button"));
 //Ponemos los datos en las clases 
        username.clear();
        username.sendKeys("standard_user");
        contra.clear();
        contra.sendKeys("secret_sauce");
        BtnLogin.click();

        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        String txtProducto = productTitle.getText();
        System.out.println(txtProducto);
        assertEquals("Products", txtProducto);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.shopping_cart_link")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", cartLink);

        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    @Test
    public void agregarProductosAlCarrito() throws InterruptedException {
       
        driver.get("https://www.saucedemo.com/v1/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.clear();
        usernameField.sendKeys("standard_user");
        passwordField.clear();
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        
        // Agregar el primer producto al carrito
        WebElement Agregar1 = driver.findElement(By.xpath("//div[@class='inventory_list']//button[contains(@class, 'btn_primary')]"));
        String Producto1 = driver.findElement(By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_name'][1]")).getText();
        Agregar1.click();
        
        // Agregar un segundo producto al carrito
        WebElement Agregar2 = driver.findElement(By.xpath("(//div[@class='inventory_list']//button[contains(@class, 'btn_primary')])[1]"));
        String Producto2 = driver.findElement(By.xpath("(//div[@class='inventory_list']//div[@class='inventory_item_name'])[2]")).getText();
        Agregar2.click();
        
        // Vvemos que el carrito de compras este en 2 , el numero de rojo 
        WebElement CarritoCompras = driver.findElement(By.className("shopping_cart_badge"));
        assertEquals("2", CarritoCompras.getText());
        
        // Ir al carrito
        WebElement CarritoLink = driver.findElement(By.className("shopping_cart_link"));
        CarritoLink.click();
        
        // vemos si esta en el carrito o nonas
        wait.until(ExpectedConditions.urlContains("cart.html"));
        
        // Vemos que los productos esten en el carrito
        Thread.sleep(3000);
        WebElement ListaCarrito = driver.findElement(By.className("cart_list"));
        assertTrue(ListaCarrito.getText().contains(Producto1));
        assertTrue(ListaCarrito.getText().contains(Producto2));
        
        Thread.sleep(3000);
        
        // vamos al checkout
        WebElement BotonVerificar = driver.findElement(By.xpath("//a[contains(@class, 'checkout_button')]"));
        BotonVerificar.click();
        
        // vemos  que estamos en la pagina de informacion del checkout
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }
    
    @After
    public void Salir() {
        
            
    }
}