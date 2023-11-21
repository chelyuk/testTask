package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StockDetailsPage extends BasePage {
    private static final String CELL = "//tr[not(@class='visible-xs')]//td[normalize-space(.) = '%s']//following-sibling::td";

    protected StockDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getCell(String parameter) {
        String selector = String.format(CELL, parameter);
        return this.driver.findElement(By.xpath(selector));
    }

    public String getCellData(String parameter) {
        return getCell(parameter).getText();
    }
}
