package Validation;
import java.io.File;


// TODO: Auto-generated Javadoc
/**
 * The Class Validator.
 * Validating input information. 
 */
// Validating the input from the user
public abstract class Validator {
	
	/**
	 * Check if path is correct.
	 *
	 * @param path the path of the document
	 */
	public static  void checkIfPathIsCorrect(String path) {
		//Try to create a test file with the given path
		File testFile = new File(path);
		
		//Checking whether the file exists
		if (!testFile.exists()) {
			throw new IllegalArgumentException("The current path is incorrect. There is no folder with the specified path. \n Please enter a valid new one.");
		}
		
		//Make sure the path leads to a folder
		if (!testFile.isDirectory()) {
			throw new IllegalArgumentException("The current path is incorrect. The given path does not lead to a directory. \n Please enter a valid new one.");
		}
	}
	
	// Checking if the input for the amount of wanted results is a valid number (can be parsed to Int)
	/**
	 * Check if input is a correct number.
	 *
	 * @param number the number of results you want to have after the search
	 * Currently not used
	 */
	// Currently not used
	public static void checkIfInputIsACorrectNumber(String number){
		try {
			Integer.parseInt(number);
		}
		catch (NumberFormatException e) {
			
		}
	}
	
	/**
	 * Check if all fields are filled.
	 *
	 * @param path the path of the folder you are searching in
	 * @param query the query input from the user
	 * @param numberOfReturnedResults the number of returned results you want to have
	 */
	public static void checkIfAllFieldsAreFilled(String path, String query, String numberOfReturnedResults) {
		
		// If the query, path or/and the number of results to be returned fields is/are empty throws an exception  
		if (path.equals("") || query.equals("") || numberOfReturnedResults.equals("")) {	
			throw new IllegalArgumentException("Fields cannot be empty. Please fill all of them before start searching.");
		}
	}

}
