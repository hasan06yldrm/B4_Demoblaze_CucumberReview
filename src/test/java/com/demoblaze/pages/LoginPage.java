package com.demoblaze.pages;

import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "login2")
    public WebElement loginHomePage;
    @FindBy(id = "loginusername")
    public WebElement input_loginusername;
    @FindBy(id = "loginpassword")
    public WebElement input_loginpassword;
    @FindBy(css = "[onclick='logIn()']")
    public WebElement submitBtn;

    public void login() {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");

        loginHomePage.click();
        input_loginusername.sendKeys(username);
        input_loginpassword.sendKeys(password);
        submitBtn.click();
        BrowserUtils.waitFor(2);

    }

    public void login(String username, String password) {
        loginHomePage.click();
        input_loginusername.sendKeys(username);
        input_loginpassword.sendKeys(password);
        submitBtn.click();
        BrowserUtils.waitFor(2);

    }

    public void verifyWithPopUpMessage(String expectedMessage){
        Alert alert= Driver.get().switchTo().alert();
        String actualMessage=alert.getText();
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertEquals(expectedMessage,actualMessage);
    }


}
