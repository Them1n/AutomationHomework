package org.example;

import org.example.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

class ProzorroSearchTestsHW5 extends BaseTests {

    @DataProvider(name = "positiveSearchWords")
    public Object[][] positiveSearchWords() {
        return new Object[][]{
                {"закупівля"},
                {"тендер"},
                {"договір"}
        };
    }

    @Test(dataProvider = "positiveSearchWords")
    public void testPositiveSearch(String searchWord) {
        webDriver.get("https://prozorro.gov.ua/");
        WebElement searchBox = webDriver.findElement(By.className("search-text__input"));
        searchBox.sendKeys(searchWord);
        searchBox.submit();
        String result = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".item-title__title")))
                .getText().trim().toLowerCase();
    }

    @Test
    public void testSearchWithValidInput() {
        webDriver.get("https://prozorro.gov.ua/");
        WebElement searchBox = webDriver.findElement(By.className("search-text__input"));
        searchBox.sendKeys("Бланки");
        searchBox.submit();
        String result = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".item-title__title")))
                .getText().trim().toLowerCase();
    }
    @Test
    public void testSearchResultsContainTerm() {
        String searchTerm = "закупівля";
        webDriver.get("https://prozorro.gov.ua/");
        WebElement searchBox = webDriver.findElement(By.className("search-text__input")); // replace with actual search box ID
        searchBox.sendKeys(searchTerm);
        searchBox.submit();

        // Verify that at least one search result contains the search term
        List<WebElement> searchResults = webDriver.findElements(By.cssSelector(".item-title__title")); // replace with actual search result item selector
        boolean containsSearchTerm = false;
        for (WebElement result : searchResults) {
            if (result.getText().toLowerCase().contains(searchTerm.toLowerCase())) {
                containsSearchTerm = true;
                break;
            }
        }
        Assert.assertTrue(containsSearchTerm, "None of the search results contain the search term.");
    }

    @Test
    public void testSearchWithIncorrectInput() {
        webDriver.get("https://prozorro.gov.ua/");
        WebElement searchBox = webDriver.findElement(By.className("search-text__input"));
        searchBox.sendKeys("TenderBender");
        searchBox.submit();

        Assert.assertTrue(webDriver.findElement(By.className("search-result__empty")).isDisplayed(), "Error message is not displayed.");
    }

    @Test
    public void testSearchWithInvalidInput() {
        webDriver.get("https://prozorro.gov.ua/");
        WebElement searchBox = webDriver.findElement(By.className("search-text__input"));
        searchBox.sendKeys("!!!!");
        searchBox.submit();

        Assert.assertTrue(webDriver.findElement(By.className("search-result__empty")).isDisplayed(), "No results message is not displayed.");
    }
}