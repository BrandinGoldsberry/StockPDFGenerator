package maintests;

import static org.junit.Assert.*;

import org.junit.Test;

import controllers.JSONLoad;
import controllers.PDFCreator;
import models.Trader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JSONLoadTests {
	@Test
	public void LoadJSonToObject() {
		//arrange
		String path = "C:\\Users\\Brandin Goldsberry\\JavaOne\\stock-statement-generator\\stock_transations.by.account.holder.json";
		long account_number = 1;
		long real_number = 0;
		
		//act
		JSONArray Loaded = JSONLoad.GetJSONTraders(true, path);
		Long real_number_parse = (Long) ((JSONObject) Loaded.get(0)).get("account_number");
		real_number = real_number_parse.longValue();
		
		//assert
		assertEquals("Expected: " + account_number + " Was: " + real_number , account_number, real_number);
	}
	
	@Test
	public void JSONToTrader() {
		//arrange
		Trader[] traders = null;
		long expected_number = 1;
		String expected_ssn = "418-99-5984";
		JSONArray toConvert = JSONLoad.GetJSONTraders(true, "C:\\Users\\Brandin Goldsberry\\JavaOne\\stock-statement-generator\\stock_transations.by.account.holder.json");
		
		String actual_ssn = null;
		long actual_number = 0;
		
		//act
		traders = Trader.JSONsToTraders(toConvert);
		Trader toCheck = traders[0];
		actual_ssn = toCheck.getSsn();
		actual_number = toCheck.getAccountNumber();
		
		//assert
		assertEquals("Expected: " + expected_number + " Was: " + actual_number , expected_number, actual_number);
		assertEquals("Expected: " + expected_ssn + " Was: " + actual_ssn , expected_ssn, actual_ssn);
	}
	
	@Test
	public void TraderToHTML() {
		JSONArray toConvert = JSONLoad.GetJSONTraders(true, "C:\\Users\\Brandin Goldsberry\\JavaOne\\stock-statement-generator\\stock_transations.by.account.holder.json");
		Trader[] traders = Trader.JSONsToTraders(toConvert);
		String Output = PDFCreator.TraderToHTML(traders[0]);
		
		System.out.println(Output);
	}
	
	@Test
	public void HTMLToPDF() {
		//arrange
		String OutputFile = "C:\\Users\\Brandin Goldsberry\\JavaOne\\stock-statement-generator\\outputs\\output.pdf";
		JSONArray toConvert = JSONLoad.GetJSONTraders(true, "C:\\Users\\Brandin Goldsberry\\JavaOne\\stock-statement-generator\\stock_transations.by.account.holder.json");
		Trader[] traders = Trader.JSONsToTraders(toConvert);
		String InputHTML = PDFCreator.TraderToHTML(traders[0]);
		
		//act
		PDFCreator.HTMLToPDF(OutputFile, InputHTML);
		
		//assert
	}
}
