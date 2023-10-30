package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StocksPage extends AbstractPage {
    Actions actions = new Actions(driver);

    protected StocksPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AbstractPage openPage() {
        return this;
    }

    @FindBy(xpath = "//button[contains(@data-value, 'Norway')]")
    WebElement norwayFilterButton;

    @FindBy(id = "risk-block")
    WebElement riskBlock;

    public WebElement tableCell(String symbol, int columnNumber) {
        String selector = String.format("//td[normalize-space(text()) = '%s']//..//td[%d]", symbol, columnNumber);
        return this.driver.findElement(By.xpath(selector));
    }

    public String getCellData(String symbol, int columnNumber) {
        return tableCell(symbol, columnNumber).getText();
    }

    public WebElement readMoreButton(String symbol) {
        String selector = String.format("//td[normalize-space(text()) = '%s']//..//a", symbol);
        return this.driver.findElement(By.xpath(selector));
    }

    public StocksPage filterNorway() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                driver -> ExpectedConditions.elementToBeClickable(norwayFilterButton).apply(driver));
        actions.scrollToElement(norwayFilterButton).scrollByAmount(0, riskBlock.getSize().getHeight()).perform();
        norwayFilterButton.click();
        return this;
    }

    public StockDetailsPage openDetails(String symbol) {
        WebElement button = readMoreButton(symbol);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                driver -> ExpectedConditions.elementToBeClickable(button).apply(driver));
        actions.scrollToElement(button).scrollByAmount(0, riskBlock.getSize().getHeight()).perform();
        button.click();
        return new StockDetailsPage(driver);
    }

//    private StocksPage getTableData() {
//        List<WebElement> rows = table.findElements(By.tagName("tr"));
//
//        // Prepare list to store all row data
//        List<List<String>> tableData = new ArrayList<>();
//
//        for (WebElement row : rows) {
//            // Get columns from the current row
//            List<WebElement> cols = row.findElements(By.tagName("td"));
//
//            // Prepare list to store the row data
//            List<String> rowData = new ArrayList<>();
//
//            for (WebElement col : cols) {
//                rowData.add(col.getText());
//            }
//
//            // Add the row data to the list of table data
//            tableData.add(rowData);
//            return tableData;
//        }
//    }
}
