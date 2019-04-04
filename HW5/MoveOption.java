import java.util.Map;
import java.util.Set;

/**
 * @author Joseph O'Connor
 * Single choice a player of Rock Paper Scissor, or some variation of the game, can make for a move
 */
public class MoveOption {

	/**
	 * @param name The name of this MoveOption
	 * @param shorthand The shorthand used to choose this MoveOption
	 * @param superiorMoveOptionNames The names of all MoveOptions that will defeat this MoveOption in a {@link Match}
	 * @param inferiorNameToAttackVerbAgainstInferiorPairs The mapping of the names of all MoveOptions that will be 
	 * defeated by this MoveOption in a {@link Match} to the verb used by this MoveOption in defeating the inferior MoveOption
	 */
	public MoveOption(String name, char shorthand,
			Set<String> superiorMoveOptionNames,
			Map<String, String> inferiorNameToAttackVerbAgainstInferiorPairs) {
		this.name = name;
		this.shorthand = shorthand;
		this.superiorMoveOptionNames = superiorMoveOptionNames;
		this.inferiorNamesToAttackVerbs = inferiorNameToAttackVerbAgainstInferiorPairs;
	}

	/**
	 * @return The name of this MoveOption
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The shorthand used to choose this MoveOption
	 */
	public char getShorthand() {
		return shorthand;
	}

	/**
	 * @return The names of all MoveOptions that will defeat this MoveOption in a {@link Match}
	 */
	public Set<String> getSuperiorMoveOptionNames() {
		return superiorMoveOptionNames;
	}

	/**
	 * @return The names of all MoveOptions that will be defeated by this MoveOption in a {@link Match}
	 */
	public Set<String> getInferiorMoveOptionNames() {
		return inferiorNamesToAttackVerbs.keySet();
	}

	/**
	 * @param inferiorMoveOptionName The name of the MoveOption that will be defeated by this MoveOption in a {@link Match}
	 * @return the verb used in describing how this MoveOption defeats the inferior MoveOption in a {@link Match}
	 */
	public String getAttackVerbAgainst(String inferiorMoveOptionName) {
		assertIsSuperiorTo(inferiorMoveOptionName, "getAttackVerbAgainst");
		return inferiorNamesToAttackVerbs.get(inferiorMoveOptionName);
	}

	private void assertIsSuperiorTo(String inferiorMoveOptionName,
			String callingMethodName) {
		if (!inferiorNamesToAttackVerbs.containsKey(inferiorMoveOptionName)) {
			throw new IllegalArgumentException(
					String.format(
							"MoveOption argument for parameter 'inferiorMoveOption' "
									+ "of MoveOption#%s must be a MoveOption inferior to the executing MoveOption instance.",
							callingMethodName));
		}
	}

	/**
	 * @return True if this MoveOption is superior to the given @param moveOption
	 */
	public boolean isSuperiorTo(MoveOption moveOption) {
		return getInferiorMoveOptionNames().contains(moveOption.getName());
	}

	/**
	 * @param moveOption The other MoveOption to test in a hypothetical {@link Match}
	 * @return The outcome of the hypothetical {@link Match} between this MoveOption and the given other MoveOption
	 */
	public String outcomeAsStringAgainst(MoveOption moveOption) {
		if (this.equals(moveOption)) {
			return String.format("%s ties itself", getName());
		} else if (isSuperiorTo(moveOption)) {
			return String.format("%s %s %s", getName(),
					getAttackVerbAgainst(moveOption.getName()),
					moveOption.getName());
		} else {
			return moveOption.outcomeAsStringAgainst(this);
		}
	}

	/* 
	 * Two MoveOptions are equal if their names equal 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MoveOption)) {
			return false;
		}
		MoveOption moveOption = (MoveOption) obj;
		return getName() == moveOption.getName();
	}

	/*
	 * The hashCode of a MoveOption is the hashCode of its name
	 */
	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	private String name;
	private char shorthand;
	private Set<String> superiorMoveOptionNames;
	protected Map<String, String> inferiorNamesToAttackVerbs;
}