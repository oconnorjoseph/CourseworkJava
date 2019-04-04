package step4;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pipeline for copying one .tsv file to one or more destination .tsv file
 * 
 * Given a {@link TSVFilter}, checks the origin .tsv file from the TSVFilter for
 * proper form, filters out any records not matching conditions set by the
 * TSVFilter, and outputs the tab separated data to a destination .tsv file.
 * This is all performed one record at a time.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class TSVPipeline {

	/**
	 * Constructs a new TSVPipeline according to the given {@link TSVFilter}
	 * filter
	 * 
	 * @param filter
	 *            {@link TSVFilter} that determines the origin .tsv file as well
	 *            as any conditions for records to meet in order to be copied to
	 *            the destination .tsv file
	 */
	public TSVPipeline(TSVFilter filter) {
		this.filter = filter;
	}

	/**
	 * Copies the origin .tsv file denoted by the instance's {@link TSVFilter}
	 * and filtered by the instance's {@link TSVFilter} to the given destination
	 * .tsv file
	 * 
	 * Checks the origin .tsv file from the TSVFilter for proper form, filters
	 * out any records not matching conditions set by the TSVFilter, and outputs
	 * the tab separated data to the destination .tsv file. This is all
	 * performed one record at a time.
	 * 
	 * @param destinationTSVDirectory
	 *            The destination .tsv file's directory to which to copy the
	 *            filtered origin .tsv file
	 * @param destinationTSVFilename
	 *            The destination .tsv file's filename to which to copy the
	 *            filtered origin .tsv file
	 * @throws FileNotFoundException
	 *             if the argument for destinationTSVFile is not actually .tsv
	 *             file
	 * @throws IOException
	 *             if there is origin .tsv file is not properly formatted or if
	 *             there is some more general IOException
	 */
	public void copyTo(String destinationTSVDirectory,
			String destinationTSVFilename) throws FileNotFoundException,
			IOException {
		File destinationTSVFile = new File(destinationTSVDirectory,
				destinationTSVFilename);
		copyTo(destinationTSVFile);
	}

	/**
	 * Copies the origin .tsv file denoted by the instance's {@link TSVFilter}
	 * and filtered by the instance's {@link TSVFilter} to the given destination
	 * .tsv file. Then performs any {@link Terminal} computations specified by
	 * the instance's {@link TSVFilter}
	 * 
	 * Checks the origin .tsv file from the TSVFilter for proper form, filters
	 * out any records not matching conditions set by the TSVFilter, and outputs
	 * the tab separated data to the destination .tsv file. This is all
	 * performed one record at a time.
	 * 
	 * @param destinationTSVFile
	 *            The destination .tsv file to which to copy the filtered origin
	 *            .tsv file
	 * @throws FileNotFoundException
	 *             if the argument for destinationTSVFile is not actually .tsv
	 *             file
	 * @throws IOException
	 *             if there is origin .tsv file is not properly formatted or if
	 *             there is some more general IOException
	 */
	public void copyTo(File destinationTSVFile) throws FileNotFoundException,
			IOException {
		atEOF = false;
		Reader tsvReader = null;
		Writer tsvWriter = null;
		try {
			tsvReader = new FileReader(filter.getOriginTSVFile());
			tsvWriter = new PrintWriter(destinationTSVFile);
			// .tsv file exists. Otherwise, FileNotFoundException would have
			// been thrown
			prepareStream(tsvReader);
			// .tsv file's header w/ types properly formatted.
			outputHeader(tsvWriter);
			while (!atEOF) {
				copyRecord(tsvReader, tsvWriter);
			}
			printTerminalComputations();
		} finally {
			close(tsvReader);
			close(tsvWriter);
			if (!atEOF) {
				destinationTSVFile.delete();
			}
		}
	}

	private void printTerminalComputations() {
		for (String header : headers) {
			if (filter.hasTerminalComputationFor(header)) {
				Computation terminalComputation = filter.getTerminalComputationFor(header);
				System.out.println(header + terminalComputation.status());
			}
		}
	}

	private void copyRecord(Reader tsvReader, Writer tsvWriter)
			throws IOException {
		String[] items = nextRecordItemStrs(tsvReader);
		if (isProperlyFormatted(items)) {
			outputByFilter(tsvWriter, items);
		} else if (items.length > 0) {
			System.out
					.println("The following record does not contain items that match the "
							+ "designated types of the second record in the .tsv file: "
							+ String.join("\\t", items));
		}
	}

	private void outputByFilter(Writer tsvWriter, String[] items)
			throws IOException {
		if (allSatisfyFilter(items)) {
			updateTerminalConditions(items);
			outputRecord(items, tsvWriter);
		}
	}

	private void updateTerminalConditions(String[] items) {	
		for (int i = 0; i < items.length; i++) {
			if (filter.hasTerminalComputationFor(headers[i])) {
				Computation terminalComputation = filter.getTerminalComputationFor(headers[i]);
				terminalComputation.update(items[i], toRecord(items));
			}
		}
	}

	private boolean allSatisfyFilter(String[] items) {
		for (int i = 0; i < items.length; i++) {
			if (filter.hasConditionsFor(headers[i])
					&& !isOneOfFilters(headers[i], items[i])) {
				return false;
			}
		}
		return true;
	}

	private boolean isOneOfFilters(String header, String item) {
		List<Object> filters = filter.getConditionsFor(header);
		for (Object filter : filters) {
			if (filter.toString().equals(item)) {
				return true;
			}
		}
		return false;
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

	private void outputRecord(String[] items, Writer tsvWriter)
			throws IOException {
		String record = toRecord(items);
		tsvWriter.write(record);
	}

	private String toRecord(String[] items) {
		return String.join(ITEM_DELIMITER, items) + RECORD_DELIMITER;
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

	private TSVFilter filter;

}
