package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import struct.MatchTracker;

/**
 * @author Joseph O'Connor
 * Throws a {@link Move} based on the other player's history of {@link Move}s in the current game such that it
 * picks the {@link MoveOption} that defeats the most commonly used {@link MoveOption} of the other player's history
 */
public class SimpleAIThrower extends RandomThrower {

	/**
	 * @param moveOptions All possible {@link MoveOption}s this SimpleAIThrower can chosen from
	 * @param matchTracker The {@linkplain MatchTracker} used by the RPGGame instance to keep a history
	 * of all previous matches of the RPGGame instance
	 * @param userName The name of the player this SimpleAIThrower is playing against
	 * @param throwerName The name of this SimpleAIThrower
	 */
	public SimpleAIThrower(Set<MoveOption> moveOptions,
			MatchTracker matchTracker, String userName, String throwerName) {
		super(moveOptions, throwerName);
		this.matchTracker = matchTracker;
		this.userName = userName;
		initMoveOptionNameToThrownCount(moveOptions);
	}

	private void initMoveOptionNameToThrownCount(Set<MoveOption> moveOptions) {
		moveOptionsToThrownCounts = new HashMap<MoveOption, Integer>();
		for (MoveOption moveOption : moveOptions) {
			moveOptionsToThrownCounts.put(moveOption, 0);
		}
	}

	/**
	 * @return The {@link Move} based on the other player's history of {@link Move}s in the current game such that it
	 * picks the {@link MoveOption} that defeats the most commonly used {@link MoveOption} of the other player's history
	 * UNLESS this {@link Move} is for the first {@link Match} of the game, then a random {@link Move} from the possible {@link MoveOption}s is thrown
	 */
	@Override
	public Move throwMove() {
		if (isFirstThrow()) {
			return super.throwMove();
		} else {
			updateUserThrownCounts();
			MoveOption chosenMoveOption = getSuperiorTo(getMostThrownUserMoveOption());
			return new Move(chosenMoveOption, throwerName);
		}
	}

	private boolean isFirstThrow() {
		return matchTracker.getPreviousMatches().size() == 0;
	}

	private MoveOption getSuperiorTo(MoveOption moveOption) {
		for (MoveOption possibleMoveOption : possibleMoveOptions) {
			if (possibleMoveOption.isSuperiorTo(moveOption)) {
				return possibleMoveOption;
			}
		}
		return null;
	}

	private MoveOption getMostThrownUserMoveOption() {
		int highestCount = -1;
		MoveOption mostThrownMoveOption = null;
		for (Entry<MoveOption, Integer> entry : moveOptionsToThrownCounts
				.entrySet()) {
			if (entry.getValue() > highestCount) {
				mostThrownMoveOption = entry.getKey();
				highestCount = entry.getValue();
			}
		}
		return mostThrownMoveOption;
	}

	private void updateUserThrownCounts() {
		Match lastMatch = matchTracker.getPreviousMatches().get(
				matchTracker.getPreviousMatches().size() - 1);
		MoveOption lastUserMoveOption = lastMatch.getMoveOf(userName);
		int moveOptionThrownCount = moveOptionsToThrownCounts
				.get(lastUserMoveOption);
		moveOptionsToThrownCounts.put(lastUserMoveOption,
				++moveOptionThrownCount);
	}

	private MatchTracker matchTracker;
	private String userName;
	private Map<MoveOption, Integer> moveOptionsToThrownCounts;
}
