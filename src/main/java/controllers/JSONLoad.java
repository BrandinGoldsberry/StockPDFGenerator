package controllers;

import org.json.simple.*;
import org.json.simple.parser.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JSONLoad {
	private static JSONArray JSONTraders;
	
	private static JSONArray LoadFile(String fileName) {
		throw new NotImplementedException();
	}
	
	private static void updateObjects() {
		
	}
	
	public static JSONArray GetJSONTraders(boolean Update, String File) {
		return JSONTraders;
	}
}
