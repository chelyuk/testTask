package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CalendarFrame extends AbstractPage {

    List<String> timeList = new ArrayList<>();

    protected CalendarFrame(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    @FindBy(css="li.saveSpace")
    WebElement timeSelectButton;

    @FindBy(id = "widgetFieldDateRange")
    WebElement dateRange;

    @FindBy(id = "timeFrame_yesterday")
    WebElement yesterdayButton;

    @FindBy(id = "timeFrame_today")
    WebElement todayButton;

    @FindBy(id = "timeFrame_tomorrow")
    WebElement tomorrowButton;

    @FindBy(id = "timeFrame_thisWeek")
    WebElement thisWeekButton;

    public CalendarFrame selectTimeframe(String timeFrame, Boolean smallCalendar) {
        WebElement dateButton = null;
        if (smallCalendar) {
            timeSelectButton.click();
        }
        switch (timeFrame) {
            case "yesterday":
                dateButton = yesterdayButton;
                break;
            case "today":
                dateButton = todayButton;
                break;
            case "tomorrow":
                dateButton = tomorrowButton;
                break;
            case "this week":
                dateButton = thisWeekButton;
        }
        dateButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                driver -> ExpectedConditions.invisibilityOfElementLocated(By.id("economicCalendarLoading")).apply(driver));
        timeList.add(dateRange.getText());
        return this;
    }

    public CalendarPage returnToCalendarPage() {
        driver.switchTo().defaultContent();
        return new CalendarPage(driver);
    }
    public List<String> getDates() {
        return timeList;
    }
}
