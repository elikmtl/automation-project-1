import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class AutomationProject1{
    
    WebDriver driver = null;
    @Test
    public void tes1() throws InterruptedException {

      try{
          driver = new ChromeDriver();
          driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


          String expectedTitle = "Welcome to Duotify!";
          String actualTitle = driver.getTitle();
          Assert.assertEquals(actualTitle, expectedTitle);

          driver.findElement(By.id("hideLogin")).click();

          Faker faker = new Faker();

          String username = faker.name().username();
          driver.findElement(By.name("username")).sendKeys(username);

          String firstName = faker.name().firstName();
          driver.findElement(By.name("firstName")).sendKeys(firstName);

          String lastName = faker.name().lastName();
          driver.findElement(By.name("lastName")).sendKeys(lastName);

          String email = faker.internet().emailAddress();
          driver.findElement(By.name("email")).sendKeys(email);
          driver.findElement(By.name("email2")).sendKeys(email);

          String password = faker.internet().password();
          driver.findElement(By.name("password")).sendKeys(password);
          driver.findElement(By.name("password2")).sendKeys(password);

          driver.findElement(By.name("registerButton")).click();

          String currentUrl = driver.getCurrentUrl();
          String actualUrl = "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?";
          Assert.assertEquals(currentUrl, actualUrl);

          String actualFirstAndLast = driver.findElement(By.id("nameFirstAndLast")).getText();
          String expectedFirstAndLast = firstName + " " + lastName;
          Assert.assertEquals(actualFirstAndLast, expectedFirstAndLast);

          driver.findElement(By.id("nameFirstAndLast")).click();
          driver.findElement(By.id("rafael")).click();

          String currentlLogoutYrl = driver.getCurrentUrl();
          String expectedlLogotUrl = "http://duotify.us-east-2.elasticbeanstalk.com/register.php";

          driver.findElement(By.name("loginUsername")).sendKeys(username);
          driver.findElement(By.name("loginPassword")).sendKeys(password);
          driver.findElement(By.name("loginButton")).click();

          Thread.sleep(2000);

          String pageSource = driver.getPageSource();
          Assert.assertTrue(pageSource.contains("You Might Also Like"));

          driver.findElement(By.id("nameFirstAndLast")).click();
          driver.findElement(By.id("rafael")).click();

          // If Log in button is displayed, it means I am logged out

          Assert.assertTrue(driver.findElement(By.name("loginButton")).isDisplayed());

      }finally {
          driver.quit();
      }




    }


}
