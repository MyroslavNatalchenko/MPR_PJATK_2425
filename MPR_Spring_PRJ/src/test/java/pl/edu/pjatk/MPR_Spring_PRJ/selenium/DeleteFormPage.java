package pl.edu.pjatk.MPR_Spring_PRJ.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteFormPage {
    WebDriver driver;

    @FindBy(id = "number")
    private WebElement numberInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public DeleteFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // <-inicializuje adnotacji FindVy
    }

    public DeleteFormPage fillNumberInput(String number){
        this.numberInput.clear();
        this.numberInput.sendKeys(number);
        return this;
    }

    public ViewAllPage submitForm(){
        this.submitButton.submit();

        return new ViewAllPage(driver);
    }
}
