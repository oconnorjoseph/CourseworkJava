package interact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import model.MoveOption;

/**
 * @author Joseph G. O'Connor III
 * Formats a collection of MoveOptions into various forms that are all Strings
 */
public class Translator {

	/**
	 * @param moveOptions Collection of MoveOptions hold internally as a list and use for various string representations
	 * of the original MoveOptions
	 */
	public Translator(Collection<MoveOption> moveOptions) {
		sortedMoveOptions = toSortedList(moveOptions);
	}

	private List<MoveOption> toSortedList(Collection<MoveOption> moveOptions) {
		List<MoveOption> moveOptionsList = new ArrayList<MoveOption>(
				moveOptions);
		Collections.sort(moveOptionsList, nameComparator);
		return moveOptionsList;
	}

	/**
	 * @return A String consisting of a comma-separate list of MoveOption names
	 */
	public String listMoveOptionsByName() {
		return listMoveOptions(nameFormatter);
	}

	private String listMoveOptions(
			Function<MoveOption, String> moveOptionFormatter) {
		StringBuilder builder = new StringBuilder();
		for (MoveOption moveOption : sortedMoveOptions) {
			builder.append(moveOptionFormatter.apply(moveOption));
			builder.append(", ");
		}
		int currentStrLength = builder.length();
		builder.delete(currentStrLength - 2, currentStrLength);
		return builder.toString();
	}

	/**
	 * @return A String consisting of a comma-separate list of MoveOption shorthands each enclosed in single-quotes
	 */
	public String listMoveOptionsByShorthand() {
		return listMoveOptions(shorthandFormatter);
	}

	/**
	 * @return A String consisting of a comma-separate list of MoveOption shorthands each enclosed in single-quotes and alongside
	 * its corresponding MoveOption name
	 */
	public String listMoveOptionsByShorthandsWithNames() {
		return listMoveOptions(shorthandWithNameFormatter);
	}

	/**
	 * @returnA String consisting of a comma-separate list of MoveOption names each alongside the
	 * name(s) of inferior MoveOption(s) defeated by it as well as the verb(s) used to describe how it defeats
	 * each inferior MoveOption(s)
	 */
	public String listMoveOptionsByNameWithWinningOutcomes() {
		return listMoveOptions(nameWithWinningOutcomes);
	}

	private List<MoveOption> sortedMoveOptions;

	private static Function<MoveOption, String> nameFormatter = new Function<MoveOption, String>() {

		public String apply(MoveOption moveOption) {
			return moveOption.getName();
		}
	};
	private static Function<MoveOption, String> shorthandFormatter = new Function<MoveOption, String>() {

		public String apply(MoveOption moveOption) {
			return String.format("'%c'", moveOption.getShorthand());
		}
	};
	private static Function<MoveOption, String> shorthandWithNameFormatter = new Function<MoveOption, String>() {

		public String apply(MoveOption moveOption) {
			return String.format("'%c' for %s", moveOption.getShorthand(),
					moveOption.getName());
		}
	};
	private static Function<MoveOption, String> nameWithWinningOutcomes = new Function<MoveOption, String>() {

		public String apply(MoveOption moveOption) {
			StringBuilder builder = new StringBuilder();
			builder.append(moveOption.getName());
			for (String inferiorMoveOptionName : moveOption
					.getInferiorMoveOptionNames()) {
				String winningOutcome = String
						.format(" %s %s", moveOption
								.getAttackVerbAgainst(inferiorMoveOptionName),
								inferiorMoveOptionName);
				builder.append(winningOutcome);
				builder.append(" and");
			}
			int currentLength = builder.length();
			builder.delete(currentLength - 4, currentLength);
			return builder.toString();
		}
	};

	private static Comparator<MoveOption> nameComparator = new Comparator<MoveOption>() {
		public int compare(MoveOption moveOptionA, MoveOption moveOptionB) {
			return moveOptionA.getName().compareTo(moveOptionB.getName());
		}
	};

}
