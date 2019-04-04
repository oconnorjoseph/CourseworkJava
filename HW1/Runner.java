import head.MatchGame;
import head.RPSGameGenerator;

/**
 * @author Joseph G. O'Connor III
 *
 */
public class Runner {

	/**
	 * Entry point for the RPSLK application
	 */
	public static void main(String... args) {
		// MatchGame currentGame = RPSGameGenerator.basicRPS();
		// MatchGame currentGame = RPSGameGenerator.RPSWithSimpleAI();
		// MatchGame currentGame = RPSGameGenerator.RPSLKWithSimpleAI();
		// MatchGame currentGame = RPSGameGenerator.RPSLKWithSimPlayerAndSimpleAI();
		MatchGame currentGame = RPSGameGenerator.RPSLKWithSimPlayerAndMAIGA();
		currentGame.start();
		currentGame.loopFor(200);
		currentGame.end();
	}

}
