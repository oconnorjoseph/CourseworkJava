package head.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import model.MoveOption;


/**
 * @author  Joseph G. O'Connor III
 *
 * Holds varying game rules used by {@link RPSGameGenerator} in generating each variation of RPS
 */
public class RPSGameData {
	public static final String RPS_GAME_NAME = "Rock Paper Scissors";
	public static final String RPSLK_GAME_NAME = "Rock Paper Scissors Lizard Spock";

	public static final String USER_NAME = "the player";
	public static final String THROWER_NAME = "the computer";

	@SuppressWarnings("serial")
	public static final MoveOption RPS_ROCK = new MoveOption("rock", 'r',
			new HashSet<String>() {
				{
					add("paper");
				}
			}, new HashMap<String, String>() {
				{
					put("scissors", "crushes");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption RPS_PAPER = new MoveOption("paper", 'p',
			new HashSet<String>() {
				{
					add("scissors");
				}
			}, new HashMap<String, String>() {
				{
					put("rock", "covers");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption RPS_SCISSORS = new MoveOption("scissors",
			's', new HashSet<String>() {
				{
					add("rock");
				}
			}, new HashMap<String, String>() {
				{
					put("paper", "cuts");
				}
			});
	@SuppressWarnings("serial")
	public static final Set<MoveOption> POSSIBLE_RPS_MOVE_OPTIONS = new HashSet<MoveOption>() {
		{
			add(RPS_ROCK);
			add(RPS_PAPER);
			add(RPS_SCISSORS);
		}
	};

	@SuppressWarnings("serial")
	public static final MoveOption RPSLK_ROCK = new MoveOption("rock", 'r',
			new HashSet<String>() {
				{
					add("paper");
					add("spock");
				}
			}, new HashMap<String, String>() {
				{
					put("scissors", "crushes");
					put("lizard", "crushes");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption RPSLK_PAPER = new MoveOption("paper", 'p',
			new HashSet<String>() {
				{
					add("scissors");
					add("lizard");
				}
			}, new HashMap<String, String>() {
				{
					put("rock", "covers");
					put("spock", "disproves");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption RPSLK_SCISSORS = new MoveOption("scissors",
			's', new HashSet<String>() {
				{
					add("rock");
					add("spock");
				}
			}, new HashMap<String, String>() {
				{
					put("paper", "cuts");
					put("lizard", "decapitates");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption RPSLK_LIZARD = new MoveOption("lizard", 'l',
			new HashSet<String>() {
				{
					add("rock");
					add("scissors");
				}
			}, new HashMap<String, String>() {
				{
					put("spock", "poisons");
					put("paper", "eats");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption RPSLK_SPOCK = new MoveOption("spock", 'k',
			new HashSet<String>() {
				{
					add("paper");
					add("lizard");
				}
			}, new HashMap<String, String>() {
				{
					put("scissors", "smashes");
					put("rock", "vaporizes");
				}
			});
	@SuppressWarnings("serial")
	public static final Set<MoveOption> POSSIBLE_RPSLK_MOVE_OPTIONS = new HashSet<MoveOption>() {
		{
			add(RPSLK_ROCK);
			add(RPSLK_PAPER);
			add(RPSLK_SCISSORS);
			add(RPSLK_LIZARD);
			add(RPSLK_SPOCK);
		}
	};
}
