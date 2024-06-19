package org.example.Homework3;

import org.example.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitersTests extends BaseTests {
    @Test
    public void prozzoroTest() {
            String searchValue = "тумби";
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
            webDriver.get("https://prozorro.gov.ua/uk");
            WebElement searchInput = webDriver.findElement(By.className("search-text__input"));
            searchInput.clear();
            searchInput.sendKeys(searchValue);
            searchInput.sendKeys(Keys.ENTER);
            int startNum = 0;
            int endNum = 25;
            int startCost = 0;
            int endCost = 9;


            List<WebElement> totalResults = webDriver.findElements(By.className("item-title__title"));
            WebElement currentResult = totalResults.get(4);
            new Actions(webDriver).moveToElement(currentResult).build().perform();
            List<WebElement> totalName = webDriver.findElements(By.className("item-title__title"));
            List<WebElement> totalCost = webDriver.findElements(By.xpath("//p[@class='text-color--green app-price__amount']"));
            List<WebElement> totalStatus = webDriver.findElements(By.xpath("//span[@class='getter _setter __v_isRef __v_isReadonly effect _cacheable']"));
            List<WebElement> totalCompanyName = webDriver.findElements(By.xpath("//div[@class='search-result-card__description']"));
            String currentName = totalName.get(4)
                    .getText().toLowerCase().trim();
            String currentCost = totalCost.get(4)
                    .getText().substring(startCost, endCost).trim();
            String currentStatus = totalStatus.get(4)
                    .getText().trim();
            String currentCompanyName = totalCompanyName
                    .get(4).getText().substring(startNum, endNum).trim();

            System.out.println(currentName);
            System.out.println(currentCost);
            System.out.println(currentStatus);
            System.out.println(currentCompanyName);

            currentResult.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='col-sm-6']")));

            String detailsName = webDriver.findElement(By.xpath("//div[@class='tender--head--title col-sm-9']"))
                    .getText().toLowerCase().trim();
            String detailsCost = webDriver.findElement(By.xpath("//div[@class='green tender--description--cost--number']//strong"))
                    .getText().substring(startCost, endCost).trim();
            String detailsStatus = webDriver.findElement(By.xpath("//span[@class='marked']"))
                    .getText().trim();
            String detailsCompanyName = webDriver.findElement(By.xpath("//table[@class='tender--customer margin-bottom']//td[@class='col-sm-6']"))
                    .getText().substring(startNum, endNum).trim();

            System.out.println(detailsName);
            System.out.println(detailsCost);
            System.out.println(detailsStatus);
            System.out.println(detailsCompanyName);

            assert currentName.equals(detailsName) : "Name does not match";
            assert currentCost.equals(detailsCost) : "Cost does not match";
            assert currentStatus.equals(detailsStatus) : "Status does not match";
            assert currentCompanyName.equals(detailsCompanyName) : "Company name does not match";
    }
}
