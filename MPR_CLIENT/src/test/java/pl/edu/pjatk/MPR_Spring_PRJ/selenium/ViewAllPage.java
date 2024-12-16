package pl.edu.pjatk.MPR_Spring_PRJ.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewAllPage {
    private WebDriver driver;

    @FindBy(tagName = "h1")
    private WebElement header;

    public ViewAllPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeader() {
        return this.header.getText();
    }
}
