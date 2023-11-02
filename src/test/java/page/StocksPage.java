package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StocksPage extends BasePage {

    protected StocksPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//button[contains(@data-value, 'Norway')]")
    WebElement norwayFilterButton;

    public WebElement symbolCell(String symbol) {
        String selector = String.format("//td[normalize-space(text()) = '%s']", symbol);
        return this.driver.findElement(By.xpath(selector));
    }

    public WebElement tableCell(String symbol, int columnNumber) {
        String selector = String.format("//td[normalize-space(text()) = '%s']//..//td[%d]", symbol, columnNumber);
        return this.driver.findElement(By.xpath(selector));
    }

    public List<WebElement> tableCells(String symbol) {
        String selector = String.format("//td[normalize-space(text()) = '%s']//..//td[not(contains(@style, 'display: none'))]", symbol);
        return this.driver.findElements(By.xpath(selector));
    }

    public WebElement tableAdditionalCell(String symbol, int additionalColumnNumber) {
        String selector = String.format("(//td[normalize-space(text()) = '%s']//..//following-sibling::tr[1]//span[@class='dtr-data' and not(child::a)])[%d]", symbol, additionalColumnNumber);
        return this.driver.findElement(By.xpath(selector));
    }

    public String getCellData(String symbol, int columnNumber, Boolean small) {
        int visibleColumns = tableCells(symbol).size();
        if (small && columnNumber > visibleColumns) {
            return tableAdditionalCell(symbol, columnNumber - visibleColumns).getText();
        }
        return tableCell(symbol, columnNumber).getText();
    }

    public WebElement readMoreButton(String symbol) {
        String selector = String.format("//td[normalize-space(text()) = '%s']//..//a[normalize-space(text() = 'Read More')]", symbol);
        return this.driver.findElement(By.xpath(selector));
    }

    public WebElement readMoreButtonSmall(String symbol) {
        String selector = String.format("//td[normalize-space(text()) = '%s']//..//following-sibling::tr[1]//a", symbol);
        return this.driver.findElement(By.xpath(selector));
    }

    public StocksPage filterNorway() {
        navigateAndClick(norwayFilterButton);
        return this;
    }

    public StocksPage expandRow(String symbol,Boolean small) {
        if (small) {
            navigateAndClick(symbolCell(symbol));
        }
        return this;
    }

    public StockDetailsPage openDetails(String symbol, Boolean smallPage) {
        WebElement button;
        if (smallPage) {
            button = readMoreButtonSmall(symbol);
        } else {
            button = readMoreButton(symbol);
        }
        navigateWaitAndClick(button);
        return new StockDetailsPage(driver);
    }
}
