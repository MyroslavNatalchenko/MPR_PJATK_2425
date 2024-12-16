package pl.edu.pjatk.MPR_Spring_PRJ.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class EditFormTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void testEditForm() throws InterruptedException {
        EditFormPage editFormPage = new EditFormPage(driver);
        driver.get("http://localhost:8080/view/all");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/editSchool')]"))
        );

        editButton.click();

        editFormPage.fillIdInput("1")
                .fillNameInput("TestSchool")
                .fillNumberInput("404");
        ViewAllPage viewAllPage = editFormPage.submitForm();
        assertEquals("All Schools:", viewAllPage.getHeader());
    }
}
