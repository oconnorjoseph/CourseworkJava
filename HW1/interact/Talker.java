package interact;

/**
 * @author Joseph G O'Connor III
 * Responsible for displaying messages to the user
 */
public interface Talker {
	/**
	 * Displays the welcome message to the user
	 */
	public void displayWelcomeText();

	/**
	 * Displays an explanation of the rules of the game
	 */
	public void displayRules();

	/**
	 * Displays the prompt asking the user to provide input choosing a {@link Move}
	 */
	public void displayPromptForInput();

	/**
	 * Displays the match outcome already formatted as a string
	 * @param matchOutcome The outcome of the match formatted as a string
	 */
	public void displayMatchOutcome(String matchOutcome);

	/**
	 * Displays the current match's number, or round, as one more than the given:
	 * @param matchIndex the index of the current match in the {@link MatchGame} loop
	 */
	public void displayCurrentMatchNum(int matchIndex);

	/**
	 * Displays the goodbye message
	 */
	public void displayGoodbyeText();

	/**
	 * Displays the game statistics already formatted as a string
	 * @param gameStats The game statistics already formatted as a string
	 */
	public void displayStats(String gameStats);
}
