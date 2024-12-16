package pl.edu.pjatk.MPR_Spring_PRJ.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AddFormTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void testAddForm() {
        AddFormPage addFormPage = new AddFormPage(driver);
        addFormPage.open()
                .fillNameInput("TestSchool")
                .fillNumberInput("404");
        ViewAllPage viewAllPage = addFormPage.submitForm();
        assertEquals("All Schools:", viewAllPage.getHeader());
    }
}
