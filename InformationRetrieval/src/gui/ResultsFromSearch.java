package gui;

import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.UIManager;

import documentsAndFiles.ReturnedDocument;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// TODO: Auto-generated Javadoc
/**
 * The Class ResultsFromSearch.
 * Dialog frame showing the results 
 */
public class ResultsFromSearch extends JDialog {

	/** The content panel. */
	// The main panel which holds all components
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * Constructor that adds all the components to the form (Window)
	 * List of documents (matches to the query) passed as a parameter to the constructor
	 *
	 * @param results - the returned documents after the searching
	 */
	public ResultsFromSearch(List<ReturnedDocument> results) {
		
		// Make form unresizeable (with fixed unchanging size)
		setResizable(false);
		setBounds(100, 100, 470, 300);
		contentPanel.setBorder(null);
		
		// Adding Grid Layout to the main panel (similar to table Layout)
		// Easy to maintain when adding components with same size
		// Works with JScrollPane (can add a scroll if necessary)
		// Creates a grid with as many rows as needed (0) an one column (1)
		contentPanel.setLayout(new GridLayout(0,1));
		
		// If there are no results (documents) and the list of results is empty 
		// Add only one label that says there are no results based on that query in the specified folder
		if(results.size() == 0) {
			JLabel lblnoResultsRetuned = new JLabel(String.format("There are no documents that match the query. \n Please try new query or change the directory in which \n you are looking for."));
			lblnoResultsRetuned.setFont(new Font("Georgia", Font.ITALIC, 11));
			lblnoResultsRetuned.setBounds(10, 11, 92, 24);
			contentPanel.add(lblnoResultsRetuned);
		}
		
		// If there are some results add information for anyone of them in the form
		else {
			
			// Adding components to the main panel describing every result (document) from the list 
			for (int i = 0; i < results.size(); i++) {
				
				ReturnedDocument currentResult = results.get(i);
				
				
				JPanel panel = new JPanel();
				panel.setBounds(0, i*128, 434, 128);
				panel.setPreferredSize(new Dimension(434, 128));
				contentPanel.add(panel);
				panel.setLayout(null);
				
				JLabel lblResult = new JLabel(String.format("Result %d:", i+1));
				lblResult.setFont(new Font("Georgia", Font.ITALIC, 15));
				lblResult.setBounds(10, 11, 92, 24);
				panel.add(lblResult);
				
				JLabel lblScore = new JLabel("Score: ");
				lblScore.setFont(new Font("Georgia", Font.ITALIC, 11));
				lblScore.setBounds(105, 11, 92, 24);
				panel.add(lblScore);
				
				JLabel lblScoreResult = new JLabel(Float.toString(currentResult.getScore()));
				lblScoreResult.setFont(new Font("Georgia", Font.ITALIC, 11));
				lblScoreResult.setBounds(147, 11, 92, 24);
				panel.add(lblScoreResult);
				
				JLabel lblLatestModifiedDate = new JLabel("Latest Modified Date:  ");
				lblLatestModifiedDate.setFont(new Font("Georgia", Font.ITALIC, 11));
				lblLatestModifiedDate.setBounds(225, 11, 150, 24);
				panel.add(lblLatestModifiedDate);
				
				JLabel lblLatestModifiedDateForAGivenDocument = new JLabel(currentResult.getLatestModificationDate());
				lblLatestModifiedDateForAGivenDocument.setFont(new Font("Georgia", Font.ITALIC, 11));
				lblLatestModifiedDateForAGivenDocument.setBounds(350, 11, 92, 24);
				panel.add(lblLatestModifiedDateForAGivenDocument);
				
				JLabel lblTitle = new JLabel("Title:");
				lblTitle.setBounds(10, 35, 46, 14);
				panel.add(lblTitle);
				
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setBounds(10, 52, 75, 14);
				panel.add(lblDescription);
				
				JLabel lblTitleOfResult = new JLabel(currentResult.getTitle());
				lblTitleOfResult.setBounds(42, 35, 340, 14);
				panel.add(lblTitleOfResult);
				
				JLabel lblPath = new JLabel("Path:");
				lblPath.setBounds(10, 103, 46, 14);
				panel.add(lblPath);
				
				JTextField lblPathDescription = new JTextField(currentResult.getPath());
				lblPathDescription.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				lblPathDescription.setBounds(56, 103, 228, 14);
				lblPathDescription.setEditable(false);
				lblPathDescription.setEnabled(true);
				panel.add(lblPathDescription);
				
				JButton btnOpenResultInBrowser = new JButton("Open Result");
				btnOpenResultInBrowser.addActionListener(new OpenUrl(currentResult.getPath()));
				btnOpenResultInBrowser.setBounds(315, 104, 115, 23);
				panel.add(btnOpenResultInBrowser);
				
				JTextArea descriptionOfFile = new JTextArea(currentResult.getDescription());
				descriptionOfFile.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				descriptionOfFile.setLineWrap(true);
				descriptionOfFile.setWrapStyleWord(true);
				descriptionOfFile.setBorder(null);
				//dfd.setText();
				descriptionOfFile.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
				descriptionOfFile.setEditable(false);
				descriptionOfFile.setEnabled(true);
				descriptionOfFile.setBounds(80, 52, 352, 51);
				
				// Adding scroll if the description is too big
				JScrollPane scrollPane = new JScrollPane(descriptionOfFile);
				scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				scrollPane.setBounds(80, 52, 352, 30);
				
				panel.add(scrollPane);
			}
		}
		getContentPane().setLayout(null);
		
		// Adding JScrollPane (scroll for the form if necessary)
		// Passing main container (panel) as parameter to the constructor
		JScrollPane scrollPane = new JScrollPane(this.contentPanel);
		
		// Giving size to the JScrollPane and coordinates where to show in the form
		scrollPane.setBounds(0, 0, 455, 263);
		
		// Checking if the passed component (the main panel) is bigger then the size of the JScrollPane
		// If yes adds a scroll (horizontal or/and vertical) based on the fact if it is wider or higher or both
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);      
		
		// Adding the JScrollPane and its components to the frame (form)
		getContentPane().add(scrollPane);
		
		// Show main menu after closing the result window
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	super.windowClosing(windowEvent);
		    	SearchEngineIntro start = new SearchEngineIntro();
		    	start.frmNeutrinoSearchEngine.setVisible(true);
		    }
		});
	}
	
	/**
	 * The Class ResultsFromSearch.OpenUrl.
	 */
	// Create Action Listener to open the file in the browser from the given path
	private class OpenUrl implements ActionListener {
		
		/** The url. */
		private String url = "";
		
		/**
		 * Gets the url.
		 *
		 * @return the url
		 */
		public String getUrl() {
			
			return this.url;
		}
		
		/**
		 * Sets the url.
		 *
		 * @param value the new url
		 */
		public void setUrl(String value) {
			
			this.url = value;
		}
		
		/**
		 * Instantiates a new open url.
		 *
		 * @param url the url
		 */
		public OpenUrl(String url){
			this.url = url;
		}
		
		// Overriding method that calls the event
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		/**
		 * Declare what happens when the event to open the file in the browser from the given path is fired
		 *
		 * @param e the event
		 */
		// Declare what happens when the event is fired
		@Override public void actionPerformed(ActionEvent e) {
			 Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			            desktop.browse(new File(this.url).toURI());
			        } catch (Exception exception) {
			        	exception.printStackTrace();
			        }
			    }
		}
		
	}
}
