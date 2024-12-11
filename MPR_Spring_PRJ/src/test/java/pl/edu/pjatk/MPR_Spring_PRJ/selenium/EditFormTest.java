package pl.edu.pjatk.MPR_Spring_PRJ.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class EditFormTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void testEditForm() {
        EditFormPage editFormPage = new EditFormPage(driver);
        editFormPage.open()
                .fillIdInput("1")
                .fillNameInput("TestSchool")
                .fillNumberInput("404");
        ViewAllPage viewAllPage = editFormPage.submitForm();
        assertEquals("All Schools:", viewAllPage.getHeader());
    }
}
