package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import struct.MatchTracker;

/**
 * @author Joseph G. O'Connor III
 * Throws a {@link Move} based on the other player's history of {@link Move}s in the current game such that it
 * predicts that the user will have a preference for some {@link MoveOption}s as evident in the long-term history of the game
 * but is unlike recent {@link MoveOption}s the user remembers choosing in the short-term
 */
public class MAIGAThrower extends RandomThrower {

	/**
	 * @param moveOptions
	 * @param matchTracker
	 * @param userName
	 * @param throwerName
	 */
	public MAIGAThrower(Set<MoveOption> moveOptions, MatchTracker matchTracker,
			String userName, String throwerName) {
		super(moveOptions, throwerName);
		this.matchTracker = matchTracker;
		this.userName = userName;
	}

	/**
	 *  @return {@link Move} based on the other player's history of {@link Move}s in the current game such that it
	 * predicts that the user will have a preference for some {@link MoveOption}s as evident in the long-term history of the game
	 * but is unlike recent {@link MoveOption}s the user remembers choosing in the short-term
	 * UNLESS this {@link Move} is for the first {@link Match} of the game, then a random {@link Move} from the possible {@link MoveOption}s is thrown
	 */
	@Override
	public Move throwMove() {
		if (isFirstThrow()) {
			return super.throwMove();
		} else {
			return new Move(highestWeightedMoveOption(), throwerName);
		}
	}

	private boolean isFirstThrow() {
		return matchTracker.getPreviousMatches().size() == 0;
	}

	private MoveOption highestWeightedMoveOption() {
		double highestWeight = Double.NEGATIVE_INFINITY;
		MoveOption highestWeightMoveOption = null;
		for (Entry<MoveOption, Double> entry : moveOptionsToWeights()
				.entrySet()) {
			if (entry.getValue() > highestWeight) {
				highestWeightMoveOption = entry.getKey();
				highestWeight = entry.getValue();
			}
		}
		return highestWeightMoveOption;
	}

	private Map<MoveOption, Double> moveOptionsToWeights() {
		Map<MoveOption, Double> moveOptionsToWeights = new HashMap<MoveOption, Double>();
		for (MoveOption moveOption : possibleMoveOptions) {
			double moveOptionWeight = calcTotalWeightFor(moveOption);
			moveOptionsToWeights.put(moveOption, moveOptionWeight);
		}
		return moveOptionsToWeights;
	}

	private double calcTotalWeightFor(MoveOption moveOption) {
		List<Match> previousMatches = matchTracker.getPreviousMatches();
		int inflectionIndex = (previousMatches.size() - 1) - 7;
		// InflectionIndex is 7 below last index as 7 is the upper-limit of how
		// many things
		// a normal human can remember at once
		double totalWeight = 0;
		for (int i = 0; i < previousMatches.size(); i++) {
			if (previousMatches.get(i).getMoveOf(userName).getName() == moveOption
					.getName()) {
				totalWeight += calcWeightFor(i, inflectionIndex);
			}
		}
		return totalWeight;
	}

	private double calcWeightFor(int index, int inflectionIndex) {
		if (index < inflectionIndex) {
			return ((inflectionIndex - index) / (inflectionIndex));
		} else {
			return (inflectionIndex - index);
		}
	}

	private MatchTracker matchTracker;
	private String userName;
}
