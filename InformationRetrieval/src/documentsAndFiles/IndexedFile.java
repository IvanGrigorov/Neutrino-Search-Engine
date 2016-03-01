package documentsAndFiles;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import Interfaces.IDocumentOrFile;

/**
 * The abstract Class File.
 * Represents an abstraction of the documents and files that are searched and indexed
 */
public abstract class IndexedFile implements IDocumentOrFile {
	
	/** The title. */
	protected String title;
	
	/** The body. */
	protected String body;
	
	/**
	 * Instantiates a new file.
	 *
	 * @param title the title of document
	 * @param body the body of document
	 */
	public IndexedFile(String title, String body) {
		this.title = title;
		this.body = body;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Return all local text files.
	 *
	 * @param dir the directory you are searching in
	 * @param documentFilter the regex filter string to select only text files or html files
	 * @return the linked list with all html files in the directory
	 */
	public static LinkedList<File> returnAllFiles(String dir, String documentFilter) {
		File directory = new File(dir);

		Collection files = FileUtils.listFiles(directory, new RegexFileFilter(documentFilter), TrueFileFilter.INSTANCE);
		LinkedList<File> result = (LinkedList<File>) files;

		return result;
	}

}
