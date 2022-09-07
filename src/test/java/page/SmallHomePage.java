package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.security.PublicKey;

public class SmallHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://xm.com/";

    public SmallHomePage(WebDriver driver) {
        super(driver);
    }

//    @FindBy(css = ".toggle-bar>.toggleLeftNav")
//    public WebElement menu;

    @FindBy(xpath = "//button[contains(text(), 'ACCEPT ALL')]")
    WebElement closePrivacyPopUpButton;

//    @FindBy(xpath = "//a[contains(@class, 'navbar-nav__toggleArrow')]/i[contains(@class,'fa-pie-chart')]")
//    public WebElement reserchAndEducation;

//    @FindBy(css = "i.fa-calendar")
//    public WebElement economicCalendar;

    public SmallHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public SmallHomePage showResearchAndEducation() {
        WebElement menu = driver.findElement(By.cssSelector(".toggle-bar>.toggleLeftNav"));
        menu.click();
        WebElement researchAndEducation = driver.findElement(By.
                xpath("//a[contains(@class, 'navbar-nav__toggleArrow')]/i[contains(@class,'fa-pie-chart')]"));
        researchAndEducation.click();
        return this;
    }
    public CalendarPage clickEconomicCalendar() {
        WebElement economicCalendar = driver.findElement(By.cssSelector("i.fa-calendar"));
        economicCalendar.click();
        return new CalendarPage(driver);
    }

    public SmallHomePage closePrivacyPopUp() {
        closePrivacyPopUpButton.click();
        return this;
    }
}
