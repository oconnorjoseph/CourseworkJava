package step1;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Tests {@link TSVStreamer#copy(String, String, String, String)} &
 * consequentially {@link TSVStreamer#copy(java.io.File, java.io.File)} against
 * several happy cases and unhappy cases.
 * 
 * *** In order to run the tests yourself: The .tsv files used for the different
 * tests are located in the included "tsvFiles" folder. Reassign BASE_URL to the
 * full URL of the "tsvFiles" directory.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class TesterRunner {
	
	// Reassign BASE_URL to the full URL of the "tsvFiles" directory.
	private static final String BASE_URL = "C:\\Users\\skibu\\Dropbox\\Columbia\\"
			+ "Sophomore\\Honors Intro to CS\\Eclipse Workspace\\Assignment3\\tsvFiles\\";

	/**
	 * Entry point for running the tests on {@link TSVStreamer} for Step 1.
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		TSVStreamer streamer = new TSVStreamer();

		testHappyCases(streamer);
		testUnhappyCases(streamer);
	}

	private static void testUnhappyCases(TSVStreamer streamer) {
		System.out.println("Testing unhappy cases...");
		testNonexistentFile(streamer);
		testInvalidHeader(streamer);
		testInvalidTypeName(streamer);
		testNoHeaders(streamer);
		testNoTypeNames(streamer);
	}

	private static void testNoTypeNames(TSVStreamer streamer) {
		System.out
				.println("     Testing a .tsv file that has no type names...");
		try {
			streamer.copy(BASE_URL, NO_TYPE_NAMES_NAME, BASE_URL,
					NO_TYPE_NAMES_DESTINATION_NAME);
		} catch (IOException e) {
			System.out.println("IOException correctly thrown.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testNoHeaders(TSVStreamer streamer) {
		System.out.println("     Testing a .tsv file that has no headers...");
		try {
			streamer.copy(BASE_URL, NO_HEADERS_NAME, BASE_URL,
					NO_HEADERS_DESTINATION_NAME);
		} catch (IOException e) {
			System.out.println("IOException correctly thrown.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testInvalidTypeName(TSVStreamer streamer) {
		System.out
				.println("     Testing a .tsv file that has an invalid type name...");
		try {
			streamer.copy(BASE_URL, INVALID_TYPE_NAME_NAME, BASE_URL,
					INVALID_TYPE_NAME_DESTINATION_NAME);
		} catch (IOException e) {
			System.out.println("IOException correctly thrown.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testInvalidHeader(TSVStreamer streamer) {
		System.out
				.println("     Testing a .tsv file that does not have a matching number of headers and types...");
		try {
			streamer.copy(BASE_URL, INVALID_HEADER_NAME, BASE_URL,
					INVALID_HEADER_DESTINATION_NAME);
		} catch (IOException e) {
			System.out.println("IOException correctly thrown.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testNonexistentFile(TSVStreamer streamer) {
		System.out.println("     Testing a .tsv file that does not exist...");
		try {
			streamer.copy(BASE_URL, NONEXISTENT_NAME, BASE_URL,
					NONEXISTENT_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException correctly thrown.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testHappyCases(TSVStreamer streamer) {
		System.out.println("Testing happy cases...");
		testExampleTSV(streamer);
		testNoStrings(streamer);
		testNoLongs(streamer);
		testOneString(streamer);
		testOneLong(streamer);
		testInvalidLine(streamer);
		testInvalidLineTwoMistakes(streamer);
		testInvalidLines(streamer);
		testInvalidLinesTwoMistakes(streamer);
	}

	private static void testInvalidLinesTwoMistakes(TSVStreamer streamer) {
		System.out
				.println("     Testing a .tsv file with all invalid lines and two mistakes on those lines...");
		try {
			streamer.copy(BASE_URL, INVALID_LINES_TWO_MISTAKES_NAME, BASE_URL,
					INVALID_LINES_TWO_MISTAKES_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testInvalidLines(TSVStreamer streamer) {
		System.out
				.println("     Testing a .tsv file with all invalid lines and one mistake on those lines...");
		try {
			streamer.copy(BASE_URL, INVALID_LINES_NAME, BASE_URL,
					INVALID_LINES_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testInvalidLineTwoMistakes(TSVStreamer streamer) {
		System.out
				.println("     Testing a .tsv file with one invalid line and two mistakes on that line...");
		try {
			streamer.copy(BASE_URL, INVALID_LINE_TWO_MISTAKES_NAME, BASE_URL,
					INVALID_LINE_TWO_MISTAKES_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testInvalidLine(TSVStreamer streamer) {
		System.out
				.println("     Testing a .tsv file with one invalid line and one mistake on that line...");
		try {
			streamer.copy(BASE_URL, INVALID_LINE_NAME, BASE_URL,
					INVALID_LINE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testOneLong(TSVStreamer streamer) {
		System.out.println("     Testing a .tsv file with one long...");
		try {
			streamer.copy(BASE_URL, ONE_LONG_NAME, BASE_URL,
					ONE_LONG_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testOneString(TSVStreamer streamer) {
		System.out.println("     Testing a .tsv file with one String...");
		try {
			streamer.copy(BASE_URL, ONE_STRING_NAME, BASE_URL,
					ONE_STRING_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testNoLongs(TSVStreamer streamer) {
		System.out.println("     Testing a .tsv file with no longs...");
		try {
			streamer.copy(BASE_URL, NO_LONGS_NAME, BASE_URL,
					NO_LONGS_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testNoStrings(TSVStreamer streamer) {
		System.out.println("     Testing a .tsv file with no Strings...");
		try {
			streamer.copy(BASE_URL, NO_STRINGS_NAME, BASE_URL,
					NO_STRINGS_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testExampleTSV(TSVStreamer streamer) {
		System.out
				.println("     Testing the example given in the assignment document...");
		try {
			streamer.copy(BASE_URL, EXAMPLE_NAME, BASE_URL,
					EXAMPLE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final String EXAMPLE_NAME = "example.tsv",
			EXAMPLE_DESTINATION_NAME = "exampleD.tsv",
			NO_STRINGS_NAME = "noStrings.tsv",
			NO_STRINGS_DESTINATION_NAME = "noStringsD.tsv",
			NO_LONGS_NAME = "noLongs.tsv",
			NO_LONGS_DESTINATION_NAME = "noLongsD.tsv",
			ONE_STRING_NAME = "oneString.tsv",
			ONE_STRING_DESTINATION_NAME = "oneStringD.tsv",
			ONE_LONG_NAME = "oneLong.tsv",
			ONE_LONG_DESTINATION_NAME = "oneLongD.tsv",
			INVALID_LINE_NAME = "invalidLine.tsv",
			INVALID_LINE_DESTINATION_NAME = "invalidLineD.tsv",
			INVALID_LINE_TWO_MISTAKES_NAME = "invalidLineTwoMistakes.tsv",
			INVALID_LINE_TWO_MISTAKES_DESTINATION_NAME = "invalidLineTwoMistakesD.tsv",
			INVALID_LINES_NAME = "invalidLines.tsv",
			INVALID_LINES_DESTINATION_NAME = "invalidLinesD.tsv",
			INVALID_LINES_TWO_MISTAKES_NAME = "invalidLinesTwoMistakes.tsv",
			INVALID_LINES_TWO_MISTAKES_DESTINATION_NAME = "invalidLinesTwoMistakesD.tsv";

	private static final String NONEXISTENT_NAME = "nonexistent.tsv",
			NONEXISTENT_DESTINATION_NAME = "nonexistentD.tsv",
			INVALID_HEADER_NAME = "invalidHeader.tsv",
			INVALID_HEADER_DESTINATION_NAME = "invalidHeaderD.tsv",
			INVALID_TYPE_NAME_NAME = "invalidTypeName.tsv",
			INVALID_TYPE_NAME_DESTINATION_NAME = "invalidTypeNameD.tsv",
			NO_HEADERS_NAME = "noHeaders.tsv",
			NO_HEADERS_DESTINATION_NAME = "noHeadersD.tsv",
			NO_TYPE_NAMES_NAME = "noTypeNames.tsv",
			NO_TYPE_NAMES_DESTINATION_NAME = "noTypeNamesD.tsv";
}
