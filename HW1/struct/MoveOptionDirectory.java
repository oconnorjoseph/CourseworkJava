/**
 * 
 */
package struct;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import model.MoveOption;

/**
 * @author Joseph G O'Connor III
 * Holds a set of {@link MoveOption}s so that those {@link MoveOption}s can later be looked up by their names and shorthand
 */
public class MoveOptionDirectory {

	/**
	 * @param moveOptions Set of {@link MoveOption}s to build this directory around
	 * @throws IllegalArgumentException if any two {@link MoveOption}s in the set have the same
	 * shorthand or the same name
	 */
	public MoveOptionDirectory(Set<MoveOption> moveOptions) {
		this.moveOptions = new HashSet<MoveOption>();
		for (MoveOption moveOption : moveOptions) {
			assertMoveOptionIsUnique(moveOption, "MoveOptionDirectory");
			this.moveOptions.add(moveOption);
		}
	}

	private void assertMoveOptionIsUnique(MoveOption moveOption,
			String callingMethodName) {
		if (!isMoveOptionUnique(moveOption)) {
			throw new IllegalArgumentException(
					String.format(
							"MoveOption argument for parameter 'moveOption' "
									+ "of MoveOptionDirectory#%s must have a unique name and unique shorthand with regard "
									+ "to the others of the executing MoveOptionDirectory instance.",
							callingMethodName));
		}
	}

	private boolean isMoveOptionUnique(MoveOption moveOption) {
		return !contains(moveOption.getName())
				&& !contains(moveOption.getShorthand());
	}

	/**
	 * @param moveOptionName The name of the {@link MoveOption} to check if this MoveOptionDirectory's internal set of {@link MoveOption}s contains
	 * @return true if this MoveOptionDirectory's internal set of {@link MoveOption}s contains a {@link MoveOption} with the given name, false if not
	 */
	public boolean contains(final String moveOptionName) {
		return get(moveOptionName) != null;
	}

	/**
	 * @param moveOptionShorthand The shorthand of the {@link MoveOption} to check if this MoveOptionDirectory's internal set of {@link MoveOption}s contains
	 * @return true if this MoveOptionDirectory's internal set of {@link MoveOption}s contains a {@link MoveOption} with the given shorthand, false if not
	 */
	public boolean contains(final char moveOptionShorthand) {
		return get(moveOptionShorthand) != null;
	}

	/**
	 * @param moveOptionName The name of the {@link MoveOption} to get from this MoveOptionDirectory's internal set of {@link MoveOption}s
	 * @return The {@link MoveOption} from this MoveOptionDirectory's internal set of {@link MoveOption}s with the given name
	 * If no {@link MoveOption} is found, null is returned
	 */
	public MoveOption get(final String moveOptionName) {
		Predicate<MoveOption> hasNameOfArg = new Predicate<MoveOption>() {

			public boolean test(MoveOption moveOption) {
				return moveOption.getName().equals(moveOptionName);
			}
		};
		return getMoveOptionSatisfying(hasNameOfArg);
	}

	/**
	 * @param moveOptionName The shorthand of the {@link MoveOption} to get from this MoveOptionDirectory's internal set of {@link MoveOption}s
	 * @return The {@link MoveOption} from this MoveOptionDirectory's internal set of {@link MoveOption}s with the given shorthand
	 * If no {@link MoveOption} is found, null is returned
	 */
	public MoveOption get(final char moveOptionShorthand) {
		Predicate<MoveOption> hasShorthandOfArg = new Predicate<MoveOption>() {

			public boolean test(MoveOption moveOption) {
				return moveOption.getShorthand() == moveOptionShorthand;
			}
		};
		return getMoveOptionSatisfying(hasShorthandOfArg);
	}

	private MoveOption getMoveOptionSatisfying(Predicate<MoveOption> condition) {
		Iterator<MoveOption> moveOptionsIterator = moveOptions.iterator();
		while (moveOptionsIterator.hasNext()) {
			MoveOption nextMoveOption = moveOptionsIterator.next();
			if (condition.test(nextMoveOption)) {
				return nextMoveOption;
			}
		}
		return null;
	}

	/**
	 * @return This MoveOptionDirectory's internal set of all {@link MoveOptions}
	 */
	public Set<MoveOption> getAll() {
		return moveOptions;
	}

	private Set<MoveOption> moveOptions;
}
