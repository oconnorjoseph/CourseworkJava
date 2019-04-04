package model;


/**
 * @author Joseph O'Connor III
 * Essentially, a {@link MoveOption} as soon as it is chosen by a player
 * In other words, a {@link MoveOption} with the name of the player whose choose it attached
 */
public class Move extends MoveOption {

	/**
	 * @param selectedMoveOption The {@link MoveOption} selected by:
	 * @param throwerName The player who made, or threw, this Move
	 */
	public Move(MoveOption selectedMoveOption, String throwerName) {
		super(selectedMoveOption.getName(), selectedMoveOption.getShorthand(),
				selectedMoveOption.getSuperiorMoveOptionNames(),
				selectedMoveOption.inferiorNamesToAttackVerbs);
		this.throwerName = throwerName;
	}

	/**
	 * @return The name of the player who made, or threw, this Move
	 */
	public String getThrowerName() {
		return throwerName;
	}

	private String throwerName;
}
