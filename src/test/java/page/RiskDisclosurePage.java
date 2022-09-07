package page;

import org.openqa.selenium.WebDriver;

public class RiskDisclosurePage extends AbstractPage {
    @Override
    protected AbstractPage openPage() {
        return this;
    }
    public RiskDisclosurePage(WebDriver driver) {
        super(driver);
    }

    public String getDocumentName() {
        return driver.getCurrentUrl();
    }
}
