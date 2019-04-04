package struct;

import java.util.ArrayList;
import java.util.List;

import model.Match;

/**
 * @author Joseph G. O'Connor III
 * Used to track the game's history of matches for inspection during or at the end of the game
 */
public class MatchTracker {

	/**
	 * Creates a new MatchTrack by initializing its internal list of previous matches of the game
	 */
	public MatchTracker() {
		previousMatches = new ArrayList<Match>();
	}

	/**
	 * @return The internal list of previous matches of the game
	 */
	public List<Match> getPreviousMatches() {
		return previousMatches;
	}

	/**
	 * @param match The {@link Match} to add to the internal list of previous matches of the game
	 * Adds the given match argument to the internal list of previous matches
	 */
	public void track(Match match) {
		assertMatchIsNotNull(match, "add");
		previousMatches.add(match);
	}

	private void assertMatchIsNotNull(Match match, String callingMethodName) {
		if (match == null) {
			throw new IllegalArgumentException(String.format(
					"Match argument for parameter 'match' "
							+ "of MatchHistory#%s must be non-null.",
					callingMethodName));
		}
	}

	private List<Match> previousMatches;
}
