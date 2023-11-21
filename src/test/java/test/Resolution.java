package test;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public enum Resolution {
        FULLSCREEN {
            @Override
            public void apply(WebDriver driver) {
                driver.manage().window().maximize();
            }
        },
        MEDIUM {
            @Override
            public void apply(WebDriver driver) {
                driver.manage().window().setSize(new Dimension(1024, 768));
            }
        },
        SMALL {
            @Override
            public void apply(WebDriver driver) {
                driver.manage().window().setSize(new Dimension(800, 600));
            }
        };

        public abstract void apply(WebDriver driver);
    }