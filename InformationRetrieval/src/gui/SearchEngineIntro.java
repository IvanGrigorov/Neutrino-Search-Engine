package gui;





import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;













import Validation.Constants;
import Validation.Validator;
import searchAlgorithms.Indexer;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import documentsAndFiles.ReturnedDocument;
import enumerations.TextOrHtmlFilesSelectorEnum;




// TODO: Auto-generated Javadoc
/**
 * The Class SearchEngineIntro.
 * Main frame of the application.
 * Represents the main menu.
 */
public class SearchEngineIntro extends Constants {

	/** The frm neutrino search engine. */
	public  JFrame frmNeutrinoSearchEngine;
	
	/** The path field. */
	private JTextField pathField;
	
	/** The query text field. */
	private JTextField queryTextField;
	
	/** The number of documents returned text field. */
	private JTextField numberOfDocumentsReturnedTextField;
	
	/** The Filepart selector button group. */
	private final ButtonGroup FilePartSelectorButtonGroup = new ButtonGroup();
	
	/** The all radio buttons for selecting part of document to be searched */
	private List<JRadioButton> allRadioButtonsForSelectingPartOfDocumentToBeSearched = new LinkedList<JRadioButton>();
	
	/** The Text or Html file selector button group.*/
	private final ButtonGroup textOrHtmlFileSelectorButtonGroup = new ButtonGroup();
	
	/** The all radio buttons for selecting html or text document*/
	private List<JRadioButton> allRadioButtonsForSelectingHtmlOrTextDocumentToBeSearched = new LinkedList<JRadioButton>();

	/**
	 * Create the application.
	 */
	public SearchEngineIntro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * Adding all the components to the frame.
	 */
	private void initialize() {
		frmNeutrinoSearchEngine = new JFrame();
		frmNeutrinoSearchEngine.setResizable(false);
		frmNeutrinoSearchEngine.setTitle("Neutrino Search Engine");
		frmNeutrinoSearchEngine.setBounds(100, 100, 450, 300);
		frmNeutrinoSearchEngine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNeutrinoSearchEngine.getContentPane().setLayout(null);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(0, 0, 444, 63);
		frmNeutrinoSearchEngine.getContentPane().add(logoPanel);
		logoPanel.setLayout(null);
		
		JLabel logoLabel = new JLabel("Neutrino");
		logoLabel.setBounds(121, 5, 216, 60);
		logoLabel.setFont(new Font("Consolas", Font.PLAIN, 50));
		logoPanel.add(logoLabel);
		
		// Loading the logo picture
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource(LOGO_PICTURE_URL));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		// Painting the picture in the frame (first on the logo Label then adding the label to the frame)
		JLabel logoLabel_1 = new JLabel("");
		logoLabel_1.setBounds(20, 5, 101, 60);
		Image dimg = img.getScaledInstance(logoLabel_1.getWidth(), logoLabel_1.getHeight(),
		        Image.SCALE_SMOOTH);
		logoLabel_1.setIcon(new ImageIcon(dimg));
		logoPanel.add(logoLabel_1);
		
		JPanel authorsPanel = new JPanel();
		authorsPanel.setBounds(0, 62, 444, 33);
		frmNeutrinoSearchEngine.getContentPane().add(authorsPanel);
		authorsPanel.setLayout(null);
		
		JLabel authorsLabel = new JLabel("Authors: Ivan Grigorov, Kristiyan Dimov, Nikolay Kanev, Simeon Gigov");
		authorsLabel.setBounds(10, 0, 424, 29);
		authorsLabel.setFont(new Font("Georgia", Font.PLAIN, 11));
		authorsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		authorsPanel.add(authorsLabel);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(0, 94, 444, 154);
		frmNeutrinoSearchEngine.getContentPane().add(optionsPanel);
		optionsPanel.setLayout(null);
		
		pathField = new JTextField();
		pathField.setBounds(10, 71, 268, 20);
		optionsPanel.add(pathField);
		pathField.setColumns(10);
		
		JLabel pathInstructionsLabel = new JLabel("Enter the path of the folder you want to search in:");
		pathInstructionsLabel.setFont(new Font("Georgia", Font.ITALIC, 15));
		pathInstructionsLabel.setBounds(10, 40, 369, 20);
		optionsPanel.add(pathInstructionsLabel);
		
		queryTextField = new JTextField();
		queryTextField.setBounds(10, 133, 225, 20);
		optionsPanel.add(queryTextField);
		queryTextField.setColumns(10);
		
		JLabel queryLabel = new JLabel("Enter your query:");
		queryLabel.setFont(new Font("Georgia", Font.ITALIC, 15));
		queryLabel.setBounds(10, 102, 135, 20);
		optionsPanel.add(queryLabel);
		
		numberOfDocumentsReturnedTextField = new JTextField();
		numberOfDocumentsReturnedTextField.setBounds(249, 133, 86, 20);
		optionsPanel.add(numberOfDocumentsReturnedTextField);
		numberOfDocumentsReturnedTextField.setColumns(10);
		
