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
        return new  Object[][]{{"Orkla ASA (ORK.OL)"}};
    }

    @Test(description = "Test Stock functionality", dataProvider = "symbol")
    public void stockTest(String symbol) {
        HomePage page = new HomePage(driver).openPage().closePrivacyPopUp();
        StocksPage stocksPage = page.showTrading().clickStocks().filterNorway();

        Stock stockMainTable = new Stock();

        stockMainTable.setSymbol(stocksPage.getCellData(symbol, 1));
        stockMainTable.setMT5(stocksPage.getCellData(symbol, 2));
        stockMainTable.setSpread(stocksPage.getCellData(symbol, 3));
        stockMainTable.setTradeSize(stocksPage.getCellData(symbol, 4));
        stockMainTable.setMargin(stocksPage.getCellData(symbol, 5));
        stockMainTable.setLongSwapValue(stocksPage.getCellData(symbol, 6));
        stockMainTable.setShortSwapValue(stocksPage.getCellData(symbol, 7));
        stockMainTable.setLimit(stocksPage.getCellData(symbol, 8));

        StockDetailsPage stockDetailsPage = stocksPage.openDetails(symbol);

        Stock stockDetailedTable = new Stock();

        stockDetailedTable.setSymbol(stockDetailsPage.getCellData("Description"));
        stockDetailedTable.setMT5(stockDetailsPage.getCellData("Symbols"));
        stockDetailedTable.setSpread(stockDetailsPage.getCellData("Spread as low as (quote currency)"));
        stockDetailedTable.setTradeSize(stockDetailsPage.getCellData("Min/Max Trade Size"));
        stockDetailedTable.setMargin(stockDetailsPage.getCellData("Margin requirement"));
        stockDetailedTable.setLongSwapValue(stockDetailsPage.getCellData("Swap Value in Margin Currency Long"));
        stockDetailedTable.setShortSwapValue(stockDetailsPage.getCellData("Swap Value in Margin Currency Short"));
        stockDetailedTable.setLimit(stockDetailsPage.getCellData("Limit and Stop Levels**"));

        assertThat(stockMainTable.getSymbol(), containsString(stockDetailedTable.getSymbol()));
        assertThat(stockMainTable.getMT5(), equalTo(stockDetailedTable.getMT5()));
        assertThat(stockMainTable.getSpread(), equalTo(stockDetailedTable.getSpread()));
        assertThat(stockMainTable.getTradeSize(), equalTo(stockDetailedTable.getTradeSize()));
        assertThat(stockMainTable.getMargin(), equalTo(stockDetailedTable.getMargin()));
        assertThat(stockMainTable.getLongSwapValue(), equalTo(stockDetailedTable.getLongSwapValue()));
        assertThat(stockMainTable.getShortSwapValue(), equalTo(stockDetailedTable.getShortSwapValue()));
        assertThat(stockMainTable.getLimit(), equalTo(stockDetailedTable.getLimit()));
    }
}
