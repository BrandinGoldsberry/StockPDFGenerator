package controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import models.StockTrade;
import models.Trader;

public class PDFCreator {
	private static String HeaderTemplate = "<h1>%s</h1>";
	private static String ParagraphTemplate = "<p><strong>%s</strong></p>";
	private static String ParagraphTemplateNoStrong = "<p>%s</p>";
	private static String MoneyParagraphTemplate = "<p><strong>$ %10.2f</strong></p>";
	private static String NameTemplate = "<p>Name: <strong>%1$s %2$s</strong></p>";
	
	private static void AddHTML(String Template, StringBuilder builder, Object ...Value) {
		builder.append(String.format(Template, Value)).append("\r\n");
	}
	
	private static void AddHTML(StringBuilder builder, String Value) {
		builder.append(Value).append("\r\n");		
	}
	
	public static String TraderToHTML(Trader trader) {
		StringBuilder HTMLBuilder = new StringBuilder();
		
		AddHTML(HTMLBuilder, "<body>");
		
		AddHTML(HTMLBuilder, "<div>");
		AddHTML(NameTemplate, HTMLBuilder, trader.getFirstName(), trader.getLastName());
		AddHTML(HTMLBuilder, "</div>");
		
		AddHTML(HTMLBuilder, "<div>");
		AddHTML(HeaderTemplate, HTMLBuilder, "Account Number");
		AddHTML(ParagraphTemplate, HTMLBuilder, trader.getAccountNumber());
		AddHTML(HTMLBuilder, "</div>");
		
		AddHTML(HTMLBuilder, "<div>");
		AddHTML(HeaderTemplate, HTMLBuilder, "SSN");
		AddHTML(ParagraphTemplate, HTMLBuilder, trader.getSsn());
		AddHTML(HTMLBuilder, "</div>");
		
		AddHTML(HTMLBuilder, "<div>");
		AddHTML(HeaderTemplate, HTMLBuilder, "Email");
		AddHTML(ParagraphTemplate, HTMLBuilder, trader.getEmail());
		AddHTML(HTMLBuilder, "</div>");
		
		AddHTML(HTMLBuilder, "<div>");
		AddHTML(HeaderTemplate, HTMLBuilder, "Phone");
		AddHTML(ParagraphTemplate, HTMLBuilder, trader.getPhone());
		AddHTML(HTMLBuilder, "</div>");
		
		AddHTML(HTMLBuilder, "<div>");
		AddHTML(HeaderTemplate, HTMLBuilder, "Beginning Balance");
		AddHTML(MoneyParagraphTemplate, HTMLBuilder, trader.getBeginningBalance());
		AddHTML(HTMLBuilder, "</div>");
		
		AddHTML(HTMLBuilder, "<div>");
		AddHTML(HeaderTemplate, HTMLBuilder, "Trades");
		for (StockTrade Trade : trader.getStockTrades()) {
			AddHTML(HTMLBuilder, "<div>");
			AddHTML(ParagraphTemplate, HTMLBuilder, Trade.getTradeType());
			AddHTML(HTMLBuilder, "</div>");
			
			AddHTML(HTMLBuilder, "<div>");
			AddHTML(ParagraphTemplateNoStrong, HTMLBuilder, Trade.getStockSymbol());
			AddHTML(HTMLBuilder, "</div>");
			
			AddHTML(HTMLBuilder, "<div>");
			AddHTML(ParagraphTemplateNoStrong, HTMLBuilder, Trade.getShareCount());
			AddHTML(HTMLBuilder, "</div>");
			
			AddHTML(HTMLBuilder, "<div>");
			AddHTML(MoneyParagraphTemplate, HTMLBuilder, Trade.getSharePrice());
			AddHTML(HTMLBuilder, "</div>");
		}
		AddHTML(HTMLBuilder, "</div>");
		
		AddHTML(HTMLBuilder, "</body>");
		
		return HTMLBuilder.toString();
	}

	public static void HTMLToPDF(String OutputFile, String InputHTML) {
		try (OutputStream os = new FileOutputStream(OutputFile)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(InputHTML, "");
            builder.toStream(os);
            try {
				builder.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
