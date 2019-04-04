package step4;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Tests {@link TSVPipeline} and {@link TSVFilter} against several happy cases
 * and unhappy cases targeted at {@link TSVFilter.Builder} and {@link TSVFilter}
 * terminal computation with {@link Terminal}
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
	 * Entry point for running the tests on {@link TSVFilter} and
	 * {@linkTSVPipeline} for Step 3.
	 * 
	 * @param args
	 *            UNUSED
	 */
	public static void main(String[] args) {
		testTerminalComputations();
	}

	private static void testTerminalComputations() {
		System.out.println("Testing terminal computations...");
		testTerminalHappyCases();
		testTerminalUnhappyCase();
	}

	private static void testTerminalUnhappyCase() {
		System.out
				.println("     Testing terminal unhappy case of sum terminal computation on String column...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
					.selectAny("Car", 200).compute("Name", Terminal.SUM).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL,
					EXAMPLE_OUTSIDE_HEADER_FILTER_DUPLICATE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException correctly thrown");
		}
	}

	private static void testTerminalHappyCases() {
		System.out
				.println("     Testing happy cases of terminal computation...");
		testPipelineNoFilterAllsame();
		testPipelineNoFilterCount();
		testPipelineNoFilterMin();
		testPipelineNoFilterMax();
		testPipelineNoFilterSum();
		testPipelineOneLongFilterCount();
		testPipelineImpossibleFilterAllsame();
		testPipelineOutsideHeaderFilterMultipleFilters();
		testPipelineOutsideHeaderFilterDuplicateFilters();

		testPipelineNoFilterFirstdiff();
		testPipelineNoFilterStats();
	}

	private static void testPipelineNoFilterStats() {
		System.out
				.println("           Testing the example given in the assignment document with no filter and stats terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
					.compute("Age", Terminal.STATS).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_STATS_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineNoFilterFirstdiff() {
		System.out
				.println("           Testing the example given in the assignment document with no filter and firstdiff terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_FIRSTDIFF_NAME)
					.compute("Zip Code", Terminal.FIRSTDIFF).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_FIRSTDIFF_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineOutsideHeaderFilterDuplicateFilters() {
		System.out
				.println("           Testing the example given in the assignment document with a filter for"
						+ " a header that is not in the .tsv file and both a min and max terminal computation on the same header...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
					.selectAny("Car", 200).compute("Name", Terminal.MIN)
					.compute("Name", Terminal.MAX).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL,
					EXAMPLE_OUTSIDE_HEADER_FILTER_DUPLICATE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineOutsideHeaderFilterMultipleFilters() {
		System.out
				.println("           Testing the example given in the assignment document with a filter for"
						+ " a header that is not in the .tsv file and both a sum and allsame terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
					.selectAny("Car", 200).compute("Age", Terminal.SUM)
					.compute("Zip Code", Terminal.ALLSAME).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL,
					EXAMPLE_OUTSIDE_HEADER_FILTER_MULTIPLE_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineImpossibleFilterAllsame() {
		System.out
				.println("           Testing the example given in the assignment document with a filter no record can meet and allsame terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
					.selectAny("Zip Code", 200)
					.compute("Name", Terminal.ALLSAME).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL,
					EXAMPLE_IMPOSSIBLE_FILTER_ALLSAME_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineOneLongFilterCount() {
		System.out
				.println("           Testing the example given in the assignment document with one long filter and count terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME)
					.selectAny("Age", 18).compute("Cell Phone", Terminal.COUNT)
					.done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL,
					EXAMPLE_ONE_LONG_FILTER_COUNT_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineNoFilterSum() {
		System.out
				.println("           Testing the example given in the assignment document with no filter and sum terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME).compute(
					"Age", Terminal.SUM).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_SUM_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineNoFilterMax() {
		System.out
				.println("           Testing the example given in the assignment document with no filter and max terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME).compute(
					"Name", Terminal.MAX).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_MAX_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineNoFilterMin() {
		System.out
				.println("           Testing the example given in the assignment document with no filter and min terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME).compute(
					"Age", Terminal.MIN).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_MIN_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineNoFilterCount() {
		System.out
				.println("           Testing the example given in the assignment document with no filter and count terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME).compute(
					"Name", Terminal.COUNT).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_COUNT_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testPipelineNoFilterAllsame() {
		System.out
				.println("           Testing the example given in the assignment document with no filter and allsum terminal computation...");
		TSVFilter filter = null;
		try {
			filter = new TSVFilter.Builder(BASE_URL, EXAMPLE_NAME).compute(
					"Zip Code", Terminal.ALLSAME).done();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TSVPipeline pipeline = new TSVPipeline(filter);
		try {
			pipeline.copyTo(BASE_URL, EXAMPLE_ALLSAME_DESTINATION_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final String EXAMPLE_NAME = "example.tsv",
			EXAMPLE_ALLSAME_DESTINATION_NAME = "exampleD4Allsame.tsv",
			EXAMPLE_COUNT_DESTINATION_NAME = "exampleD4Count.tsv",
			EXAMPLE_MIN_DESTINATION_NAME = "exampleD4Min.tsv",
			EXAMPLE_MAX_DESTINATION_NAME = "exampleD4Max.tsv",
			EXAMPLE_SUM_DESTINATION_NAME = "exampleD4Sum.tsv",
			EXAMPLE_ONE_LONG_FILTER_COUNT_DESTINATION_NAME = "exampleD4OneLongFilterCount.tsv",
			EXAMPLE_IMPOSSIBLE_FILTER_ALLSAME_DESTINATION_NAME = "exampleD4ImpossibleFilterAllsum.tsv",
			EXAMPLE_OUTSIDE_HEADER_FILTER_MULTIPLE_DESTINATION_NAME = "exampleD4OutsideHeaderMultiple.tsv",
			EXAMPLE_OUTSIDE_HEADER_FILTER_DUPLICATE_DESTINATION_NAME = "exampleD4OutsideHeaderDuplicate.tsv",
			EXAMPLE_FIRSTDIFF_NAME = "exampleFirstdiff.tsv",
			EXAMPLE_FIRSTDIFF_DESTINATION_NAME = "exampleFirstdiffD4.tsv",
			EXAMPLE_STATS_DESTINATION_NAME = "exampleD4Stats.tsv";
}
