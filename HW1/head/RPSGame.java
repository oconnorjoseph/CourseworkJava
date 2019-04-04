package head;

import interact.Hearer;
import interact.Talker;
import model.Match;
import model.Move;
import model.Thrower;
import struct.MatchStatistician;

/**
 * @author Joseph G. O'Connor III
 * 
 * Encapsulates a Rock Paper Scissors game, or some variation of Rock Paper Scissors 
 * (i.e. Rock Paper Scissors Lizard Spock), that can survive for multiple matches
 */
public class RPSGame implements MatchGame {

	/**
	 * @param gameName Name of this Rock Paper Scissors game (e.g. "Rock Paper Scissors Lizard Spock"
	 * @param thrower Implementation of {@link Thrower} interface responsible for automated player's, or computer's, participation
	 * @param talker Implementation of {@link Talker} interface responsible for displaying game information to user
	 * @param hearer Implementation of {@link Hearer} interface responsible for getting user input
	 * @param matchStat {@link MatchStatistician} to which matches will be feed in order to gather game statistics at the end of the game
	 */
	public RPSGame(String gameName, Thrower thrower, Talker talker,
			Hearer hearer, MatchStatistician matchStat) {
		this.thrower = thrower;
		this.talker = talker;
		this.hearer = hearer;
		this.matchStat = matchStat;
	}

	/**
	 * @see head.MatchGame#start()
	 * Displays a message welcoming the user as well as a description of the game rules
	 */
	public void start() {
		talker.displayWelcomeText();
		talker.displayRules();
	}

	/**
	 * @see head.MatchGame#loopFor(int)
	 * For each match: displays how many matches the user has played (by displaying the number of the current match),
	 * then prompts for and gets user input, and finally displays and tracks the outcome of the match
	 */
	public void loopFor(int maxCountMatches) {
		for (int i = 0; i < maxCountMatches; i++) {
			talker.displayCurrentMatchNum(i);
			waitForMatchInput();
			if (hearer.isInputQuitRequest()) {
				return;
			}
			conductMatch();
		}
	}

	private void waitForMatchInput() {
		talker.displayPromptForInput();
		hearer.waitForValidInput();
	}

	private void conductMatch() {
		Move throwerMove = thrower.throwMove();
		Move playerMove = hearer.inputAsMove();
		Match currentMatch = new Match(throwerMove, playerMove);

		talker.displayMatchOutcome(currentMatch.outcomeAsString());
		matchStat.track(currentMatch);
	}

	/**
	 * @see head.MatchGame#end()
	 * Displays the goodbye message as well as overall game statistics
	 */
	public void end() {
		talker.displayGoodbyeText();
		talker.displayStats(matchStat.allStatsAsString());
	}

	private MatchStatistician matchStat;
	private Thrower thrower;
	private Talker talker;
	private Hearer hearer;

}
