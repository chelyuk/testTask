package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import page.CalendarFrame;
import page.HomePage;
import page.RiskDisclosurePage;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class MyTest {
    private WebDriver driver;
    Boolean smallCalendar = Boolean.FALSE;
    Boolean smallHomePage = Boolean.FALSE;

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
                dimension = new Dimension(1024,768);
                driver.manage().window().setSize(dimension);
                break;
            case "800x600":
                smallHomePage = Boolean.TRUE;
                dimension = new Dimension(800,600);
                driver.manage().window().setSize(dimension);
        }
    }

    @Test(description = "MyTest task")
    public void myTest() {
        SoftAssert polite = new SoftAssert();
        HomePage page = new HomePage(driver).openPage().closePrivacyPopUp();
        CalendarFrame calendar = page.showResearchAndEducation(smallHomePage)
                .clickEconomicCalendar(smallHomePage)
                .openCalendarFrame();
        RiskDisclosurePage document = calendar
                .selectTimeframe("yesterday", smallCalendar)
                .selectTimeframe("today", smallCalendar)
                .selectTimeframe("tomorrow", smallCalendar)
                .selectTimeframe("this week", smallCalendar)
                .returnToCalendarPage()
                .clickHere()
                .clickHere();
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate tomorrow = today.plusDays(1);
        LocalDate weekStart = today.minusWeeks(1).with(DayOfWeek.SUNDAY);
        LocalDate weekEnd = today.with(DayOfWeek.SATURDAY);

        polite.assertEquals(calendar.getDates().get(0), dateFormatter(yesterday));
        polite.assertEquals(calendar.getDates().get(1), dateFormatter(today));
        polite.assertEquals(calendar.getDates().get(2), dateFormatter(tomorrow));
        polite.assertEquals(calendar.getDates().get(3), weekFormatter(weekStart, weekEnd));
        polite.assertAll();
        assertThat(document.getDocumentName(), containsString("XM-Risk-Disclosures-for-Financial-Instruments.pdf"));
    }

    @AfterMethod(alwaysRun = true)
    public void browserShutDown() {
        driver.quit();
        driver = null;
    }

    private String weekFormatter(LocalDate weekStart, LocalDate weekEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(weekStart) + " - " + formatter.format(weekEnd);
    }
    private String dateFormatter(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - dd/MM/yyyy");
        return formatter.format(date);
    }
}
