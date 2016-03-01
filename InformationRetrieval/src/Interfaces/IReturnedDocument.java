package Interfaces;

// TODO: Auto-generated Javadoc
/**
 * The Interface IReturnedDocument.
 * Inteface for the returned (matched) document after the search.  
 */
public interface IReturnedDocument {
	
	/**
	 * Gets the rank.
	 *
	 * @return the rank
	 */
	public int getRank();
	/**
	 * Sets the rank.
	 *
	 * @param rank the new rank
	 */
	public void setRank(int rank);

	/**
	 * Gets the latest modification date.
	 *
	 * @return the latest modification date
	 */
	public String getLatestModificationDate();

	/**
	 * Sets the latest modification date.
	 *
	 * @param latestModificationDate the new latest modification date
	 */
	public void setLatestModificationDate(String latestModificationDate);

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle();

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title);

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description);

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath();

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path);

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public float getScore();

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(float score);
	

}
