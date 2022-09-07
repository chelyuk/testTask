package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CalendarPage extends AbstractPage {

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".economic-calendar>iframe")
    WebElement calendarFrame;

    @FindBy(xpath = "//a[contains(text(),'here')]")
    WebElement here;

    @FindBy(id = "risk-block")
    WebElement riskBlock;


    public CalendarFrame openCalendarFrame() {
        Actions actions = new Actions(driver);
        actions.scrollToElement(calendarFrame).scrollByAmount(0, riskBlock.getSize().getHeight()).perform();
        driver.switchTo().frame(calendarFrame);
        return new CalendarFrame(driver);
    }

    public RiskWarningPage clickHere() {
        Actions actions = new Actions(driver);
        actions.scrollToElement(here).scrollByAmount(0,riskBlock.getSize().getHeight()).perform();
        here.click();
        return new RiskWarningPage(driver);
    }
}
