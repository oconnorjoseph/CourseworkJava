package interact;

import java.util.Scanner;

import struct.MoveOptionDirectory;
import model.Move;

/**
 * @author Joseph G. O'Connor III
 * Responsible for getting user input from the console, ensuring it's valid, and deciphering it into a {@link Move}
 */
public class ConsoleHearer implements Hearer {

	/**
	 * @param possibleMoveOptions the different possible MoveOptions the user can pick from to make
	 * his or her {@link Move}
	 * @param userName the name of the user to assign to the deciphered {@link Move}
	 */
	public ConsoleHearer(MoveOptionDirectory possibleMoveOptions,
			String userName) {
		lastInput = null;
		this.possibleMoveOptions = possibleMoveOptions;
		this.userName = userName;
	}

	/**
	 * @see interact.Hearer#waitForValidInput()
	 * Blocks the current thread until the user has requested to quit or typed in a shorthand for one of the possible
	 * @see model.MoveOption and then deciphers it into a {@link Move}
	 */
	public void waitForValidInput() {
		@SuppressWarnings("resource")
		// System.in should not be closed
		Scanner inputScanner = new Scanner(System.in);
		while (inputScanner.hasNextLine()) {
			String inputLine = inputScanner.nextLine().trim().toLowerCase();
			if (successfullyParsed(inputLine)) {
				return;
			}
		}
	}

	/**
	 * @see interact.Hearer#isInputQuitRequest()
	 * @return True if the last user input was "quit", otherwise false
	 */
	public boolean isInputQuitRequest() {
		return lastInput == null;
	}

	/**
	 * @see interact.Hearer#inputAsMove()
	 * Gets the last user input as {@link Move}
	 */
	public Move inputAsMove() {
		return lastInput;
	}

	private boolean successfullyParsed(String inputLine) {
		if (isQuit(inputLine)) {
			lastInput = null;
			return true;
		} else if (isMoveOption(inputLine)) {
			lastInput = parseMoveOption(inputLine);
			return true;
		}
		return false;
	}

	private boolean isQuit(String inputLine) {
		return inputLine.toLowerCase().contains("quit");
	}

	private boolean isMoveOption(String inputLine) {
		return inputLine.length() == 1
				&& possibleMoveOptions.contains(inputLine.charAt(0));
	}

	private Move parseMoveOption(String inputLine) {
		char shorthand = inputLine.charAt(0);
		return new Move(possibleMoveOptions.get(shorthand), userName);
	}

	private Move lastInput;
	private MoveOptionDirectory possibleMoveOptions;
	private String userName;
}