		JLabel numberOfDocumentsReturnedLabel = new JLabel("# of returned documents:");
		numberOfDocumentsReturnedLabel.setFont(new Font("Georgia", Font.ITALIC, 15));
		numberOfDocumentsReturnedLabel.setBounds(194, 106, 180, 16);
		optionsPanel.add(numberOfDocumentsReturnedLabel);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new StartSearch());
		searchButton.setFont(new Font("Georgia", Font.PLAIN, 15));
		searchButton.setBounds(345, 131, 89, 23);
		optionsPanel.add(searchButton);
		
		JRadioButton searchOnlyTitleRadioButton = new JRadioButton("Search File Name Only");
		FilePartSelectorButtonGroup.add(searchOnlyTitleRadioButton);
		searchOnlyTitleRadioButton.setBounds(10, 7, 154, 33);
		optionsPanel.add(searchOnlyTitleRadioButton);
		
		JRadioButton searchOnlyBodyRadioButton = new JRadioButton("Search Body Only");
		FilePartSelectorButtonGroup.add(searchOnlyBodyRadioButton);
		searchOnlyBodyRadioButton.setBounds(166, 7, 135, 33);
		optionsPanel.add(searchOnlyBodyRadioButton);
		
		JRadioButton searchBothTitleAndBodyRadioButton = new JRadioButton("Search Both");
		searchBothTitleAndBodyRadioButton.setSelected(true);
		FilePartSelectorButtonGroup.add(searchBothTitleAndBodyRadioButton);
		searchBothTitleAndBodyRadioButton.setBounds(303, 7, 135, 33);
		optionsPanel.add(searchBothTitleAndBodyRadioButton);
		
		// Include all radio buttons in a collection
		allRadioButtonsForSelectingPartOfDocumentToBeSearched.add(searchOnlyTitleRadioButton);
		allRadioButtonsForSelectingPartOfDocumentToBeSearched.add(searchOnlyBodyRadioButton);
		allRadioButtonsForSelectingPartOfDocumentToBeSearched.add(searchBothTitleAndBodyRadioButton);
		
		JButton browseButton = new JButton("Browse");
		browseButton.setBounds(301, 69, 94, 24);
		optionsPanel.add(browseButton);
		browseButton.addActionListener(new StartBrowsing());
		
		JLabel copyrightLabel = new JLabel("Copyright: @Neutrino");
		copyrightLabel.setFont(new Font("Georgia", Font.ITALIC, 15));
		copyrightLabel.setBounds(265, 251, 169, 18);
		frmNeutrinoSearchEngine.getContentPane().add(copyrightLabel);
		
		JRadioButton TextFilesButton = new JRadioButton("Text Files");
		textOrHtmlFileSelectorButtonGroup.add(TextFilesButton);
		TextFilesButton.setBounds(10, 250, 109, 23);
		frmNeutrinoSearchEngine.getContentPane().add(TextFilesButton);
		
		JRadioButton HTMLFilesButton = new JRadioButton("HTML Files");
		HTMLFilesButton.setSelected(true);
		textOrHtmlFileSelectorButtonGroup.add(HTMLFilesButton);
		HTMLFilesButton.setBounds(121, 250, 109, 23);
		frmNeutrinoSearchEngine.getContentPane().add(HTMLFilesButton);
		
		allRadioButtonsForSelectingHtmlOrTextDocumentToBeSearched.add(TextFilesButton);
		allRadioButtonsForSelectingHtmlOrTextDocumentToBeSearched.add(HTMLFilesButton);
	}

	
	/**
	 * The Class SearchEngineIntro.StartSearch.
	 * Creating an ActionListner for the Search Button
	 */
	// Creating an ActionListner for the Search Button
	private class StartSearch implements ActionListener {
		
		/**
		 * Return selected button.
		 *
		 * @return the j radio button
		 */
		// Checks which search option is selected
		public JRadioButton returnSelectedButtonForPartOfDocumentToBeSearched() {
			JRadioButton selectedButtonOption = null;
			for (JRadioButton tmpButton : allRadioButtonsForSelectingPartOfDocumentToBeSearched) {
				if (tmpButton.isSelected()) {
					selectedButtonOption = tmpButton;
					break;
				}
			}
			return selectedButtonOption;
		}
		
		// Checks which document type is selected
		public JRadioButton returnSelectedButtonForDoccumentType() {
			JRadioButton selectedButtonOption = null;
			for (JRadioButton tmpButton : allRadioButtonsForSelectingHtmlOrTextDocumentToBeSearched) {
				if (tmpButton.isSelected()) {
					selectedButtonOption = tmpButton;
					break;
				}
			}
			return selectedButtonOption;
		}
		
		// Overriding method that calls the event
		/** 
		 * Overriding method that calls the event
		 * Declare what happens when the event is fired
		 */
		// Declare what happens when the event is fired
		@Override public void actionPerformed(ActionEvent e) {
			if (returnSelectedButtonForDoccumentType().getText() == Constants.HMTL_FILE) {
				launchSearch(pathField.getText(), queryTextField.getText(), numberOfDocumentsReturnedTextField.getText(), frmNeutrinoSearchEngine, returnSelectedButtonForPartOfDocumentToBeSearched().getText(), TextOrHtmlFilesSelectorEnum.HTML);
			}
			else {
				launchSearch(pathField.getText(), queryTextField.getText(), numberOfDocumentsReturnedTextField.getText(), frmNeutrinoSearchEngine, returnSelectedButtonForPartOfDocumentToBeSearched().getText(), TextOrHtmlFilesSelectorEnum.TEXTFILE);
			}
		}
		
	}
	
	/**
	 * The Class SearchEngineIntro.StartBrowsing
	 * Creating an ActionListner for the Browse Button
	 */
	// Creating an ActionListner for the Browse Button
		private class StartBrowsing implements ActionListener {
			
			// Creates File Chooser object -> a file chooser menu
			/**
			 * Creates the new file chooser.
			 *
			 * @return the j file chooser
			 */
			// From this new menu only folders can be chosen
			public JFileChooser CreateNewFileChooser() {
				JFileChooser fileChooser = new JFileChooser("D:\\");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				return fileChooser;
			}
			
			/** The file chooser. */
			// Initializing the file chooser
			private final JFileChooser fileChooser = CreateNewFileChooser();
			// Overriding method that calls the event
			/**
			 * Repaint the frame with the new information added (the selected path to the selected folder)
			 * Declare what happens when the event is fired
			 */
			// Declare what happens when the event is fired
			@Override public void actionPerformed(ActionEvent e) {
				
				// Save the result of the action from the file chooser
				int returnValue = fileChooser.showOpenDialog(frmNeutrinoSearchEngine);
				
				// If you click OK then the result of the return value should be the same as the build in APPROVE_OPTION constant
				if (returnValue == JFileChooser.APPROVE_OPTION ){
					
					// Save the path to the folder
					File directory = fileChooser.getSelectedFile();
					
					// Pass the path string to the path field input box
					pathField.setText(directory.getPath());
					
					// Repaint the frame with the new information added (the selected path)
					frmNeutrinoSearchEngine.repaint();
				}
			}
			
		}
	
	/**
	 * Launch search.
	 *
	 * @param path the path of the folder you are searching in
	 * @param query the query string
	 * @param numberOfResults the number of results
	 * @param frame the current opened frame
	 * @param searchSelector the search selector (searching only by title, body or both)
	 * @param fileSelector the file type selector (searching only text or Html files)
	 */
	public static void launchSearch(String path, String query, String numberOfResults, JFrame frame, String searchSelector, TextOrHtmlFilesSelectorEnum fileSelector) {
		
		// Validation if everything is set right before start searching
		// Check if all fields are filled
		// Return Error Message if not
		try {
			Validator.checkIfAllFieldsAreFilled(path, query, numberOfResults);
		}
		catch (IllegalArgumentException exception) {
			JOptionPane.showMessageDialog(frame, exception.getMessage());
			return;
		}
		
		// Check if path is correct (leads to a folder)
		// Return Error Message if not
		try {
			Validator.checkIfPathIsCorrect(path);
		}
		catch (IllegalArgumentException exception) {
			JOptionPane.showMessageDialog(frame, exception.getMessage());
			return;
		}
		
		// Check if input for number of results to be shown is a valid number (can be parsed to Int)
		// Return Error Message if not
		try {
			Integer.parseInt(numberOfResults);
		}
		catch (NumberFormatException exception) {
			JOptionPane.showMessageDialog(frame, "Enter valid number of documents to be returned.");
			return;
		}
		
		
		//Parse the input String of expected results number to Integer
		int numberOfResultsInInt = Integer.parseInt(numberOfResults);
		
		// Get all the results (matched documents to the query) as a List
		List<ReturnedDocument> returnedDocuments = Indexer.startSearching(path, numberOfResultsInInt, query, searchSelector, fileSelector);
		
		// Check if the wanted amount of results is bigger then the actual number of returned (matched) documents
		// Shows an alert that informs the user about the difference 
		// Shows all the returned results anyway
		if ((numberOfResultsInInt > returnedDocuments.size()) && (returnedDocuments.size() != 0)) {
			String response = String.format("You asked for %d results, but there are only %d. \n You can see all of the matched documents in the new window.", numberOfResultsInInt, returnedDocuments.size());
			JOptionPane.showMessageDialog(frame, response);
		}
		
		// Start the window (GUI Form showing the results) with document results as a parameter
		ResultsFromSearch dialog = new ResultsFromSearch(returnedDocuments);
		dialog.setVisible(true);
		
		// Close current form (Window)
		frame.setVisible(false);
		frame.dispose();
	}
}
