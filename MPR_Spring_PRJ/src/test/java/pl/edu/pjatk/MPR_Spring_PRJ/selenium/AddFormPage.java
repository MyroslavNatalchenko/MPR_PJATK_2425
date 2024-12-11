package pl.edu.pjatk.MPR_Spring_PRJ.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFormPage {
    WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "number")
    private WebElement numberInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public AddFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // <-inicializuje adnotacji FindVy
    }

    public AddFormPage open(){
        this.driver.get("http://localhost:8080/addSchool");
        return this;
    }

    public AddFormPage fillNameInput(String name){
        this.nameInput.sendKeys(name);
        return this;
    }

    public AddFormPage fillNumberInput(String number){
        this.numberInput.sendKeys(number);
        return this;
    }

    public ViewAllPage submitForm(){
        this.submitButton.submit();

        return new ViewAllPage(driver);
    }
}
