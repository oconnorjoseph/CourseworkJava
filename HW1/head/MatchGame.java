package head;

/**
 * @author Joseph O'Connor III
 * 
 * Interface for games that consist of a certain number of matches 
 */
public interface MatchGame {
	
	/**
	 * Startup of the game
	 */
	public void start();

	/**
	 * Body of game that loops over all matches
	 * 
	 * @param maxCountMatches the maximum number of matches that will occur in the game
	 * (if a player quits early, this max is not reached)
	 */
	public void loopFor(int maxCountMatches);

	/**
	 * Termination of the game
	 */
	public void end();
}
