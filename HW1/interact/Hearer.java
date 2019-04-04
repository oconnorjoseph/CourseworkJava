package interact;

import model.Move;

/**
 * @author Joseph G. O'Connor III
 * Responsible for getting user input, ensuring it's valid, and deciphering it into an {@link Move}
 */
public interface Hearer {
	/**
	 * Blocks the current thread until valid input has been detected
	 */
	public void waitForValidInput();

	/**
	 * @return true if the last valid input detected was a request by the user to quit the game or false if not
	 */
	public boolean isInputQuitRequest();

	/**
	 * assuming the last valid input detected was deciperable into a {@link Move}
	 * @return the user's chosen {@link Move}
	 */
	public Move inputAsMove();
}
