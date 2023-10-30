package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected final int WAIT_TIMEOUT_SECONDS = 5;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TIMEOUT_SECONDS), this);
    }
}
