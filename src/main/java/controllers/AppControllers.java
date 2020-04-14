package controllers;

import org.json.simple.JSONArray;

import models.Trader;

public class AppControllers {
	private static Trader[] traders;
	
	private static String inputString;
	private static String outputString;
	
	public static void Run(String[] args) {
		inputString = args[0];
		outputString = args[1];
		
		LoadTraders();
	}
	
	public static void LoadTraders() {
		JSONArray toLoad = JSONLoad.GetJSONTraders(true, inputString);
		traders = Trader.JSONsToTraders(toLoad);
	}
}
