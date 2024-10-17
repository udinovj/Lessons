import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCartTestLiteCart {
    public static void main(String[] args) {
        // Вказуємо шлях до WebDriver (замініть шлях відповідно до вашої системи)
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Створюємо об'єкт WebDriverWait для очікувань
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Переходимо на тестовий сайт
        driver.get("http://litecart.net/");

        // Очікуємо, поки головна сторінка завантажиться, і шукаємо перший товар на сторінці
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product.column.shadow.hover-light"))).click();

        // Очікуємо появу кнопки "Add to cart" і натискаємо на неї
        wait.until(ExpectedConditions.elementToBeClickable(By.name("add_cart_product"))).click();

        // Очікуємо появу підтвердження додавання товару
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".quantity"), "1"));

        // Перевіряємо, чи кількість товарів в корзині збільшилася
        String expectedMessage = "1";
        String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quantity"))).getText();

        // Перевіряємо, чи збігається очікуване повідомлення з фактичним
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Тест пройшов успішно! Товар додано в корзину.");
        } else {
            System.out.println("Тест провалено! Очікувано: " + expectedMessage + ", але отримано: " + actualMessage);
        }

        // Закриваємо браузер
        driver.quit();
    }
}