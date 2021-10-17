package Holidays;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class SignUpTest {

    WebDriver driver;

    @Test
    public void signUp()  {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String firstName = "Anna";
        String lastName = "Nowak";
        int randomNumber = (int) (Math.random()*1000);
        String email = firstName + lastName + randomNumber + "@gmail.com";

        driver.findElements(By.cssSelector("[id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("111222333");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.name("confirmpassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button[class*='signupbtn']")).click();


        WebElement welcomeText = driver.findElement(By.cssSelector("[class='RTL']"));

        Assert.assertEquals(welcomeText.getText(), "Hi, " + firstName + " " + lastName, "Wrong name");
        Assert.assertTrue(welcomeText.getText().contains(firstName));
        Assert.assertTrue(welcomeText.getText().contains(lastName));


        driver.quit();
    }

    @Test
    public void SignUpWithNoValues()  {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElements(By.cssSelector("[id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);


        driver.findElement(By.cssSelector("button[class*='signupbtn']")).click();


        WebElement warning = driver.findElement(By.cssSelector("[class*='alert-danger']"));
        System.out.println(warning.getText());


        Assert.assertTrue(warning.getText().contains("The Email field is required."));
        Assert.assertTrue(warning.getText().contains("The Password field is required."));

        driver.quit();


    }

}
