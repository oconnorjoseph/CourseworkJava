package step2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Tests {@link TSVPipeline} and {@link TSVFilter} against
 * several happy cases and unhappy cases targeted at {@link TSVFilter.Builder}
 * and {@link TSVFilter} filtering.
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
	 * Entry point for running the tests on {@link TSVFilter} and {@link TSVPipeline} for Step 2.
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		testTSVFilter();
		testTSVPipeline();
	}

	private static void testTSVPipeline() {
		System.out.println("Testing TSVPipeline...");
		testPipelineHappyCases();
		// Unhappy cases effectively tested in testTSVFilter() and by step1.Tester
		// since Step 2's TSVPipeline encapsulates the method of step1.TSVStreamer
	}

	private static void testPipelineHappyCases() {
		System.out.println("     Testing happy cases for TSVPipeline...");
		// Other basic happy cases effectively tested in step1.Tester
		// since Step 2's TSVPipeline encapsulates the method of step1.TSVStreamer
		testPipelineNoFilter();
		testPipelineOneStringFilter();
		testPipelineOneLongFilter();
		testPipelineLongStringFilter();
		testPipelineMultipleStringFilter();
		testPipelineMultipleStringFilterSeparate();
		testPipelineMultipleLongFilter();
		testPipelineMultipleLongFilterSeparate();
		testPipelineImpossibleFilter();
		testPipelineOutsideHeaderFilter();
	}

	private static void testPipelineOutsideHeaderFilter() {
		System.out.println("           Testing the example given in the assignment document with a filter for"
				+ " a header that is not in the .tsv file...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Car", 200).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_OUTSIDE_HEADER_FILTER_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineImpossibleFilter() {
		System.out.println("           Testing the example given in the assignment document with a filter no record can meet...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Zip Code", 200).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_IMPOSSIBLE_FILTER_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineMultipleLongFilterSeparate() {
		System.out.println("           Testing the example given in the assignment document with multiple long filters added separately...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Age", 20).selectAny("Age", 22).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_MULTIPLE_LONG_FILTER_SEPARATE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineMultipleLongFilter() {
		System.out.println("           Testing the example given in the assignment document with multiple long filters...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Age", 20, 22).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_MULTIPLE_LONG_FILTER_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineMultipleStringFilterSeparate() {
		System.out.println("           Testing the example given in the assignment document with multiple String filters added separately...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Name", "Frank").selectAny("Name", "Molly").done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_MULTIPLE_STRING_FILTER_SEPARATE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineMultipleStringFilter() {
		System.out.println("           Testing the example given in the assignment document with multiple String filters...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Name", "Frank", "Molly").done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_MULTIPLE_STRING_FILTER_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineLongStringFilter() {
		System.out.println("           Testing the example given in the assignment document with both a long and String filter...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Age", 19).selectAny("Name", "Ann").done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_LONG_STRING_FILTER_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineOneLongFilter() {
		System.out.println("           Testing the example given in the assignment document with one long filter...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Age", 18).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_ONE_LONG_FILTER_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineOneStringFilter() {
		System.out.println("           Testing the example given in the assignment document with one String filter...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
				.selectAny("Name", "Frank").done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_ONE_STRING_FILTER_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineNoFilter() {
		System.out.println("           Testing the example given in the assignment document with no filter...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testTSVFilter() {
		System.out.println("Testing TSVFilter...");
		testFilterHappyCases();
		testFilterUnhappyCases();
	}

	private static void testFilterUnhappyCases() {
		System.out
				.println("     Testing unhappy cases for TSVFilter#addFilter...");
		testFilterNoArguments();
		testFilterInvalidType();
		testFilterInvalidTypeAfterValidType();
	}

	private static void testFilterInvalidTypeAfterValidType() {

		String header = "unhappyCharSneakyHeader";
		System.out
				.println("           Testing adding of invalid filter type after a valid filter type...");
		try {
			new TSVFilter.Builder(null).selectAny(header, 0L, 'b').done();
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException correctly thrown");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void testFilterNoArguments() {
		String header = "unhappyNoArgumentsHeader";
		System.out
				.println("           Testing adding of 0-length variable arguments for #addFilter parameter items...");
		try {
			new TSVFilter.Builder(null).selectAny(header).done();
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException correctly thrown");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void testFilterInvalidType() {
		String header = "unhappyCharHeader";
		System.out
				.println("           Testing adding of invalid filter type...");
		try {
			new TSVFilter.Builder(null).selectAny(header, 'a', 'b').done();
		} catch (IllegalArgumentException e) {
			System.out.println("IllegalArgumentException correctly thrown");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void testFilterHappyCases() {
		System.out
				.println("     Testing happy cases for TSVFilter#addFilter...");
		testFilterStrings();
		testFilterLongs();
	}

	private static void testFilterLongs() {
		String header = "happyLongHeader";
		System.out.println("          Testing adding of long filters...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(null).selectAny(header, 1L, 2)
					.done();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Long> longFilters = filter.getConditionsFor(header);
		System.out.println("Added long filters are  : " + longFilters);
		System.out.println("Filters should have been: 1, 2");
		try {
			filter = new TSVFilter.Builder(null).selectAny(header, 1L, 2)
					.selectAny(header, 99999999999999L).done();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		longFilters = filter.getConditionsFor(header);
		System.out.println("Added long filters now are  : " + longFilters);
		System.out
				.println("Filters should have now been: 1, 2, 99999999999999");
		boolean hasFilters = filter.hasConditionsFor(header);
		System.out.println("#hasFiltersFor should return true. It returned: "
				+ hasFilters);
	}

	private static void testFilterStrings() {
		String header = "happyStrHeader";
		System.out.println("          Testing adding of String filters...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(null).selectAny(header,
					"strFilter1", "strFilter2").done();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<String> strFilters = filter.getConditionsFor(header);
		System.out.println("Added String filters are: "
				+ String.join(", ", strFilters));
		System.out.println("Filters should have been: strFilter1, strFilter2");
		try {
			filter = new TSVFilter.Builder(null).selectAny(header, "strFilter1",
					"strFilter2", "strFilter3").done();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		strFilters = filter.getConditionsFor(header);
		System.out.println("Added String filters now are: "
				+ String.join(", ", strFilters));
		System.out
				.println("Filters should have now been: strFilter1, strFilter2, strFilter3");
		boolean hasFilters = filter.hasConditionsFor(header);
		System.out.println("#hasFiltersFor should return true. It returned: "
				+ hasFilters);
	}

	private static final String EXAMPLE_NAME = "example.tsv",
			EXAMPLE_DESTINATION_NAME = "exampleD2.tsv",
			EXAMPLE_ONE_STRING_FILTER_DESTINATION_NAME = "exampleD2OneStringFilter.tsv",
			EXAMPLE_ONE_LONG_FILTER_DESTINATION_NAME = "exampleD2OneLongFilter.tsv",
			EXAMPLE_LONG_STRING_FILTER_DESTINATION_NAME = "exampleD2LongStringFilter.tsv",
			EXAMPLE_MULTIPLE_STRING_FILTER_DESTINATION_NAME = "exampleD2MultipleStringFilter.tsv",
			EXAMPLE_MULTIPLE_LONG_FILTER_DESTINATION_NAME = "exampleD2MultipleLongFilter.tsv",
			EXAMPLE_MULTIPLE_STRING_FILTER_SEPARATE_DESTINATION_NAME = "exampleD2MultipleStringFilterSeparate.tsv",
			EXAMPLE_MULTIPLE_LONG_FILTER_SEPARATE_DESTINATION_NAME = "exampleD2MultipleLongFilterSeparate.tsv",
			EXAMPLE_IMPOSSIBLE_FILTER_DESTINATION_NAME = "exampleD2ImpossibleFilter.tsv",
			EXAMPLE_OUTSIDE_HEADER_FILTER_DESTINATION_NAME = "exampleD2OutsideHeader.tsv";
}
