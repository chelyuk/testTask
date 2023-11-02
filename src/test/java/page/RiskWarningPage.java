package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class RiskWarningPage extends BasePage {

    public RiskWarningPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//strong[contains(text(),'Risk Disclosure')]/../a[contains(text(),'here')]")
    WebElement here;

    @FindBy(id = "risk-block")
    WebElement riskBlock;

    public RiskDisclosurePage clickHere() {
        Actions actions = new Actions(driver);
        actions.scrollToElement(here).scrollByAmount(0,riskBlock.getSize().getHeight()).perform();
        here.click();
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        return new RiskDisclosurePage(driver);
    }
}
