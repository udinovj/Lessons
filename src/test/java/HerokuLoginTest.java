import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners({AllureTestNg.class})

public class HerokuLoginTest {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        driver.get("https://the-internet.herokuapp.com/login");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("tomsmith");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("SuperSecretPassword!");


        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();


        String expectedMessage = "You logged into a secure area!";
        String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.flash.success"))).getText();


        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Тест пройшов успішно! Логін підтверджено.");
        } else {
            System.out.println("Тест провалено! Очікуване повідомлення: " + expectedMessage + ", але отримано: " + actualMessage);
        }


        driver.quit();
    }
}