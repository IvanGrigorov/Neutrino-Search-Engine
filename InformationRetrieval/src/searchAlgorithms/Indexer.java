package searchAlgorithms;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.commons.io.FilenameUtils;

import documentsAndFiles.HtmlFile;
import documentsAndFiles.IndexedFile;
import documentsAndFiles.LocalFile;
import documentsAndFiles.ReturnedDocument;
import enumerations.TextOrHtmlFilesSelectorEnum;
import Validation.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class Indexer.
 * Indexing and searching of documents. 
 */
public class Indexer extends Constants {
	
	/** The Constant DIRECTORY_LOCATION. */
	public static final String DIRECTORY_LOCATION = "src\\HtmlTest";
	
	/** The analyzer. */
	// EnglishAnalyzer - specifically made for the English language;
	private static EnglishAnalyzer analyzer = new EnglishAnalyzer();
	
	/** The writer. */
	private IndexWriter writer;
	//private Highlighter highlighter = new Highlighter();

	// Constructor that creates a new indexer;
	/**
	 * Instantiates a new indexer.
	 *
	 * @param indexDirectory the index directory
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// indexDiectory the name of the folder where the index will be created;
	public Indexer(String indexDirectory) throws IOException {

		Path dirPath = Paths.get(indexDirectory);
		FSDirectory dir = FSDirectory.open(dirPath);

		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

		writer = new IndexWriter(dir, config);
	}

	/**
	 * Creates the index at location.
	 *
	 * @param indexLocation the index location
	 * @return the indexer
	 */
	public static Indexer createIndexAtLocation(String indexLocation) {
		Indexer indexer = null;

		// Try to create an index at the specified location;
		try {
			indexer = new Indexer(indexLocation);
		} catch (Exception ex) {
			System.out.println("ERROR: Unable to create index! " + ex.getMessage());
		}
		return indexer;
	}

