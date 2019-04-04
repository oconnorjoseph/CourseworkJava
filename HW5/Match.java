/**
 * Immediately calculates the results of two {@link StringFigures}s when pinned
 * against each other
 * 
 * @author Joseph O'Connor (jgo2115)
 */
public class Match {
	
	/**
	 * Constructs a new Match given the names of two {@link StringFigure}s
	 * 
	 * @param moveAName
	 *            Name of first of two {@link StringFigure}s to be pinned against each
	 *            other in a match
	 * @param moveB
	 *            Name of second of two {@link StringFigure}s to be pinned against each
	 *            other in a match
	 */
	public Match(StringFigure figureA, StringFigure figureB) {
		MoveOption moveA = getMoveOptionFor(figureA);
		MoveOption moveB = getMoveOptionFor(figureB);
		
		if (moveA.isSuperiorTo(moveB)) {
			winningFigure = figureA;
			loosingFigure = figureB;
		} else {
			winningFigure = figureB;
			loosingFigure = figureA;
		}
	}
	
	private MoveOption getMoveOptionFor(StringFigure figure) {
		String figureValue = figure.getValue();
		figureValue = format(figureValue);
		return RPSGameData.NAME_TO_MOVE_OPTION.get(figureValue);
	}
	
	private String format(String value) {
		return value.trim().toLowerCase();
	}

	/**
	 * @return True if neither {@link StringFigure} is superior to the other,
	 *         false if there is a winner
	 */
	public boolean isTie() {
		return winningFigure == null;
	}

	/**
	 * Assuming the match did not end in a tie:
	 * 
	 * @return The {@link StringFigure} that won the match
	 */
	public StringFigure getWinningFigure() {
		return winningFigure;
	}

	/**
	 * Assuming the match did not end in a tie:
	 * 
	 * @return The {@link StringFigure} that lost the match
	 */
	public StringFigure getLoosingFigure() {
		return loosingFigure;
	}

	/**
	 * @return The outcome of this Match describing what {@link StringFigure}
	 *         defeated what {@StringFigure} and the action verb for
	 *         how that took place
	 */
	public String outcomeAsString() {
		MoveOption winningMove = getMoveOptionFor(winningFigure);
		MoveOption loosingMove = getMoveOptionFor(loosingFigure);
		
		String attackVerb = isTie() ? "ties" : winningMove
				.getAttackVerbAgainst(loosingMove.getName());
		return String.format("%s %s %s", winningMove.getName(), attackVerb,
				loosingMove.getName());
	}

	private StringFigure winningFigure, loosingFigure;
}
