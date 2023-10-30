package objects;

public class Stock {
    private String symbol;
    private String MT5;
    private String spread;
    private String tradeSize;
    private String margin;
    private String longSwapValue;
    private String shortSwapValue;
    private String limit;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMT5() {
        return MT5;
    }

    public void setMT5(String MT5) {
        this.MT5 = MT5;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }

    public String getTradeSize() {
        return tradeSize;
    }

    public void setTradeSize(String tradeSize) {
        this.tradeSize = tradeSize;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getLongSwapValue() {
        return longSwapValue;
    }

    public void setLongSwapValue(String longSwapValue) {
        this.longSwapValue = longSwapValue;
    }

    public String getShortSwapValue() {
        return shortSwapValue;
    }

    public void setShortSwapValue(String shortSwapValue) {
        this.shortSwapValue = shortSwapValue;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