	/**
	 * Index Html files.
	 *
	 * @param allFiles the all html files in the folder
	 * @param location the location
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void indexHtmlFiles(LinkedList<File> allFiles, String location) throws IOException {
			
		if (allFiles.isEmpty()) {
			System.out.println("The folder does not contain unindexed HTML files!");
			return;
		}

		int originalNumberOfDocuments = writer.numDocs();

		for (File file : allFiles) {
			
			FileReader reader = null;

			try {
				Document doc = new Document();

				// Read all the contents of the file;
				reader = new FileReader(file);

				// Parse the html and extract the content;
				HtmlFile currentHtml = HtmlParser.textOfHtml(HtmlParser.parseHtml(file));
				
				String contentAndTitle = FilenameUtils.removeExtension(file.getName()) + " " + currentHtml.getTitle() + " " + currentHtml.getBody();
				doc.add(new TextField("contentAndTitle", contentAndTitle, Field.Store.YES));
				
				String content = currentHtml.getBody() + currentHtml.getTitle();
				// Contains the document's text (body + title);
				doc.add(new TextField("contents", content, Field.Store.YES));

				// Contains info about file path and name;
				doc.add(new StringField("path", file.getPath(), Field.Store.YES));
				
				// Save the name of the file without extensions 
				String name = FilenameUtils.removeExtension(file.getName());
				System.out.println(name);
				doc.add(new StringField("filename", name, Field.Store.YES));
				
				// We use updateDocument to prevent duplicates; the path of each file is used as an unique identifier;
				writer.updateDocument(new Term("path", file.getPath()), doc);
				System.out.println("The following file was added successfully: " + file);
			} catch (Exception e) {
				System.out.println("The file could NOT be added" + file);
				System.out.println("\n " + e.getMessage());
			} finally {
				reader.close();
			}
		}

		int newNumberOfDocuments = writer.numDocs();
		System.out.println("\n");
		System.out.println((newNumberOfDocuments - originalNumberOfDocuments) + " documents were added.");
		System.out.println("\n");
	}
	
	/**
	 * Index Text files.
	 *
	 * @param allFiles the all text files in the folder
	 * @param location the location
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void indexTextFiles(LinkedList<File> allFiles, String location) throws IOException {
			
		if (allFiles.isEmpty()) {
			System.out.println("The folder does not contain unindexed HTML files!");
			return;
		}

		int originalNumberOfDocuments = writer.numDocs();

		for (File file : allFiles) {
			
			FileReader reader = null;

			try {
				Document doc = new Document();

				// Read all the contents of the file;
				reader = new FileReader(file);

				// Parse the html and extract the content;
				LocalFile currentTextFile = LocalFileExtractor.extractLocalTextFile(file);
				
				String contentAndTitle = currentTextFile.getTitle() + " " + currentTextFile.getBody();
				doc.add(new TextField("contentAndTitle", contentAndTitle, Field.Store.YES));
				
				String content = currentTextFile.getBody();
				// Contains the document's text (body + title);
				doc.add(new TextField("contents", content, Field.Store.YES));

				// Contains info about file path and name;
				doc.add(new StringField("path", file.getPath(), Field.Store.YES));
				
				// Save the name of the file without extensions 
				String name = FilenameUtils.removeExtension(file.getName());
				System.out.println(name);
				doc.add(new StringField("filename", name, Field.Store.YES));
				
				// We use updateDocument to prevent duplicates; the path of each file is used as an unique identifier;
				writer.updateDocument(new Term("path", file.getPath()), doc);
				System.out.println("The following file was added successfully: " + file);
			} catch (Exception e) {
				System.out.println("The file could NOT be added" + file);
				System.out.println("\n " + e.getMessage());
			} finally {
				reader.close();
			}
		}

		int newNumberOfDocuments = writer.numDocs();
		System.out.println("\n");
		System.out.println((newNumberOfDocuments - originalNumberOfDocuments) + " documents were added.");
		System.out.println("\n");
	}
	
	
	/**
	 * Close index.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Method for closing the index;
	public void closeIndex() throws IOException {
		writer.close();
	}

	/**
	 * Searching the documents.
	 *
	 * @param location the location of the folder you are searching in (currently not used)
	 * @param numOfMatches the number of matches you want to get
	 * @param queryInput the query input from the user
	 * @param searchSelector the search selector (by title, body or both)
	 * @param fileSelector selects if html or text files are searched
	 * @return the list with matched documents
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws InvalidTokenOffsetsException the invalid token offsets exception
	 */
	public static List<ReturnedDocument> search(String location, int numOfMatches, String queryInput, String searchSelector, TextOrHtmlFilesSelectorEnum fileSelector) throws IOException, ParseException, InvalidTokenOffsetsException {

		IndexReader reader;
		IndexSearcher searcher;
		TopScoreDocCollector collector;
		Query query = null;
		
		reader = DirectoryReader.open(FSDirectory.open(Paths.get(location)));
		// Implements search over a single IndexReader;
		searcher = new IndexSearcher(reader);

		// Collects the n top files that match our query;
		collector = TopScoreDocCollector.create(numOfMatches);

		// The content of document to be indexed can be specified by the serachSelector
		if (searchSelector.equals(TITLE_SEARCH_SELECTOR)) {
			
			// Create a query from the title of file of the html
			// The term is the exact query input in order to find exact matches
			query =  new TermQuery(new Term("filename", queryInput));
			System.out.println(query.toString());
		}
		else if (searchSelector.equals(BODY_SEARCH_SELECTOR)) {
			
			// Create a parsed query from the contents of the html, using the
			// Standard-Analyzer;
			query = new QueryParser("contents", analyzer).parse(queryInput);
		}
		else if (searchSelector.equals(BOTH_SEARCH_SELECTOR)){
			
			// Create a parsed query from the contents and title of file of the html, using the
			// Standard-Analyzer;
			query = new QueryParser("contentAndTitle", analyzer).parse(queryInput);
		}
		else {
			System.out.println("There is no selector option selected");
		}
		
		// Query specialy designed for the highlighter
		Query highlighterQuery = new QueryParser("contents", analyzer).parse(queryInput);
		QueryScorer queryScorer = new QueryScorer(highlighterQuery);
		Highlighter highlighter = new Highlighter(queryScorer);
		// Search using the query and add to the collection of matching
		// documents;
		searcher.search(query, collector);

		ScoreDoc[] matches = collector.topDocs().scoreDocs;
		List<ReturnedDocument> returnedDocuments = new ArrayList<ReturnedDocument>();
		
		// Print out the search results and the score of each document;
		//System.out.println("Found " + matches.length + " documents that match the query.");
		
		System.out.println("List of all related documents: \n");
		for (int i = 0; i < matches.length; ++i) {
			int docID = matches[i].doc;
			Document d = searcher.doc(docID);
			// If Html files are searched add only html files
			if ((fileSelector == TextOrHtmlFilesSelectorEnum.HTML) && FilenameUtils.getExtension(d.get("path")).equals("html")) {
				// Create a new formatter to make the last modified date readable
				// The method lastModified returns the amount of milliseconds passed since the last modification
				SimpleDateFormat formatter = new SimpleDateFormat();
				ReturnedDocument currentDocument = new ReturnedDocument(d.get("filename"), d.get("path"), matches[i].score, highlighter.getBestFragment(analyzer, "contentAndTitle", d.get("contentAndTitle")), (i+1), formatter.format(new File(d.get("path")).lastModified()));
				returnedDocuments.add(currentDocument);
				// Printing the information for every document like the task says 
				System.out.println(currentDocument.toString());
			}
			// If Text files are selected add only text files
			else if ((fileSelector == TextOrHtmlFilesSelectorEnum.TEXTFILE) && (FilenameUtils.getExtension(d.get("path")).equals("pdf") || FilenameUtils.getExtension(d.get("path")).equals("doc") ||
					FilenameUtils.getExtension(d.get("path")).equals("docx") || FilenameUtils.getExtension(d.get("path")).equals("txt"))) {
				// Create a new formatter to make the last modified date readable
				// The method lastModified returns the amount of milliseconds passed since the last modification
				SimpleDateFormat formatter = new SimpleDateFormat();
				ReturnedDocument currentDocument = new ReturnedDocument(d.get("filename"), d.get("path"), matches[i].score, highlighter.getBestFragment(analyzer, "contentAndTitle", d.get("contentAndTitle")), (i+1), formatter.format(new File(d.get("path")).lastModified()));
				returnedDocuments.add(currentDocument);
				// Printing the information for every document like the task says 
				System.out.println(currentDocument.toString());
			}
		}
		reader.close();
		return returnedDocuments;
	}
	
