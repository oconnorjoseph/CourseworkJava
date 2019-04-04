package interact;

import java.util.Collection;

import model.MoveOption;

/**
 * @author Joseph G. O'Connor III
 * Responsible for displaying messages to the user via the console
 */
public class ConsoleTalker extends Translator implements Talker {

	/**
	 * @param moveOptions all possible MoveOptions either player may choose
	 * @param gameName the name of the RPS-variation
	 */
	public ConsoleTalker(Collection<MoveOption> moveOptions, String gameName) {
		super(moveOptions);
		this.gameName = gameName;
	}

	/**
	 * @see interact.Talker#displayWelcomeText()
	 * Displays a welcome message as a line on the console
	 */
	public void displayWelcomeText() {
		String welcomeText = String.format("Welcome! Let's play %s!", gameName);
		System.out.println(welcomeText);
	}

	/**
	 * @see interact.Talker#displayRules()
	 * Explains how many players there are, what a player can do to win by explaining the different
	 * choices a player can make are, how to give input to select a choice, and how to quit
	 * via a line on the console.
	 */
	public void displayRules() {
		String moveOptionsDescription = String
				.format("In a match of %s, two players each choose one of the following: %s.",
						gameName, listMoveOptionsByName());
		String winningMoveOptionsDescription = String.format(
				"All choices either win or loose against any other choice. "
						+ "\nThis is how each choice wins: %s.",
				listMoveOptionsByNameWithWinningOutcomes());
		String inputDescription = String
				.format("To make a choice, when prompted, type one of the following shorthands and press 'enter': %s.",
						listMoveOptionsByShorthandsWithNames());
		String quitDescription = "If instead you want to quit, type 'quit' and press 'enter'.";

		System.out.println(moveOptionsDescription);
		System.out.println(winningMoveOptionsDescription);
		System.out.println(inputDescription);
		System.out.println(quitDescription);
	}

	/**
	 * @see interact.Talker#displayPromptForInput()
	 * Asks the user, via a line on the console, to choose a {@link MoveOption} or quit and explains how to do either
	 */
	public void displayPromptForInput() {
		String movePrompt = String
				.format("Please type 'quit' to quit or one of the following: %s. Then press 'enter'.",
						listMoveOptionsByShorthandsWithNames());
		System.out.println(movePrompt);
	}

	/**
	 * @see interact.Talker#displayMatchOutcome(java.lang.String)
	 * Displays the match outcome already formatted as a string via a line on the console
	 * @param matchOutcome The outcome of the match formatted as a string
	 */
	public void displayMatchOutcome(String matchOutcome) {
		String outcomeDescription = String.format("The outcome is: %s.",
				matchOutcome);
		System.out.println(outcomeDescription);
	}

	/**
	 * @see interact.Talker#displayCurrentMatchNum(int)
	 * Displays the current match's number, or round, as one more than the given matchIndex via a line on the console
	 * @param matchIndex the index of the current match in the {@link MatchGame} loop
	 */
	public void displayCurrentMatchNum(int currentMatchIndex) {
		int currentMatchNum = currentMatchIndex + 1;
		String currentMatchText = String.format("Let's play match #%d!",
				currentMatchNum);
		System.out.println(currentMatchText);
	}

	/**
	 * @see interact.Talker#displayGoodbyeText()
	 * Displays a goodbye message as a line on the console
	 */
	public void displayGoodbyeText() {
		String gameOverText = "That's it! Thanks for playing!";
		System.out.println(gameOverText);
	}

	/**
	 * @see interact.Talker#displayStats(java.lang.String)
	 * Displays the game statistics already formatted as a string via a line on the console
	 * @param gameStats The game statistics already formatted as a string
	 */
	public void displayStats(String allMatchStats) {
		String statsDescription = String.format(
				"Here are the results of the game: %s.", allMatchStats);
		System.out.println(statsDescription);
	}

	private String gameName;

}
