package test;


import objects.Stock;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.StockDetailsPage;
import page.StocksPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class StocksTest extends BaseClass {

    @DataProvider(name = "symbol")
    public Object[][] dataProviderFunction() {
        return new Object[][]{{"Storebrand (STB.OL)"}};
    }

    @Test(description = "Test Stock functionality", dataProvider = "symbol")
    public void stockTest(String symbol) {
        HomePage page = new HomePage(driver).openPage().closePrivacyPopUp();
        StocksPage stocksPage = page.
                showTrading(smallHomePage).
                clickStocks(smallHomePage).
                filterNorway().expandRow(symbol, smallStocksPage);

        Stock stockMainTable = new Stock(stocksPage.getCellData(symbol, 1, smallStocksPage),
                stocksPage.getCellData(symbol, 2, smallHomePage),
                stocksPage.getCellData(symbol, 3, smallHomePage),
                stocksPage.getCellData(symbol, 4, smallHomePage),
                stocksPage.getCellData(symbol, 5, smallHomePage),
                stocksPage.getCellData(symbol, 6, smallHomePage),
                stocksPage.getCellData(symbol, 7, smallHomePage),
                stocksPage.getCellData(symbol, 8, smallHomePage));

        StockDetailsPage stockDetailsPage = stocksPage.openDetails(symbol, smallStocksPage);

        Stock stockDetailedTable = new Stock(stockDetailsPage.getCellData("Description"),
                stockDetailsPage.getCellData("Symbols"),
                stockDetailsPage.getCellData("Spread as low as (quote currency)"),
                stockDetailsPage.getCellData("Min/Max Trade Size"),
                stockDetailsPage.getCellData("Margin requirement"),
                stockDetailsPage.getCellData("Swap Value in Margin Currency Long"),
                stockDetailsPage.getCellData("Swap Value in Margin Currency Short"),
                stockDetailsPage.getCellData("Limit and Stop Levels**"));

        assertThat(stockMainTable.symbol(), containsString(stockDetailedTable.symbol()));
        assertThat(stockMainTable.MT5(), equalTo(stockDetailedTable.MT5()));
        assertThat(stockMainTable.spread(), equalTo(stockDetailedTable.spread()));
        assertThat(stockMainTable.tradeSize(), equalTo(stockDetailedTable.tradeSize()));
        assertThat(stockMainTable.margin(), equalTo(stockDetailedTable.margin()));
        assertThat(stockMainTable.longSwapValue(), equalTo(stockDetailedTable.longSwapValue()));
        assertThat(stockMainTable.shortSwapValue(), equalTo(stockDetailedTable.shortSwapValue()));
        assertThat(stockMainTable.limit(), equalTo(stockDetailedTable.limit()));
    }
}
