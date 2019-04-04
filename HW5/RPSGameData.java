import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

/**
 * @author Joseph O'Connor (jgo2115)
 * 
 *         Holds the options for moves in RPSLKH a game as well a mapping of
 *         move names to their corresponding {@link MoveOption} objects
 */
public class RPSGameData {
	@SuppressWarnings("serial")
	public static final MoveOption ROCK = new MoveOption("rock", 'r',
			new HashSet<String>() {
				{
					add("paper");
					add("spock");
					add("black hole");
				}
			}, new HashMap<String, String>() {
				{
					put("scissors", "crushes");
					put("lizard", "crushes");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption PAPER = new MoveOption("paper", 'p',
			new HashSet<String>() {
				{
					add("scissors");
					add("lizard");
					add("black hole");
				}
			}, new HashMap<String, String>() {
				{
					put("rock", "covers");
					put("spock", "disproves");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption SCISSORS = new MoveOption("scissors", 's',
			new HashSet<String>() {
				{
					add("rock");
					add("spock");
					add("black hole");
				}
			}, new HashMap<String, String>() {
				{
					put("paper", "cuts");
					put("lizard", "decapitates");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption LIZARD = new MoveOption("lizard", 'l',
			new HashSet<String>() {
				{
					add("rock");
					add("scissors");
					add("black hole");
				}
			}, new HashMap<String, String>() {
				{
					put("spock", "poisons");
					put("paper", "eats");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption SPOCK = new MoveOption("spock", 'k',
			new HashSet<String>() {
				{
					add("paper");
					add("lizard");
					add("black hole");
				}
			}, new HashMap<String, String>() {
				{
					put("scissors", "smashes");
					put("rock", "vaporizes");
				}
			});
	@SuppressWarnings("serial")
	public static final MoveOption BLACK_HOLE = new MoveOption("black hole",
			'h', new HashSet<String>() {
				{
				}
			}, new HashMap<String, String>() {
				{
					put("rock", "eats");
					put("paper", "eats");
					put("scissors", "eats");
					put("lizard", "eats");
					put("spock", "eats");
				}
			});

	@SuppressWarnings("serial")
	public static final Map<String, MoveOption> NAME_TO_MOVE_OPTION = new HashMap<String, MoveOption>() {
		{
			put("rock", ROCK);
			put("paper", PAPER);
			put("scissors", SCISSORS);
			put("lizard", LIZARD);
			put("spock", SPOCK);
			put("black hole", BLACK_HOLE);
		}
	};
}
