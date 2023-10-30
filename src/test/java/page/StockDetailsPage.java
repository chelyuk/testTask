package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StockDetailsPage extends AbstractPage{
    protected StockDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public WebElement getCell(String parameter) {
        String selector = String.format("//tr[not(@class='visible-xs')]//td[normalize-space(.) = '%s']//following-sibling::td", parameter);
        return this.driver.findElement(By.xpath(selector));
    }

    public String getCellData(String parameter) {
        return getCell(parameter).getText();
    }
}
