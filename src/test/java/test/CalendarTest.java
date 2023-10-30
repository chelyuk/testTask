package test;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import page.CalendarFrame;
import page.HomePage;
import page.RiskDisclosurePage;
import utils.Formatters;


import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CalendarTest extends BaseClass {

    private final Formatters formatters = new Formatters();

    @Test(description = "Test Calendar functionality")
    public void calendarTest() {
        SoftAssert softAssert = new SoftAssert();
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

        softAssert.assertEquals(calendar.getDates().get(0), formatters.dateFormatter(yesterday));
        softAssert.assertEquals(calendar.getDates().get(1), formatters.dateFormatter(today));
        softAssert.assertEquals(calendar.getDates().get(2), formatters.dateFormatter(tomorrow));
        softAssert.assertEquals(calendar.getDates().get(3), formatters.weekFormatter(weekStart, weekEnd));
        softAssert.assertAll();
        assertThat(document.getDocumentName(), containsString("XM-Risk-Disclosures-for-Financial-Instruments.pdf"));
    }
}
