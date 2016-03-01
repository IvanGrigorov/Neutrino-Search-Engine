package Interfaces;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDocumentOrFile.
 * Interface for a file, wich is going to be indexed 
 */
public interface IDocumentOrFile {

	/**
	 * Sets the title.
	 *
	 * @param title the title of the document
	 * 
	 */
	public void setTitle(String title);
	
	/**
	 * Gets the title.
	 *
	 * 
	 * @return the title of the document
	 */
	public String getTitle();
	
	/**
	 * Sets the body.
	 *
	 * @param body the body of the document
	 * 
	 */
	public void setBody(String body);
	
	/**
	 * Gets the body.
	 *
	 * 
	 * @return the body of the document
	 */
	public String getBody();
	
	
}
