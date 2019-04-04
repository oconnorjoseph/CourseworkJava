package interact;

import model.Move;
import model.Thrower;

/**
 * @author Joseph G O'Connor III
 * Pretends to seek user input but instead fetches each {@link Move} from a designated {@link Thrower}
 */
public class SimHearer implements Hearer {

	/**
	 * @param simPlayer {@link Thrower} to treat as the user
	 */
	public SimHearer(Thrower simPlayer) {
		this.simPlayer = simPlayer;
	}

	/**
	 * @see interact.Hearer#waitForValidInput()
	 * Never actually blocks the current thread because the thrower never quits and always gives a valid {@link Move}
	 * in {@link #inputAsMove()}
	 */
	public void waitForValidInput() {
		// Simulated player always immediately gives valid input when requested in
		// inputAsMove()
	}

	/**
	 * @see interact.Hearer#isInputQuitRequest()
	 * Always returns false because simulated player never quits
	 */
	public boolean isInputQuitRequest() {
		return false; // Simulated player never quits
	}

	/**
	 * @see interact.Hearer#inputAsMove()
	 * @return {@link Move} thrown by the {@link Thrower} chosen at construction
	 */
	public Move inputAsMove() {
		return simPlayer.throwMove();
	}

	private Thrower simPlayer;

}
