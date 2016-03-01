package documentsAndFiles;

import Interfaces.IDocumentOrFile;


/**
 * The Class LocalFile.
 * Represents an local extracted file
 */
public class LocalFile extends IndexedFile implements IDocumentOrFile{
	
		/**
		 * Instantiates a new local file.
		 *
		 * @param title the title of document
		 * @param body the body of document
		 */
		public LocalFile(String title, String body) {
			super(title, body);
		}
}
