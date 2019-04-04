package struct;

import java.util.List;
import java.util.function.Predicate;

import model.Match;

/**
 * @author Joseph G O'Connor III
 * 
 * Extends the {@link MatchTracker} by offering statistics on the game's previous matches
 */
public class MatchStatistician extends MatchTracker {

	/**
	 * @return The total number of previous matches played in the game
	 */
	public int countMatches() {
		return getPreviousMatches().size();
	}

	/**
	 * @param throwerName The name of the player for which to count the number of winning matches
	 * @return The total number of previous matches played in the game that the player with the name of the
	 * given argument won
	 */
	public int countMatchesWonBy(final String throwerName) {
		Predicate<Match> isWonByThrowerArg = new Predicate<Match>() {

			public boolean test(Match match) {
				return !match.isTie()
						&& match.getWinningMove().getThrowerName()
								.equals(throwerName);
			}
		};
		return countMatchesSatisfying(isWonByThrowerArg);
	}

	private int countMatchesSatisfying(Predicate<Match> condition) {
		int count = 0;
		for (Match match : getPreviousMatches()) {
			if (condition.test(match)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * @return The total number of previous matches in the game that ended in a tie
	 */
	public int countMatchesTied() {
		Predicate<Match> isTie = new Predicate<Match>() {

			public boolean test(Match match) {
				return match.isTie();
			}
		};
		return countMatchesSatisfying(isTie);
	}

	/**
	 * @param throwerName The name of the player for which to get the percentage the winning matches
	 * @return The percentage of all previous matches played in the game that the player with the name of the
	 * given argument won
	 */
	public float percentMatchesWonBy(final String throwerName) {
		return (((float) countMatchesWonBy(throwerName)) / countMatches()) * 100;
	}

	/**
	 * @return The percentage of previous matches in the game that ended in a tie
	 */
	public float percentMatchesTied() {
		return (((float) countMatchesTied()) / countMatches()) * 100;
	}

	/**
	 * Formats the total number of previous matches, the number of matches each player won,
	 * the percentage of all previous matches won by each player, the number of matches that ended in a tie,
	 * and the percentage of all previous matches that ended in a tie as a comma-separated list in a String
	 * @return Comma-separated list in a String containing the total number of previous matches, the number of matches each player won,
	 * the percentage of all previous matches won by each player, the number of matches that ended in a tie,
	 * and the percentage of all previous matches that ended in a tie
	 */
	public String allStatsAsString() {
		List<Match> previousMatches = getPreviousMatches();
		if (previousMatches.size() == 0) {
			return "no matches were played";
		} else {
			String[] throwerNames = getPreviousMatches().get(0)
					.getThrowerNames();
			return String.format("Of the %s: %s won %s, or %.1f%% of matches; "
					+ "%s won %s, or %.1f%% of matches; "
					+ "and %s, or %.1f%% of matches, ended in a tie",
					formatCountWithUnit(countMatches()), throwerNames[0],
					formatCountWithUnit(countMatchesWonBy(throwerNames[0])),
					percentMatchesWonBy(throwerNames[0]), throwerNames[1],
					formatCountWithUnit(countMatchesWonBy(throwerNames[1])),
					percentMatchesWonBy(throwerNames[1]),
					formatCountWithUnit(countMatchesTied()),
					percentMatchesTied());
		}
	}

	private String formatCountWithUnit(int count) {
		String unit = (count == 1) ? "match" : "matches";
		return String.format("%d %s", count, unit);
	}
}
