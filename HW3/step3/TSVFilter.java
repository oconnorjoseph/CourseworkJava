package step3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Filter to be used by {@link TSVPipeline} for filtering out records meeting
 * certain conditions before eventual copying them to a destination .tsv file
 * and for choosing which terminal computations to perform at the end of copying
 * 
 * @see Terminal
 * 
 *      The conditions are matching-item conditions.
 * 
 *      Matching-item conditions: one or more items, specified by header, that a
 *      record must match (at least one of) to eventually be copied
 * 
 *      Includes a builder so that denoting conditions for the filter are easier
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class TSVFilter {

	private TSVFilter(Builder builder) {
		headerToConditions = builder.headerToConditions;
		headerToTerminal = builder.headerToTerminal;
		originTSVFile = builder.originTSVFile;
	}

	/**
	 * Gets all matching-item conditions for a given header name
	 * 
	 * @param header
	 *            The header name for which to get matching-item conditions
	 * @return all items that a record must match (at least one of) to
	 *         eventually be copied
	 */
	public <T> List<T> getConditionsFor(String header) {
		if (!hasConditionsFor(header)) {
			return null;
		}
		return conditionsClonedFrom(header);

	}

	/**
	 * Gets the terminal computation for a given header name, or returns null if
	 * there is not terminal computation for the given header
	 * 
	 * @param header
	 *            The header name for which to get the terminal computation
	 * @return the chosen terminal computation for the given header name, or
	 *         null if there is not terminal computation chosen for the header
	 *         name
	 */
	public Computation getTerminalComputationFor(String header) {
		if (!hasTerminalComputationFor(header)) {
			return null;
		}
		return headerToTerminal.get(header);

	}

	// Manual adherence to clone-in, clone-out
	@SuppressWarnings("unchecked")
	private <T> List<T> conditionsClonedFrom(String header) {
		List<Object> rawHeaderConditions = headerToConditions.get(header);
		ArrayList<T> headerConditions = new ArrayList<T>();
		for (Object obj : rawHeaderConditions) {
			headerConditions.add((T) obj);
		}
		headerConditions.trimToSize();
		return headerConditions;
	}

	/**
	 * Indicates whether this filter has any matching-item conditions for the
	 * given header name
	 * 
	 * @param header
	 *            The header name for which to get matching-item conditions
	 * @return true if there are any matching-item conditions for a the given
	 *         header name
	 */
	public boolean hasConditionsFor(String header) {
		if (!headerToConditions.containsKey(header)) {
			return false;
		}
		List<?> headerConditions = headerToConditions.get(header);
		return !headerConditions.isEmpty();
	}

	/**
	 * Indicates whether this filter has a terminal computation chosen for the
	 * given header name
	 * 
	 * @param header
	 *            The header name for which to get the terminal computation
	 * @return true if there is a terminal computation chosen for a the given
	 *         header name
	 */
	public boolean hasTerminalComputationFor(String header) {
		if(!headerToTerminal.containsKey(header)) {
			return false;
		}
		return headerToTerminal.get(header) != null;
	}

	/**
	 * Gets the origin .tsv file denoted upon building this TSVFilter instance
	 * 
	 * @return The origin .tsv file denoted upon building this TSVFilter
	 *         instance
	 */
	public File getOriginTSVFile() {
		return originTSVFile;
	}

	/**
	 * Returns a string-representation of this filter exposing the full path of
	 * the origin.tsv file; all conditions, grouped by the header to which they
	 * correspond; and all terminal computations
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("TSVFilter {");

		stringBuilder.append("\n\toriginTSVFile: ");
		stringBuilder.append(originTSVFile.getAbsolutePath());

		stringBuilder.append("\n,\theaderToConditions: ");
		for (Entry<String, List<Object>> entry : headerToConditions.entrySet()) {
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" --> ");
			stringBuilder.append(entry.getValue().toArray());
		}

		stringBuilder.append("\n,\theaderToTerminal: ");
		for (Entry<String, Computation> entry : headerToTerminal.entrySet()) {
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" --> ");
			stringBuilder.append(entry.getValue().getTerminal().name());
		}

		stringBuilder.append("\n}");
		return stringBuilder.toString();
	}

	private final Map<String, List<Object>> headerToConditions;
	private final Map<String, Computation> headerToTerminal;
	private final File originTSVFile;

	/**
	 * Builder for constructing TSVFilter instances
	 * 
	 * Required Information: The origin .tsv File or the origin .tsv File's
	 * directory and filename Optional Information: Any items, specified by
	 * header, to require records to contain
	 * 
	 * @author Joseph O'Connor (jgo2115)
	 * 
	 */
	public static class Builder {

		/**
		 * Constructs a new Builder to make a TSVFilter instance
		 * 
		 * @param originTSVDirectory
		 *            The origin .tsv file's directory from which to eventually
		 *            copy
		 * @param originTSVFilename
		 *            The origin .tsv file's filename from which to eventually
		 *            copy
		 * @throws FileNotFoundException
		 *             if the origin .tsv does not actually exist
		 */
		public Builder(String originTSVDirectory, String originTSVFilename)
				throws FileNotFoundException {
			this(new File(originTSVDirectory, originTSVFilename));
		}

		/**
		 * Constructs a new Builder to make a TSVFilter instance
		 * 
		 * @param originTSVFile
		 *            The origin .tsv file from which to eventually copy
		 * @throws FileNotFoundException
		 *             if the origin .tsv file does not actually exist
		 */
		public Builder(File originTSVFile) throws FileNotFoundException {
			assertExists(originTSVFile);
			this.originTSVFile = originTSVFile;
			headerToConditions = new HashMap<String, List<Object>>();
			headerToTerminal = new HashMap<String, Computation>();
		}

		private void assertExists(File originTSVFile)
				throws FileNotFoundException {
			if (originTSVFile != null && !originTSVFile.exists()) {
				throw new FileNotFoundException(
						"Origin .tsv file does not exist at: "
								+ originTSVFile.getAbsolutePath());
			}
		}

		/**
		 * Adds one or more possible items, specified by a header name, a record
		 * must have (at least one of) in order to be eventually copied
		 * 
		 * Calling this method never overwrites previously added possible
		 * matching-items for a header. Instead, it merely adds another possible
		 * item for the record to contain, specified by header name, that would
		 * allow it to be eventually copied.
		 * 
		 * @param header
		 *            the header name for the column that must have a item
		 *            matching one of the given possible matching items
		 * @param possibleItems
		 *            The possible matching item(s) that a record must have (at
		 *            least one of) for the column with the given header name
		 * @return this TSVFilter.Builder instance
		 */
		public Builder selectAny(String header, Object... possibleItems) {
			assertValidTypesLength(possibleItems);
			assertAreValidTypes(possibleItems);

			if (!headerToConditions.containsKey(header)) {
				List<Object> headerConditions = new ArrayList<Object>();
				headerToConditions.put(header, headerConditions);
			}
			for (Object item : possibleItems) {
				headerToConditions.get(header).add(item);
			}

			return this;
		}

		/**
		 * Sets the terminal computation to be eventually performed for the
		 * column of data under the given header name at the end of the
		 * {@link TSVPipeline}'s streaming.
		 * 
		 * If there has already been a terminal computation set for a given
		 * header name, then the previous terminal computation choice will be
		 * updated to the most recently set terminal computation choice
		 * 
		 * @param header
		 *            The header for which's column the terminal computation
		 *            will be performed
		 * @param terminalComputation
		 *            The chosen {@link Terminal} computation to be eventually
		 *            performed for the given header at the end of
		 *            {@link TSVPipeline}'s streaming
		 * @return this TSVFilter.Builder instance
		 */
		public Builder compute(String header, Terminal terminalComputation) {
			headerToTerminal.put(header, new Computation(terminalComputation));
			return this;
		}

		/**
		 * Builds the actual TSVFilter instance for this TSVFilter.Builder
		 * instance
		 * 
		 * @return The TSVFilter instance constructed based on this
		 *         TSVFilter.Builder instance
		 */
		public TSVFilter done() {
			return new TSVFilter(this);
		}

		private void assertValidTypesLength(Object[] items) {
			if (items == null || items.length == 0) {
				throw new IllegalArgumentException(
						"There must be at least argument for "
								+ "TSVFilter#addFilter parameter 'items'");
			}
		}

		private void assertAreValidTypes(Object[] items) {
			if (!areValidTypes(items)) {
				throw new IllegalArgumentException(
						"All arguments for for TSVFilter#addFilter "
								+ "parameter 'items' must be of the same type and must be either of type String or type Long");
			}
		}

		private boolean areValidTypes(Object[] items) {
			boolean areStrings = items[0] instanceof String;
			boolean areLongs = isLong(items[0]);
			if (!areStrings && !areLongs) {
				return false;
			}
			for (int i = 1; i < items.length; i++) {
				if (areStrings && !(items[i] instanceof String)) {
					return false;
				} else if (areLongs && !isLong(items[i])) {
					return false;
				}
			}
			return true;
		}

		private boolean isLong(Object obj) {
			return (obj instanceof Number) && !(obj instanceof Float)
					&& !(obj instanceof Double);
		}

		private Map<String, List<Object>> headerToConditions;
		private Map<String, Computation> headerToTerminal;
		private File originTSVFile;
	}
}
