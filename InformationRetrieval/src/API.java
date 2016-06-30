import java.awt.EventQueue;

import gui.SearchEngineIntro;

// TODO: Auto-generated Javadoc
/**
 * The Class API.
 * Starts the application.
 * @author      Ivan Grigorov (ivangrigorov9@gmail.com)
 * @version     1.2.0                (current version number of program)
 * @since       05.01.2016           (the date of the last version)
 */
public class API {

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEngineIntro window = new SearchEngineIntro();
					window.frmNeutrinoSearchEngine.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
