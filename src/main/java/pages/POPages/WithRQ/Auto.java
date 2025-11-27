package pages.POPages.WithRQ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Auto
{
    public static void main(String[] args)
    {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        //driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[3]/a")).click();
        //driver.findElement(By.xpath("//*[text()='Home']")).click();
        //driver.findElement(By.xpath("(//a[@class=\"nav-link dropdown-toggle \"])[1]")).click();
        //driver.findElement(By.xpath("(//*[@href=\"software-testing.php\"])[1]")).click();
        driver.findElement(By.xpath("(//*[@href=\"index.php\"])[1]")).click();
        //driver.findElement(By.xpath("(//a[@href=\"tel:+917983003640\"])[2]")).click();
        /// for entry in frame
        //WebElement frame_ele=(WebElement) driver.findElements(By.xpath("(//a[@href=\"tel:+917983003640\"])[2]"));
        //driver.switchTo().frame(frame_ele);
        //driver.findElement(By.xpath("(//a[@href=\"tel:+917983003640\"])[2]")).click();
        //driver.switchTo().defaultContent();
        //Thread.sleep("4000");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ele= driver.findElement(By.xpath("(//*[@class=\"default-btn btn\"])[1]"));
        ele.click();

    }
}