package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://xm.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "main_nav_research")
    WebElement researchAndEducation;

    @FindBy(xpath = "//a[contains(text(), 'Economic Calendar')]")
    WebElement economicCalendar;

    @FindBy(xpath = "//button[contains(text(), 'ACCEPT ALL')]")
    WebElement closePrivacyPopUpButton;

    @FindBy(id = "risk-block")
    WebElement riskBlock;

    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public HomePage showResearchAndEducation() {
        researchAndEducation.click();
        return this;
    }

    public CalendarPage clickEconomicCalendar() {
        Actions actions = new Actions(driver);
        actions.scrollToElement(economicCalendar);
        actions.scrollToElement(economicCalendar).scrollByAmount(0,riskBlock.getSize().getHeight()).perform();
        economicCalendar.click();
        return new CalendarPage(driver);
    }

    public HomePage closePrivacyPopUp() {
        closePrivacyPopUpButton.click();
        return this;
    }
}
