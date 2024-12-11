package pl.edu.pjatk.MPR_Spring_PRJ.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditFormPage {
    WebDriver driver;

    @FindBy(id = "id")
    private WebElement idInput;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "number")
    private WebElement numberInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public EditFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // <-inicializuje adnotacji FindVy
    }

    public EditFormPage open(){
        this.driver.get("http://localhost:8080/editSchool?id=1");
        return this;
    }

    public EditFormPage fillIdInput(String id){
        this.idInput.clear();
        this.idInput.sendKeys(id);
        return this;
    }

    public EditFormPage fillNameInput(String name){
        this.nameInput.clear();
        this.nameInput.sendKeys(name);
        return this;
    }

    public EditFormPage fillNumberInput(String number){
        this.numberInput.clear();
        this.numberInput.sendKeys(number);
        return this;
    }

    public ViewAllPage submitForm(){
        this.submitButton.submit();

        return new ViewAllPage(driver);
    }
}
