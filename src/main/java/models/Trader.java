package models;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Trader {
	private long accountNumber;
	
	private String ssn;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private float beginningBalance;
	
	private StockTrade[] stockTrades;

	
	public Trader(JSONObject InputJSON) {
		setAccountNumber((long)InputJSON.get("account_number"));
		
		setSsn((String)InputJSON.get("ssn"));
		
		setFirstName((String)InputJSON.get("first_name"));
		
		setLastName((String)InputJSON.get("last_name"));
		
		setEmail((String)InputJSON.get("email"));
		
		setPhone((String)InputJSON.get("phone"));
		
		//setBeginningBalance((String)InputJSON.get("beginning_balance"));
		String priceToParse = ((String) InputJSON.get("beginning_balance")).substring(1);
		float startPrice = Float.parseFloat(priceToParse);
		setBeginningBalance(startPrice);
		
		setStockTrades((JSONArray)InputJSON.get("stock_trades"));
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public String getSsn() {
		return ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public float getBeginningBalance() {
		return beginningBalance;
	}

	public StockTrade[] getStockTrades() {
		return stockTrades;
	}

	private void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	private void setSsn(String ssn) {
		this.ssn = ssn;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	private void setPhone(String phone) {
		this.phone = phone;
	}

	private void setBeginningBalance(float beginningBalance) {
		this.beginningBalance = beginningBalance;
	}

	private void setStockTrades(JSONArray inputJSON) {
		Iterator<JSONObject> stockIterator = inputJSON.iterator();
		
		ArrayList<StockTrade> stockList = new ArrayList<StockTrade>(); 
		
		while(stockIterator.hasNext()) {
			StockTrade newStock = new StockTrade(stockIterator.next());
			stockList.add(newStock);
		}
		
		this.stockTrades = stockList.toArray(new StockTrade[stockList.size()]);
	} 
	
	public static Trader[] JSONsToTraders(JSONArray inputJSON) {
		Iterator<JSONObject> stockIterator = inputJSON.iterator();
		
		ArrayList<Trader> traderList = new ArrayList<Trader>(); 
		
		while(stockIterator.hasNext()) {
			Trader newTrader = new Trader(stockIterator.next());
			traderList.add(newTrader);
		}
		
		return traderList.toArray(new Trader[traderList.size()]);
	}
}
