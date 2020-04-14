package controllers;

import org.json.simple.JSONArray;

import models.Trader;

public class AppControllers {
	private static Trader[] traders;
	
	private static String[] traderHTMLs;
	
	private static String inputString;
	private static String outputString;
	
	public static void Run(String[] args) {
		//inputString = args[0];
		//outputString = args[1];
		
		inputString = "C:\\Users\\Brandin Goldsberry\\JavaOne\\stock-statement-generator\\stock_transations.by.account.holder.json";
		outputString = "C:\\Output";
		
		LoadTraders();
		ConvertToHTML();
		SaveAsPDFs();
	}
	
	public static void LoadTraders() {
		JSONArray toLoad = JSONLoad.GetJSONTraders(true, inputString);
		traders = Trader.JSONsToTraders(toLoad);
	}
	
	public static void ConvertToHTML() {
		traderHTMLs = new String[traders.length];
		for(int i = 0; i < traders.length; i++) {
			traderHTMLs[i] = PDFCreator.TraderToHTML(traders[i]);
		}
	}
	
	public static void SaveAsPDFs() {
		for (int i = 0; i < traderHTMLs.length; i++) {
			String html = traderHTMLs[i];
			String newOutput = outputString + "\\Account" + i + ".pdf";
			
			PDFCreator.HTMLToPDF(newOutput, html);
		}
	}
}
