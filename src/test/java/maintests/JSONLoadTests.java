package maintests;

import static org.junit.Assert.*;

import org.junit.Test;

import controllers.JSONLoad;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


public class JSONLoadTests {
	@Test
	public void LoadJSonToObject() {
		//arrange
		String path = "C:\\Users\\Brandin Goldsberry\\JavaOne\\stock-statement-generator\\stock_transations.by.account.holder.json";
		int account_number = 1;
		int real_number = 0;
		//act
		JSONArray Loaded = JSONLoad.GetJSONTraders(true, path);
		Integer real_number_parse = (Integer) ((JSONObject) Loaded.get(0)).get("account_number");
		real_number = real_number_parse.intValue();
		//assert
		assertEquals("Expected: " + account_number + " Was: " + real_number , account_number, real_number);
	}
}
