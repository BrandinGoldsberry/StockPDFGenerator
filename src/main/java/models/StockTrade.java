package models;

import org.json.simple.*;

public class StockTrade {
	private TradeType tradeType;
	private String stockSymbol;
	private long shareCount;
	private float sharePrice;
	
	public StockTrade(JSONObject InputJson) {
		//ternary that determines the enum type of sell or buy
		//if sell then sell otherwise buy
		tradeType = InputJson.get("type").equals("Sell") ? TradeType.SELL : TradeType.BUY;
		
		//Grabs value and converts object to string
		stockSymbol = (String) InputJson.get("stock_symbol");
		
		//Grabs value and parses to long
		shareCount = (long) InputJson.get("count_shares");
		
		//Grabs value, converts to float
		String sharePriceToParse = ((String) InputJson.get("price_per_share")).substring(1);
		sharePrice = Float.parseFloat(sharePriceToParse);
	}
	
	public TradeType getTradeType() {
		return tradeType;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public long getShareCount() {
		return shareCount;
	}
	public float getSharePrice() {
		return sharePrice;
	}
	
	private void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}
	private void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	private void setShareCount(long shareCount) {
		this.shareCount = shareCount;
	}
	private void setSharePrice(float sharePrice) {
		this.sharePrice = sharePrice;
	}
}
