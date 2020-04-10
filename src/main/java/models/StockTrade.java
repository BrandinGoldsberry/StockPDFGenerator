package models;

import org.json.simple.*;

public class StockTrade {
	private TradeType tradeType;
	private String stockSymbol;
	private int shareCount;
	private float sharePrice;
	
	public StockTrade(JSONObject InputJson) {
		//ternary that determines the enum type of sell or buy
		//if sell then sell otherwise buy
		tradeType = InputJson.get("type").equals("Sell") ? TradeType.SELL : TradeType.BUY;
		
		//Grabs value and converts object to string
		stockSymbol = (String) InputJson.get("stock_symbol");
		
		//Grabs value, converts to string, then parses to int
		shareCount = Integer.parseInt((String)InputJson.get("count_shares"));
		
		//Grabs value, converts to string, then grabs all but the first character, which should only leave out the $ sign
		String priceToParse = ((String) InputJson.get("price_per_share")).substring(1);
		
		//parses previous string to a float
		sharePrice = Float.parseFloat(priceToParse);
	}
	
	public TradeType getTradeType() {
		return tradeType;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public int getShareCount() {
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
	private void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	private void setSharePrice(float sharePrice) {
		this.sharePrice = sharePrice;
	}
}
