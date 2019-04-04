package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * @author Joseph G. O'Connor
 * Used to simulate player throws, this {@link Thrower} cycles through the given possible {@link MoveOption}s
 * when called upon to give a Move
 */
public class SimPlayerThrower implements Thrower {

	/**
	 * @param possibleMoveOptions Set of {@link MoveOption}s this RandomThrower can choose from to make a {@link Move}
	 * @param throwerName the name of this simulated player
	 */
	public SimPlayerThrower(Set<MoveOption> possibleMoveOptions,
			String throwerName) {
		orderedMoveOptions = new ArrayDeque<MoveOption>(possibleMoveOptions);
		this.throwerName = throwerName;
	}

	/**
	 * @see model.Thrower#getName()
	 */
	public String getName() {
		return throwerName;
	}

	/**
	 * @see model.Thrower#throwMove()
	 * The chosen {@link MoveOption} for the {@link Move} to throw is the next {@MoveOption} this Thrower is to use
	 * as it cycles through all possible {@link MoveOption}s
	 */
	public Move throwMove() {
		MoveOption moveOption = nextMoveOptionInCycle();
		return new Move(moveOption, throwerName);
	}

	private MoveOption nextMoveOptionInCycle() {
		MoveOption nextMoveOption = orderedMoveOptions.pollFirst();
		orderedMoveOptions.offerLast(nextMoveOption);
		return nextMoveOption;
	}

	private String throwerName;
	private Deque<MoveOption> orderedMoveOptions;
}
