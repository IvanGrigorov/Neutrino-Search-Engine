package searchAlgorithms;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class HmtlTagRemover.
 */
public class HmtlTagRemover {
	
	/** The Constant INPUT_FILE_NAME. */
	private static final String INPUT_FILE_NAME = "C:\\Users\\Kris\\Desktop\\Workspace\\KnigataZaJava\\src\\PrimerenIzpit2005\\Problem1.html";
	
	/** The Constant CHARSET. */
	private static final String CHARSET = "windows-1251";
	
	/** The Constant OUTPUT_FILE_NAME. */
	private static final String OUTPUT_FILE_NAME = "C:\\Users\\Kris\\Desktop\\Workspace\\KnigataZaJava\\src\\PrimerenIzpit2005\\output.txt";

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(new FileInputStream(
				INPUT_FILE_NAME), CHARSET);
		PrintWriter writer = new PrintWriter(new File(OUTPUT_FILE_NAME), CHARSET);
		try {
			boolean inTag = false;
			StringBuilder buffer = new StringBuilder();
			while (true) {
				int nextChar = reader.read();
				if (nextChar == -1) {
					printBuffer(writer, buffer);
					break;
				}
				char ch = (char) nextChar;
				if (ch == '<') {
					if (!inTag) {
						printBuffer(writer, buffer);
					}
					buffer.setLength(0);
					inTag = true;
				} else if (ch == '>') {
					inTag = false;
				} else {
					// We have other character (not "<" or ">");
					if(!inTag) {
						buffer.append(ch);
					}
				}
			}
		} finally {
			reader.close();
			writer.close();
		}

	}

	/**
	 * Removes the new lines with white space.
	 *
	 * @param str the str
	 * @return the string
	 */
	private static String removeNewLinesWithWhiteSpace(String str) {
		str = str.replaceAll("\n\\s+", "\n");
		return str;
	}

	/**
	 * Prints the buffer.
	 *
	 * @param writer the writer
	 * @param buffer the buffer
	 */
	private static void printBuffer(PrintWriter writer, StringBuilder buffer) {
		String str = buffer.toString();
		String trimmed = str.trim();
		String textOnly = removeNewLinesWithWhiteSpace(trimmed);
		if (textOnly.length() != 0) {
			writer.println(textOnly);
		}
	}
}
