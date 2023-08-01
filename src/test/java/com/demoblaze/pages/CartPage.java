package com.demoblaze.pages;

import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.Driver;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(xpath = "//button[.='Place Order']")
    public WebElement placeOrderBtn;

    @FindBy(id = "name")
    public WebElement name;
    @FindBy(id = "country")
    public WebElement country;
    @FindBy(id = "city")
    public WebElement city;
    @FindBy(id = "card")
    public WebElement credidCard;
    @FindBy(id = "month")
    public WebElement month;
    @FindBy(id = "year")
    public WebElement year;
    @FindBy(xpath = "//button[.='Purchase']")
    public WebElement purchaseBtn;


    public int removeProduct(String product) {
        cartModule.click();
        BrowserUtils.waitFor(2);

        String productPath = "//td[.='" + product + "']";
        String productPricePath = productPath + "/../td[3]";
        String deletePath = productPath + "/../td[4]/a";

        //get the price of the deleted product
        String priceText= Driver.get().findElement(By.xpath(productPricePath)).getText();
        System.out.println("priceText = " + priceText);

        //delete the product
        Driver.get().findElement(By.xpath(deletePath)).click();
        BrowserUtils.waitForVisibility(By.xpath(productPath),5);

        return Integer.parseInt(priceText);

    }

    public void fillForm(){
        Faker faker=new Faker();
        BrowserUtils.waitFor(1);
        name.sendKeys(faker.name().fullName());
        BrowserUtils.waitFor(1);
        country.sendKeys(faker.country().name());
        BrowserUtils.waitFor(1);
        city.sendKeys(faker.country().capital());
        BrowserUtils.waitFor(1);
        credidCard.sendKeys(faker.finance().creditCard());
        BrowserUtils.waitFor(1);
        month.sendKeys(String.valueOf(faker.number().numberBetween(1,12)));
        BrowserUtils.waitFor(1);
        year.sendKeys(String.valueOf(faker.number().numberBetween(2023,2030)));
        BrowserUtils.waitFor(1);

    }

    @FindBy(xpath = "//p[@class='lead text-muted ']")
    public WebElement confirmation;
    int actualAmount ;

    @FindBy(xpath = "//button[@class='confirm btn btn-lg btn-primary']")
    public WebElement ok_Btn;

    public void finishPurchase_logAmount(){
        BrowserUtils.waitFor(1);
        placeOrderBtn.click();
        BrowserUtils.waitFor(1);
        fillForm();
        BrowserUtils.waitFor(1);
        purchaseBtn.click();
        BrowserUtils.waitFor(1);

        String confirmationMessage= confirmation.getText();
        System.out.println("confirmationMessage = " + confirmationMessage);

        String [] confirmationArray=confirmationMessage.split("\n");

        actualAmount = Integer.parseInt(confirmationArray[1].split(" ")[1]);
        BrowserUtils.waitFor(1);
        ok_Btn.click();
    }

    public void verifyPurchaseAmount(int expectedAmount){
        System.out.println("actualAmount = " + actualAmount);
        Assert.assertEquals(expectedAmount,actualAmount);
    }
}
