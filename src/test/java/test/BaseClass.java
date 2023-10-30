package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
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
    public void browserSetup(String resolution) {
        Dimension dimension;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        switch(resolution) {
            case "fullscreen":
                driver.manage().window().maximize();
                break;
            case "1024x768":
                smallCalendar = Boolean.TRUE;
                smallStocksPage = Boolean.TRUE;
                dimension = new Dimension(1024,768);
                driver.manage().window().setSize(dimension);
                break;
            case "800x600":
                smallHomePage = Boolean.TRUE;
                dimension = new Dimension(800,600);
                driver.manage().window().setSize(dimension);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void browserShutDown() {
        driver.quit();
        driver = null;
    }
}
