package objects;

public record Stock(String symbol, String MT5, String spread, String tradeSize,
                    String margin, String longSwapValue, String shortSwapValue,
                    String limit) {
}