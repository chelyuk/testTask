package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends AbstractPage {

    Actions actions = new Actions(driver);

    protected BasePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "risk-block")
    WebElement riskBlock;

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public void navigateAndClick(WebElement element) {
        navigate(element);
        element.click();
    }

    public void navigateWaitAndClick(WebElement element) {
        waitForClickable(element);
        navigate(element);
        element.click();
    }

    public void navigate(WebElement element) {
        actions.scrollToElement(element).scrollByAmount(0, riskBlock.getSize().getHeight()).perform();
    }

    public void waitForClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                driver -> ExpectedConditions.elementToBeClickable(element).apply(driver));
    }
}
