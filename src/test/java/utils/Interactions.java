package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AbstractPage;
import page.StocksPage;

import java.time.Duration;

public class Interactions {
//    private final StocksPage stocksPage;
//    Actions actions = new Actions(driver);
//
//    public Interactions(StocksPage stocksPage) {
//        this.stocksPage = stocksPage;
//    }
//
//    public void navigateAndClick(AbstractPage page, WebElement element) {
//        navigate(page, element);
//        element.click();
//    }
//
//    public void navigateWaitAndClick(AbstractPage page ,WebElement element) {
//        waitForClickable(element);
//        navigate(page, element);
//        element.click();
//    }
//
//    public void navigate(AbstractPage page, WebElement element) {
//        page.getActions().scrollToElement(element).scrollByAmount(0, page.getRiskBlock().getSize().getHeight()).perform();
//    }
//
//    public void waitForClickable(WebElement element) {
//        new WebDriverWait(stocksPage.getDriver(), Duration.ofSeconds(5)).until(
//                driver -> ExpectedConditions.elementToBeClickable(element).apply(driver));
//    }
}