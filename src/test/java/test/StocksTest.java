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
        StocksPage stocksPage = page.
                showTrading(smallHomePage).
                clickStocks(smallHomePage).
                filterNorway().expandRow(symbol, smallStocksPage);

        Stock stockMainTable = new Stock();

        saveData(symbol, stockMainTable, stocksPage);

        StockDetailsPage stockDetailsPage = stocksPage.openDetails(symbol, smallStocksPage);

        Stock stockDetailedTable = new Stock();

        saveDataDetailedPage(stockDetailedTable, stockDetailsPage);

        assertThat(stockMainTable.getSymbol(), containsString(stockDetailedTable.getSymbol()));
        assertThat(stockMainTable.getMT5(), equalTo(stockDetailedTable.getMT5()));
        assertThat(stockMainTable.getSpread(), equalTo(stockDetailedTable.getSpread()));
        assertThat(stockMainTable.getTradeSize(), equalTo(stockDetailedTable.getTradeSize()));
        assertThat(stockMainTable.getMargin(), equalTo(stockDetailedTable.getMargin()));
        assertThat(stockMainTable.getLongSwapValue(), equalTo(stockDetailedTable.getLongSwapValue()));
        assertThat(stockMainTable.getShortSwapValue(), equalTo(stockDetailedTable.getShortSwapValue()));
        assertThat(stockMainTable.getLimit(), equalTo(stockDetailedTable.getLimit()));
    }

    private static void saveDataDetailedPage(Stock stockDetailedTable, StockDetailsPage stockDetailsPage) {
        stockDetailedTable.setSymbol(stockDetailsPage.getCellData("Description"));
        stockDetailedTable.setMT5(stockDetailsPage.getCellData("Symbols"));
        stockDetailedTable.setSpread(stockDetailsPage.getCellData("Spread as low as (quote currency)"));
        stockDetailedTable.setTradeSize(stockDetailsPage.getCellData("Min/Max Trade Size"));
        stockDetailedTable.setMargin(stockDetailsPage.getCellData("Margin requirement"));
        stockDetailedTable.setLongSwapValue(stockDetailsPage.getCellData("Swap Value in Margin Currency Long"));
        stockDetailedTable.setShortSwapValue(stockDetailsPage.getCellData("Swap Value in Margin Currency Short"));
        stockDetailedTable.setLimit(stockDetailsPage.getCellData("Limit and Stop Levels**"));
    }

    private void saveData(String symbol, Stock stockMainTable, StocksPage stocksPage) {
        stockMainTable.setSymbol(stocksPage.getCellData(symbol, 1, smallStocksPage));
        stockMainTable.setMT5(stocksPage.getCellData(symbol, 2, smallHomePage));
        stockMainTable.setSpread(stocksPage.getCellData(symbol, 3, smallHomePage));
        stockMainTable.setTradeSize(stocksPage.getCellData(symbol, 4, smallHomePage));
        stockMainTable.setMargin(stocksPage.getCellData(symbol, 5, smallHomePage));
        stockMainTable.setLongSwapValue(stocksPage.getCellData(symbol, 6, smallHomePage));
        stockMainTable.setShortSwapValue(stocksPage.getCellData(symbol, 7, smallHomePage));
        stockMainTable.setLimit(stocksPage.getCellData(symbol, 8, smallHomePage));
    }
}
