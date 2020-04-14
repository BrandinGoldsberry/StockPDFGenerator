package controllers;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JSONLoad {
	private static JSONArray JSONTraders;
	
	private static JSONArray LoadFile(String fileName) {
		
		JSONParser parser = new JSONParser();
		
		JSONArray jsonObject = null;
		
		try (Reader reader = new FileReader(fileName)) {

            jsonObject = (JSONArray) parser.parse(reader);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return jsonObject;
	}
	
	private static void updateObjects(String File) {
		JSONTraders = LoadFile(File);
	}
	
	public static JSONArray GetJSONTraders(boolean Update, String File) {
		if(Update) {
			updateObjects(File);
		}
		return JSONTraders;
	}
}
