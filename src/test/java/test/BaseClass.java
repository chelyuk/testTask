package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {
    protected WebDriver driver;
    Boolean smallCalendar = Boolean.FALSE;
    Boolean smallHomePage = Boolean.FALSE;
    Boolean smallStocksPage = Boolean.FALSE;

    @BeforeMethod(alwaysRun = true)
    @Parameters("resolution")
    public void browserSetup(String resolutionString) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        Resolution resolution = Resolution.valueOf(resolutionString.toUpperCase());
        switch (resolution) {
            case FULLSCREEN -> resolution.apply(driver);
            case MEDIUM -> {
                smallCalendar = Boolean.TRUE;
                smallStocksPage = Boolean.TRUE;
                resolution.apply(driver);
            }
            case SMALL -> {
                smallHomePage = Boolean.TRUE;
                smallStocksPage = Boolean.TRUE;
                resolution.apply(driver);
            }
            default -> throw new IllegalStateException("Unexpected value: " + resolution);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void browserShutDown() {
        driver.quit();
        driver = null;
    }
}
