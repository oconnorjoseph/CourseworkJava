package head;

import struct.MatchStatistician;
import struct.MoveOptionDirectory;
import model.MAIGAThrower;
import model.RandomThrower;
import model.SimPlayerThrower;
import model.SimpleAIThrower;
import model.Thrower;
import interact.ConsoleHearer;
import interact.ConsoleTalker;
import interact.Hearer;
import interact.SimHearer;
import interact.Talker;
import head.data.RPSGameData;

/**
 * @author Joseph G O'Connor III
 * 
 *         Can be used to get an instance of the Rock Paper Scissors games for
 *         each of the 5 different steps listed in the assignment
 */
public class RPSGameGenerator {

	/**
	 * @return Basic Rock Paper Scissors game as asked for in Step 1 of the
	 *         assignment
	 */
	public static RPSGame basicRPS() {
		MatchStatistician matchStat = new MatchStatistician();
		Thrower randomThrower = new RandomThrower(
				RPSGameData.POSSIBLE_RPS_MOVE_OPTIONS, RPSGameData.THROWER_NAME);
		Talker consoleTalker = new ConsoleTalker(
				RPSGameData.POSSIBLE_RPS_MOVE_OPTIONS,
				RPSGameData.RPS_GAME_NAME);
		MoveOptionDirectory moveOptionDirectory = new MoveOptionDirectory(
				RPSGameData.POSSIBLE_RPS_MOVE_OPTIONS);
		Hearer consoleHearer = new ConsoleHearer(moveOptionDirectory,
				RPSGameData.USER_NAME);
		return new RPSGame(RPSGameData.RPS_GAME_NAME, randomThrower,
				consoleTalker, consoleHearer, matchStat);
	}

	/**
	 * @return Basic Rock Paper Scissors game but with a simple AI based on user
	 *         input history as asked for in Step 2 of the assignment
	 */
	public static RPSGame RPSWithSimpleAI() {
		MatchStatistician matchStat = new MatchStatistician();
		Thrower simpleAIThrower = new SimpleAIThrower(
				RPSGameData.POSSIBLE_RPS_MOVE_OPTIONS, matchStat,
				RPSGameData.USER_NAME, RPSGameData.THROWER_NAME);
		Talker consoleTalker = new ConsoleTalker(
				RPSGameData.POSSIBLE_RPS_MOVE_OPTIONS,
				RPSGameData.RPS_GAME_NAME);
		MoveOptionDirectory moveOptionDirectory = new MoveOptionDirectory(
				RPSGameData.POSSIBLE_RPS_MOVE_OPTIONS);
		Hearer consoleHearer = new ConsoleHearer(moveOptionDirectory,
				RPSGameData.USER_NAME);
		return new RPSGame(RPSGameData.RPS_GAME_NAME, simpleAIThrower,
				consoleTalker, consoleHearer, matchStat);
	}

	/**
	 * @return Rock Paper Scissors Lizard Spock game with simple AI based on user input history as asked for in Step 3 of the assignment
	 */
	public static RPSGame RPSLKWithSimpleAI() {
		MatchStatistician matchStat = new MatchStatistician();
		Thrower simpleAIThrower = new SimpleAIThrower(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS, matchStat,
				RPSGameData.USER_NAME, RPSGameData.THROWER_NAME);
		Talker consoleTalker = new ConsoleTalker(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS,
				RPSGameData.RPSLK_GAME_NAME);
		MoveOptionDirectory moveOptionDirectory = new MoveOptionDirectory(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS);
		Hearer consoleHearer = new ConsoleHearer(moveOptionDirectory,
				RPSGameData.USER_NAME);
		return new RPSGame(RPSGameData.RPSLK_GAME_NAME, simpleAIThrower,
				consoleTalker, consoleHearer, matchStat);
	}

	/**
	 * @return Rock Paper Scissors Lizard Spock game with a simulated player against the simple AI based on user input history
	 * as asked for in Step 4 of the assignment
	 */
	public static RPSGame RPSLKWithSimPlayerAndSimpleAI() {
		MatchStatistician matchStat = new MatchStatistician();
		Thrower simpleAIThrower = new SimpleAIThrower(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS, matchStat,
				RPSGameData.USER_NAME, RPSGameData.THROWER_NAME);
		Talker consoleTalker = new ConsoleTalker(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS,
				RPSGameData.RPSLK_GAME_NAME);
		Thrower simPlayerThrower = new SimPlayerThrower(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS, RPSGameData.USER_NAME);
		Hearer simHearer = new SimHearer(simPlayerThrower);
		return new RPSGame(RPSGameData.RPSLK_GAME_NAME, simpleAIThrower,
				consoleTalker, simHearer, matchStat);
	}

	/**
	 * @return Rock Paper Scissors Lizard Spock game with a simulated player against a more intelligent "MAIGA"
	 * AI, which predicts user input by assuming user input demonstrates a preference in the long-term history of the game
	 * but is unlike recent user input the user remembers in the short-term
	 */
	public static RPSGame RPSLKWithSimPlayerAndMAIGA() {
		MatchStatistician matchStat = new MatchStatistician();
		Thrower MAIGAThrower = new MAIGAThrower(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS, matchStat,
				RPSGameData.USER_NAME, RPSGameData.THROWER_NAME);
		Talker consoleTalker = new ConsoleTalker(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS,
				RPSGameData.RPSLK_GAME_NAME);
		Thrower simPlayerThrower = new SimPlayerThrower(
				RPSGameData.POSSIBLE_RPSLK_MOVE_OPTIONS, RPSGameData.USER_NAME);
		Hearer simHearer = new SimHearer(simPlayerThrower);
		return new RPSGame(RPSGameData.RPSLK_GAME_NAME, MAIGAThrower,
				consoleTalker, simHearer, matchStat);
	}
}
