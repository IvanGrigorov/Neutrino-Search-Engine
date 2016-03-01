package documentsAndFiles;

import Interfaces.IDocumentOrFile;


// TODO: Auto-generated Javadoc
/**
 * The Class HtmlFile.
 * Represents an Html extracted file
 */
public class HtmlFile extends IndexedFile implements IDocumentOrFile  {
	
	/**
	 * Instantiates a new html file.
	 *
	 * @param title the title of document
	 * @param body the body of document
	 */
	public HtmlFile(String title, String body) {
		super(title, body);
	}
	
}
