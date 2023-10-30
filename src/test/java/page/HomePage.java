package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://xm.com/";
    Actions actions = new Actions(driver);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".toggle-bar>.toggleLeftNav")
    WebElement menu;

    @FindBy(className = "main_nav_research")
    WebElement researchAndEducation;

    @FindBy(xpath = "//a[contains(@class, 'navbar-nav__toggleArrow')]/i[contains(@class,'fa-pie-chart')]")
    WebElement researchAndEducationSmall;

    @FindBy(xpath = "//a[contains(text(), 'Economic Calendar')]")
    WebElement economicCalendar;

    @FindBy(css = "i.fa-calendar")
    WebElement economicCalendarSmall;

    @FindBy(xpath = "//button[contains(text(), 'ACCEPT ALL')]")
    WebElement closePrivacyPopUpButton;

    @FindBy(id = "risk-block")
    WebElement riskBlock;

    @FindBy(xpath = "//a[normalize-space(text()) = 'Trading']")
    WebElement tradingMenu;

    @FindBy(xpath = "//a[@href='#tradingMenu']//span[normalize-space(text()) = 'Trading']")
    WebElement tradingMenuSmall;

    @FindBy(xpath = "//div[@class='block']//a[normalize-space(text()) = 'Stocks']")
    WebElement stocksLink;

    @FindBy(xpath = "//span[normalize-space(text()) = 'Stocks']")
    WebElement stocksLinkSmall;

    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public HomePage showResearchAndEducation(Boolean small) {
        if (small) {
            menu.click();
            researchAndEducation = researchAndEducationSmall;
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    driver -> ExpectedConditions.elementToBeClickable(researchAndEducation).apply(driver));
            actions.scrollToElement(researchAndEducation).scrollByAmount(0, riskBlock.getSize().getHeight()).perform();
        }
        researchAndEducation.click();
        return this;
    }

    public HomePage showTrading(Boolean small) {
        if (small) {
            menu.click();
            navigateWaitAndClick(tradingMenuSmall);
        } else {
            tradingMenu.click();
        }
        return this;
    }

    public StocksPage clickStocks(Boolean small) {
        if (small) {
            navigateWaitAndClick(stocksLinkSmall);
        } else {
            stocksLink.click();
        }
        return new StocksPage(driver);
    }

    public CalendarPage clickEconomicCalendar(Boolean small) {
        if (small) {
            economicCalendar = economicCalendarSmall;
        }
        actions.scrollToElement(economicCalendar).scrollByAmount(0, riskBlock.getSize().getHeight()).perform();
        economicCalendar.click();
        return new CalendarPage(driver);
    }

    public HomePage closePrivacyPopUp() {
        closePrivacyPopUpButton.click();
        return this;
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
