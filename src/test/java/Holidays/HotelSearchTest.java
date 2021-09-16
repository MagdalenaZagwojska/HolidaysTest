package Holidays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class HotelSearchTest {

    WebDriver driver;

    @Test
    public void hotelSearch() throws InterruptedException {


        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector("[class= 'select2-chosen']")).click();
        driver.findElement(By.cssSelector("[id = 'select2-drop'] > div > input")).sendKeys("Dubai");
        driver.findElement(By.cssSelector("span[class='select2-match']")).click();

        driver.findElement(By.cssSelector("input[name='checkin']")).click();
        driver.findElement(By.cssSelector("input[name='checkin']")).sendKeys("16/09/2021");

        driver.findElement(By.cssSelector("input[name='checkout']")).click();
        driver.findElement(By.cssSelector("input[name='checkout']")).sendKeys("16/10/2021");

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childInput")).clear();
        driver.findElement(By.id("childInput")).sendKeys("3");


    }

}
