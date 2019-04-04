package model;

/**
 * 
 */

/**
 * @author Joseph G O'Connor III
 * Immediately calculates the results of two {@link Move}s when pinned against eachother
 */
public class Match {

	/**
	 * @param moveA One Move by someone
	 * @param moveB Another Move by someone else
	 */
	public Match(Move moveA, Move moveB) {
		if (moveA.isSuperiorTo(moveB)) {
			winningMove = moveA;
			loosingMove = moveB;
		} else {
			winningMove = moveB;
			loosingMove = moveA;
		}
	}

	/**
	 * @return True if neither {@link Move} is superior to the other, false if there is a winner
	 */
	public boolean isTie() {
		return winningMove.equals(loosingMove);
	}

	/**
	 * Assuming the match did not end in a tie:
	 * @return The {@link Move} that won the match
	 */
	public Move getWinningMove() {
		assertIsNotTie("getWinningMove");
		return winningMove;
	}

	/**
	 * Assuming the match did not end in a tie:
	 * @return The {@link Move} that lost the match
	 */
	public Move getLoosingMove() {
		assertIsNotTie("getLoosingMove");
		return loosingMove;
	}

	/**
	 * @param throwerName Whoever threw the desired Move
	 * @return The desired Move whose thrower has the given name
	 */
	public Move getMoveOf(String throwerName) {
		if (winningMove.getThrowerName() == throwerName) {
			return winningMove;
		} else if (loosingMove.getThrowerName() == throwerName) {
			return loosingMove;
		} else {
			throw new IllegalArgumentException(String.format(
					"throwerName argument '%s' for Match#getMoveOf"
							+ " did not participate in this Match instance.",
					throwerName));
		}
	}

	private void assertIsNotTie(String callingMethodName) {
		if (isTie()) {
			throw new RuntimeException(
					String.format(
							"Match#%s can only be accessed when Match.isTie() is false.",
							callingMethodName));
		}
	}

	/**
	 * @return The names of whoever threw the Moves in this Match
	 */
	public String[] getThrowerNames() {
		return new String[] { winningMove.getThrowerName(),
				loosingMove.getThrowerName() };
	}

	/**
	 * @return The outcome of this Match describing who won against who and how formatted as a String
	 */
	public String outcomeAsString() {
		String attackVerb = isTie() ? "ties" : winningMove
				.getAttackVerbAgainst(loosingMove.getName());
		return String
				.format("%s's %s %s %s's %s", winningMove.getThrowerName(),
						winningMove.getName(), attackVerb,
						loosingMove.getThrowerName(), loosingMove.getName());
	}

	private Move winningMove, loosingMove;
}
