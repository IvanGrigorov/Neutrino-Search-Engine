package documentsAndFiles;

import Interfaces.IReturnedDocument;



// TODO: Auto-generated Javadoc
/**
 * The Class ReturnedDocument.
 * Creating this class as a way to store the returned documents from the search (the matched documents to the query)
 */
// Creating this class as a way to store the returned documents from the search (the matched documents to the query)
public class ReturnedDocument implements IReturnedDocument {
	
	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The path. */
	private String path;
	
	/** The score. */
	private float score;
	
	/** The rank. */
	private int rank;
	
	/** The latest modification date. */
	private String latestModificationDate;
	
	
	/**
	 * Gets the rank.
	 *
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Sets the rank.
	 *
	 * @param rank the new rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Gets the latest modification date.
	 *
	 * @return the latest modification date
	 */
	public String getLatestModificationDate() {
		return latestModificationDate;
	}

	/**
	 * Sets the latest modification date.
	 *
	 * @param latestModificationDate the new latest modification date
	 */
	public void setLatestModificationDate(String latestModificationDate) {
		this.latestModificationDate = latestModificationDate;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public float getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(float score) {
		this.score = score;
	}
	
	//TODO: Add Description

	/**
	 * Instantiates a new returned document.
	 *
	 * @param title the title of document
	 * @param path the path to the document
	 * @param score the score of the document after searching
	 * @param description the description (the highlighting of a document)
	 * @param rank the rank of the document after the search
	 * @param modDate the last modificatiob date of the document
	 */
	public ReturnedDocument(String title, String path, float score, String description, int rank, String modDate) {
		this.description = description;
		this.title = title;
		this.path = path;
		this.score = score;
		this.rank = rank;
		this.latestModificationDate = modDate;
	}
	
	/**  
	 * Override the toString method to display the data of a document in a more pleasant way
	 */
	// Override the toString method to display the data of a document in a more pleasant way
	@Override 
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("Rank: %d", this.rank));
		stringBuilder.append(String.format(" Document Name: %s", this.title));
		stringBuilder.append(String.format(" Latest Mod Date: %s", this.latestModificationDate));
		stringBuilder.append(String.format(" Score: %s", this.score));
		stringBuilder.append(" Summary: \n");
		stringBuilder.append(this.description);
		
		// Adding a new line in the end
		stringBuilder.append("\n");
		
		return stringBuilder.toString();
	}
	
}
