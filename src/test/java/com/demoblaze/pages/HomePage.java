package com.demoblaze.pages;

import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//h3[@class='price-container']")
    public WebElement priceText;
    @FindBy(linkText = "Add to cart")
    public WebElement addToCartBtn;


    public void verifyLogin(){
        String actualUsername=nameofuser.getText();
        String expectedUsername= ConfigurationReader.get("username");
        Assert.assertTrue(actualUsername.contains(expectedUsername));
    }
    public void verifyLogin(String expectedUsername){
        String actualUsername=nameofuser.getText();
        Assert.assertTrue(actualUsername.contains(expectedUsername));
    }

    public int addProduct(String product, String category){
        WebElement categoryTab = Driver.get().findElement(By.linkText(category));
        BrowserUtils.waitForClickablility(categoryTab,5).click();
        WebElement productItem = Driver.get().findElement(By.linkText(product));
        BrowserUtils.scrollToElement(productItem);
        BrowserUtils.waitForClickablility(productItem,5).click();

//        System.out.println("priceText = " + priceText.getText());
        String [] arrayAmount=priceText.getText().split(" ");
//        System.out.println(Arrays.toString(arrayAmount));
//        System.out.println("arrayAmount[0].substring(1) = " + arrayAmount[0].substring(1));
        int listPrice= Integer.parseInt(arrayAmount[0].substring(1));
        System.out.println("listPrice = " + listPrice);

        addToCartBtn.click();
        BrowserUtils.waitFor(2);
        Alert alert=Driver.get().switchTo().alert();
        alert.accept();

        homeModule.click();

        return listPrice;

    }


}
