package Holidays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest {

    WebDriver driver;

    @Test
    public void signUpTest()  {


        driver = DriverFactory.getDriver();
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







    }

}
