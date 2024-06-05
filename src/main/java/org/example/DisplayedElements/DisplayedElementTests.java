package org.example.DisplayedElements;

import org.example.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DisplayedElementTests extends BaseTests {
    @Test
    public void displayedElements(){
        webDriver.get("https://ex-fs.net/");
        WebElement element = webDriver.findElement(By.className("TitleScrollBox"));
        boolean isDisplayed = element.isDisplayed();
        Assert.assertTrue(isDisplayed, "The element should be displayed.");
    }

}
