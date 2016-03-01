package searchAlgorithms;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import documentsAndFiles.HtmlFile;

// TODO: Auto-generated Javadoc
/**
 * The Class HtmlParser.
 * Parses Html.
 */
public abstract class HtmlParser {
	
	/**
	 * Parses the html.
	 *
	 * @param file the current html file
	 * @return the document
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Method for parsing an html file;
	public static Document parseHtml(File file) throws IOException {
		Document doc = Jsoup.parse(file, "UTF-8");
		
		return doc;
	}
	
	/**
	 *  Returns text of the parsed html.
	 *
	 * @param doc the document after parsing
	 * @return the html file
	 */
	// Here we get the content of the html file;
	public static HtmlFile textOfHtml(Document doc) {
		
		String title = doc.title();
		String body = doc.body().text();
		
		HtmlFile htmlFile = new HtmlFile(title, body);
		return htmlFile;
	}
}
