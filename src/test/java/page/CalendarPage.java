package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CalendarPage extends BasePage {

    public CalendarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".economic-calendar>iframe")
    WebElement calendarFrame;

    @FindBy(xpath = "//a[contains(text(),'here')]")
    WebElement here;

    public CalendarFrame openCalendarFrame() {
        Actions actions = new Actions(driver);
        navigate(calendarFrame);
        driver.switchTo().frame(calendarFrame);
        return new CalendarFrame(driver);
    }

    public RiskWarningPage clickHere() {
        Actions actions = new Actions(driver);
        navigate(here);
        here.click();
        return new RiskWarningPage(driver);
    }
}
