package step3;

/**
 * Holds information for a {@link Terminal} computation that can be continuously
 * updated and then observed at any point
 * 
 * Progress for a Computation cannot be reverse but only moved forward (e.g. if
 * the computation is for Terminal.COUNT, the computation's count cannot be
 * decreased), and the behavior in which a Computation progresses is determined
 * by the {@link Terminal}
 * 
 * Updating by passing in an appropriate item of .tsv file, which is an item of
 * just filtered record.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Computation {

	/**
	 * Constructs a new Computation instance from a given {@link Terminal}
	 * 
	 * @param terminal
	 *            Terminal that defines the behavior of the new Computation
	 *            instance
	 */
	public Computation(Terminal terminal) {
		this.terminal = terminal;
	}

	/**
	 * Exposes the {@link Terminal} defining the behavior of this Computation
	 * instance
	 * 
	 * @return The Terminal defining he behavior of this Computation instance
	 */
	public Terminal getTerminal() {
		return this.terminal;
	}

	/**
	 * Progresses the Computation forward based on the given item
	 * 
	 * @param item
	 *            An item of a just-filtered record
	 */
	public void update(Comparable item) {
		switch (this.terminal) {
		case ALLSAME:
			updateAllsame(item);
			break;
		case COUNT:
			updateCount();
			break;
		case MIN:
			updateMin(item);
			break;
		case MAX:
			updateMax(item);
			break;
		case SUM:
			updateSum(item);
			break;
		default:
			break;
		}
	}

	/**
	 * @return Formatted string representation of the computation at the current
	 *         moment
	 * 
	 *         To be used after the name of a column header
	 * 
	 *         This should be called at the end of a stream's lifetime, but can
	 *         be called anytime without breaking the computation's future
	 *         progress
	 */
	public String status() {
		switch (this.terminal) {
		case ALLSAME:
			return allsameStatus();
		case COUNT:
			return countStatus();
		case MIN:
			return minStatus();
		case MAX:
			return maxStatus();
		case SUM:
			return sumStatus();
		default:
			return null;
		}
	}

	private String sumStatus() {
		return " has a sum of " + accumulation;
	}

	private String maxStatus() {
		return " has a max of " + temp.toString();
	}

	private String minStatus() {
		return " has a min of " + temp.toString();
	}

	private String countStatus() {
		return " has a count of " + accumulation;
	}

	private String allsameStatus() {
		if (allsame) {
			return " is all the same";
		} else {
			return " is not all the same";
		}
	}

	private void updateSum(Object item) {
		assertIsLong(item);
		accumulation += Long.parseLong(item.toString());
	}

	private void assertIsLong(Object item) {
		if (!isLong(item)) {
			throw new NumberFormatException(this.terminal.name()
					+ " terminal computation cannot "
					+ "be computed for non-long types");
		}
	}

	private boolean isLong(Object obj) {
		if ((obj instanceof Number) && !(obj instanceof Float)
				&& !(obj instanceof Double)) {
			return true;
		} else if (obj instanceof String) {
			try {
				Long.parseLong((String) obj);
			} catch (NumberFormatException e) {
				return false;
			} catch (ClassCastException e) {
				return false;
			}
			return true;
		}
		return false;
	}

	private void updateMax(Comparable item) {
		if (item != null && (temp == null || item.compareTo(temp) > 0)) {
			temp = item;
		}
	}

	private void updateMin(Comparable item) {
		if (item != null && (temp == null || item.compareTo(temp) < 0)) {
			temp = item;
		}
	}

	private void updateCount() {
		accumulation++;
	}

	private void updateAllsame(Comparable item) {
		if (allsame) {
			if (item == null && temp != null) {
				allsame = false;
			} else if (item != null && temp != null
					&& item.compareTo(temp) != 0) {
				allsame = false;
			} else {
				temp = item;
			}
		}
	}

	// Allsame
	private boolean allsame = true;
	// General
	private long accumulation = 0;
	private Comparable temp = null;

	private Terminal terminal;
}
