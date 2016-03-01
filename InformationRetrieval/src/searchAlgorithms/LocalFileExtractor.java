package searchAlgorithms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.hwpf.HWPFDocument;

import documentsAndFiles.LocalFile;


// TODO: Auto-generated Javadoc
/**
 * The Class LocalFileExtractor.
 * Extracts information from text file. 
 */
public abstract class LocalFileExtractor {
	
	/**
	 * Extract local text file.
	 *
	 * @param file the file
	 * @return the extracted text local file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static LocalFile extractLocalTextFile(File file) throws IOException {
		
		String titleOfFile = FilenameUtils.removeExtension(file.getName());
		String ext = FilenameUtils.getExtension(file.getPath());
		LocalFile extractedFile = null;
		
		// Extract normal text files 
		if (ext.equals("txt")) {
			StringBuilder stringBuilder = new StringBuilder();
			 
			// Next line may throw an exception!
			Scanner fileReader = new Scanner(file);
			
			 
			// Read file
			while (fileReader.hasNextLine()) {
				stringBuilder.append(fileReader.nextLine());
			}
			
			String bodyOfDocument = stringBuilder.toString();
			
			// Close the resource after you've finished using it
			fileReader.close();
			
			extractedFile = new LocalFile(titleOfFile, bodyOfDocument);
		}
		// Extract pdf text files  
		else if (ext.equals("pdf")) {
			try{
			    PDDocument document = null; 
			    document = PDDocument.load(file);
			    document.getClass();
			    if( !document.isEncrypted() ){
			        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			        stripper.setSortByPosition( true );
			        PDFTextStripper Tstripper = new PDFTextStripper();
			        String extractedText = Tstripper.getText(document);
			        extractedFile = new LocalFile(titleOfFile, extractedText);
			    }
		    }catch(Exception e){
		        e.printStackTrace();
		    }
		}
		// Extract doc (Word) files
		else {
			FileInputStream fis = new FileInputStream(file);
			HWPFDocument doc = new HWPFDocument(fis);
			String extractedText = doc.getDocumentText();
			extractedFile = new LocalFile(titleOfFile, extractedText);
		}
		return extractedFile; 		
	}

}
