package Holidays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignUpWithNoValuesTest {

    WebDriver driver;

    @Test
    public void SignUpWithNoValues()  {


        driver = DriverFactory.getDriver();
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







    }

}