	/**
	 * Start searching. Trying to call the search method
	 *
	 * @param location the location of the folder you are searching in
	 * @param numOfMatches the number of matches you want to get
	 * @param queryInput the query input from the user
	 * @param searchSelector the search selector (by title, body or both)
	 * @param fileSelector selects if html or text files are searched
	 * @return the list with matched documents
	 */
	public static List<ReturnedDocument> startSearching(String location, int numOfMatches, String queryInput, String searchSelector, TextOrHtmlFilesSelectorEnum fileSelector) {
		List<ReturnedDocument> result = null;
		
		// Search in Html files
		if (fileSelector == TextOrHtmlFilesSelectorEnum.HTML) {
			try {
				
				String htmlRegExFilter = "^.*\\.html";
				
				// Get all html files from that directory and it's sub directories;
				LinkedList<File> allHtmlFiles = IndexedFile.returnAllFiles(location, htmlRegExFilter);
				
				// Create an index for the directory that will be searched;
				Indexer indexer  = createIndexAtLocation(location);
				
				indexer.writer.commit();
				
				// Extract only not indexed Files
				LinkedList<File> notIndexedFiles = Indexer.selectOnlyNotAlreadyIndexedFilesFromACollection(allHtmlFiles, location);
				
				// Index all the files;
				indexer.indexHtmlFiles(notIndexedFiles, location);
				indexer.closeIndex();
				// Perform a search;
				result = search(location, numOfMatches, queryInput, searchSelector, fileSelector);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTokenOffsetsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		// Search in text files
		else {
			try {
				
				String textRegExFilter = "[^\\n\\t]*\\.(pdf|txt|doc|docx)";
				
				// Get all Text files from that directory and it's sub directories;
				LinkedList<File> allTextFiles = IndexedFile.returnAllFiles(location, textRegExFilter);
				
				// Create an index for the directory that will be searched;
				Indexer indexer  = createIndexAtLocation(location);
				
				indexer.writer.commit();
				
				// Extract only not indexed Files
				LinkedList<File> notIndexedFiles = Indexer.selectOnlyNotAlreadyIndexedFilesFromACollection(allTextFiles, location);
				
				// Index all the files;
				indexer.indexTextFiles(notIndexedFiles, location);
				indexer.closeIndex();
				// Perform a search;
				result = search(location, numOfMatches, queryInput, searchSelector, fileSelector);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTokenOffsetsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
	}
	
	// Get the whole collection of returned HTML documents from the given folder
	// (The given path)
	/**
	 * Select only not already indexed files from a collection.
	 *
	 * @param fileCollection the file collection
	 * @param location the location
	 * @return the linked list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws InvalidTokenOffsetsException the invalid token offsets exception
	 */
	// Return the collection with extracted (not indexed) Files
	public static LinkedList<File> selectOnlyNotAlreadyIndexedFilesFromACollection(List<File> fileCollection, String location) throws IOException, ParseException, InvalidTokenOffsetsException {
		// Getting the path of our index and opens a reader to read the index in the current folder
		Path dirPath = Paths.get(location);
		FSDirectory dir = FSDirectory.open(dirPath);
		DirectoryReader indexReader = DirectoryReader.open(dir);
		LinkedList<File> onlyNotIndexedFiles = new LinkedList<File>();
		
		for (File file : fileCollection) {
			// Creates new Term using the path of the html file 
			Term indexTerm = new Term("path", file.getPath());
						
			// Checks if a document in the index has the same path
			if (indexReader.docFreq(indexTerm) > 0)
			{
				// If yes do not add the document
				continue;
			}
			else {
				onlyNotIndexedFiles.add(file);
			}
		}
		indexReader.close();
		return onlyNotIndexedFiles;
	}
}
