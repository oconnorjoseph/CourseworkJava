package step1;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows use of a non-elegant stream method for reading one .tsv file, checking the
 * .tsv file for proper form, and outputting the tab separated data to a new
 * .tsv file one record at a time.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class TSVStreamer {

	/**
	 * Effectively copies a .tsv file to a new .tsv file by streaming the data
	 * from one .tsv file to a new .tsv file one record at a time.
	 * 
	 * 
	 * @param originTSVDirectory
	 *            Full directory of the original .tsv file from which to copy
	 * @param originTSVFilename
	 *            Full name of the original .tsv file (e.g. aName.tsv)
	 * @param destinationTSVDirectory
	 *            Full directory of the destination .tsv file to which to copy
	 * @param destinationTSVFilename
	 *            Full name of the destiantion .tsv file (e.g. anotherName.tsv)
	 * @throws FileNotFoundException
	 *             if there is not a .tsv file at originTSVDirectory +
	 *             originTSVFilename
	 * @throws IOException
	 *             if there is origin .tsv file is not properly formatted or if
	 *             there is some more general IOException
	 */
	public void copy(String originTSVDirectory, String originTSVFilename,
			String destinationTSVDirectory, String destinationTSVFilename)
			throws FileNotFoundException, IOException {
		File originTSVFile = new File(originTSVDirectory, originTSVFilename);
		File destinationTSVFile = new File(destinationTSVDirectory,
				destinationTSVFilename);

		copy(originTSVFile, destinationTSVFile);
	}

	/**
	 * Effectively copies a .tsv file to a new .tsv file by streaming the data
	 * from one .tsv file to a new .tsv file one record at a time.
	 * 
	 * @param originTSVFile
	 *            Original .tsv file from which to copy
	 * @param destinationTSVFile
	 *            Destination .tsv file to which to copy
	 * @throws FileNotFoundException
	 *             if there is not a .tsv file at originTSVDirectory +
	 *             originTSVFilename
	 * @throws IOException
	 *             if there is origin .tsv file is not properly formatted or if
	 *             there is some more general IOException
	 */
	public void copy(File originTSVFile, File destinationTSVFile)
			throws FileNotFoundException, IOException {
		atEOF = false;
		Reader tsvReader = null;
		Writer tsvWriter = null;
		try {
			tsvReader = new FileReader(originTSVFile);
			tsvWriter = new PrintWriter(destinationTSVFile);
			// .tsv file exists. Otherwise, FileNotFoundException would have
			// been thrown
			prepareStream(tsvReader);
			// .tsv file's header w/ types properly formatted.
			outputHeader(tsvWriter);
			while (!atEOF) {
				copyRecord(tsvReader, tsvWriter);
			}
		} finally {
			close(tsvReader);
			close(tsvWriter);
			if (!atEOF) {
				destinationTSVFile.delete();
			}
		}
	}

	private void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				System.out.println("Unable to close a Closeable");
				e.printStackTrace();
			}
		}
	}

	private void outputHeader(Writer tsvWriter) throws IOException {
		outputRecord(headers, tsvWriter);
		outputRecord(buildTypeNames(), tsvWriter);
	}

	private String[] buildTypeNames() throws IOException {
		String[] typeNames = new String[typeCodes.length];
		for (int i = 0; i < typeCodes.length; i++) {
			typeNames[i] = toName(typeCodes[i]);
		}
		return typeNames;
	}

	private void prepareStream(Reader tsvReader) throws IOException {
		headers = nextRecordItemStrs(tsvReader);
		typeCodes = parseTypes(tsvReader);
		assertHeaderProperlyFormatted();
	}

	private void assertHeaderProperlyFormatted() throws IOException {
		if (headers.length != typeCodes.length || typeCodes.length == 0) {
			throw new IOException(
					"Number of headers does not match number of types in .tsv file.");
		}
	}

	private void copyRecord(Reader tsvReader, Writer tsvWriter)
			throws IOException {
		String[] items = nextRecordItemStrs(tsvReader);
		if (isProperlyFormatted(items)) {
			outputRecord(items, tsvWriter);
		} else if (items.length > 0) {
			System.out
					.println("The following record does not contain items that match the "
							+ "designated types of the second record in the .tsv file: "
							+ String.join("\\t", items));
		}
	}

	private void outputRecord(String[] items, Writer tsvWriter)
			throws IOException {
		String record = String.join(ITEM_DELIMITER, items) + RECORD_DELIMITER;
		tsvWriter.write(record);
	}

	private boolean isProperlyFormatted(String[] items) {
		if (!isProperLength(items)) {
			return false;
		}
		for (int i = 0; i < items.length; i++) {
			if (!isMatchingFormat(items[i], typeCodes[i])) {
				return false;
			}
		}
		return true;
	}

	private boolean isMatchingFormat(String item, byte typeCode) {
		return typeCode != LONG_TYPE_CODE || isLong(item);
	}

	private boolean isLong(String str) {
		try {
			Long.parseLong(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private boolean isProperLength(String[] items) {
		return items.length == typeCodes.length;
	}

	private byte[] parseTypes(Reader tsvReader) throws IOException {
		String[] typeNames = nextRecordItemStrs(tsvReader);
		return fetchCodes(typeNames);
	}

	private byte[] fetchCodes(String[] typeNames) throws IOException {
		byte[] typeCodes = new byte[typeNames.length];
		for (int i = 0; i < typeCodes.length; i++) {
			typeCodes[i] = toCode(typeNames[i]);
		}
		return typeCodes;
	}

	private String toName(byte typeCode) throws IOException {
		for (Map.Entry<String, Byte> entry : TYPE_MAP.entrySet()) {
			if (entry.getValue() == typeCode) {
				return entry.getKey();
			}
		}
		throw new IOException("Invalid type code in .tsv file: "
				+ Byte.toString(typeCode));
	}

	private byte toCode(String typeName) throws IOException {
		String lowercaseName = typeName.toLowerCase();
		if (!TYPE_MAP.containsKey(lowercaseName)) {
			throw new IOException("Invalid type name in .tsv file: " + typeName);
		}
		return TYPE_MAP.get(lowercaseName);
	}

	private String[] nextRecordItemStrs(Reader tsvReader) throws IOException {
		String record = nextRecord(tsvReader);
		return parseItemsStrs(record);
	}

	private String[] parseItemsStrs(String record) {
		if (isEmpty(record)) {
			return new String[0];
		}
		return record.split(ITEM_DELIMITER);
	}

	private boolean isEmpty(String record) {
		return record.trim().equals("");
	}

	private String nextRecord(Reader tsvReader) throws IOException {
		StringBuilder recordBuilder = new StringBuilder();
		int nextCharVal = tsvReader.read();
		while (!isTerminatingChar(nextCharVal)) {
			if (!isCarrageReturnChar(nextCharVal)) {
				appendCharOf(nextCharVal, recordBuilder);
			}
			nextCharVal = tsvReader.read();
		}
		return recordBuilder.toString();
	}

	private boolean isCarrageReturnChar(int nextCharVal) {
		return nextCharVal == CARRIAGE_RETURN_CHAR_VAL;
	}

	private boolean isTerminatingChar(int charVal) {
		if (charVal == EOF_CHAR_VAL) {
			atEOF = true;
		}
		return atEOF || charVal == NEWLINE_CHAR_VAL;
	}

	private void appendCharOf(int charVal, StringBuilder builder) {
		char nextChar = (char) charVal;
		builder.append(nextChar);
	}

	private String[] headers;
	private byte[] typeCodes;
	private boolean atEOF;

	@SuppressWarnings("serial")
	private static final Map<String, Byte> TYPE_MAP = new HashMap<String, Byte>() {
		{
			put(STRING_TYPE_NAME, STRING_TYPE_CODE);
			put(LONG_TYPE_NAME, LONG_TYPE_CODE);
		}
	};

	private static final String STRING_TYPE_NAME = "string",
			LONG_TYPE_NAME = "long";
	private static final byte STRING_TYPE_CODE = 0, LONG_TYPE_CODE = 1;

	private static final int NEWLINE_CHAR_VAL = (int) '\n',
			CARRIAGE_RETURN_CHAR_VAL = (int) '\r', EOF_CHAR_VAL = -1;
	private static final String ITEM_DELIMITER = "\t", RECORD_DELIMITER = "\n";
}
