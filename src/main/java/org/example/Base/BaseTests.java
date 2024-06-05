package org.example.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTests {
    public WebDriver webDriver;
    @BeforeMethod
    public void initDriver()
    {
        webDriver = new ChromeDriver();
    }
    @AfterMethod
    public void quitDriver()
    {
        webDriver.quit();
    }
}