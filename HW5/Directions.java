import java.util.HashMap;
import java.util.Map;

/**
 * Contains several cardinal and inter-cardinal {@link Direction}s as well as
 * functionality for getting a {@link Direction} by its name
 * 
 * @author Joseph O'Connor
 * 
 */
public class Directions {

	/**
	 * Gets the cardinal or inter-cardinal with the given name
	 * 
	 * @param directionName
	 *            the name of the direction
	 * @return The direction with the given name
	 */
	public static Direction getDirection(String directionName) {
		directionName = directionName.trim().toLowerCase();
		Direction foundDirection = NAME_TO_DIRECTION_MAP.get(directionName);
		return foundDirection;
	}

	private static final float CARDINAL_MULTIPLIER = 1.0f,
			INTERCARDINAL_MULTIPLIER = (float) Math.sqrt(2);

	/**
	 * Several preset cardinal and inter-cardinal directions
	 */
	public static final Direction NORTH = new Direction(0, -CARDINAL_MULTIPLIER),
			NORTHEAST = new Direction(INTERCARDINAL_MULTIPLIER,
					-INTERCARDINAL_MULTIPLIER),
			EAST = new Direction(CARDINAL_MULTIPLIER, 0),
			SOUTHEAST = new Direction(INTERCARDINAL_MULTIPLIER,
					INTERCARDINAL_MULTIPLIER),
			SOUTH = new Direction(0, CARDINAL_MULTIPLIER),
			SOUTHWEST = new Direction(-INTERCARDINAL_MULTIPLIER,
					INTERCARDINAL_MULTIPLIER),
			WEST = new Direction(-CARDINAL_MULTIPLIER, 0),
			NORTHWEST = new Direction(-INTERCARDINAL_MULTIPLIER,
					-INTERCARDINAL_MULTIPLIER),
			STATIONARY = new Direction(0, 0);

	@SuppressWarnings("serial")
	private static final Map<String, Direction> NAME_TO_DIRECTION_MAP = new HashMap<String, Direction>() {
		{
			put("north", NORTH);
			put("northeast", NORTHEAST);
			put("east", EAST);
			put("southeast", SOUTHEAST);
			put("south", SOUTH);
			put("southwest", SOUTHWEST);
			put("west", WEST);
			put("northwest", NORTHWEST);
			put("stationary", STATIONARY);
			put("n", NORTH);
			put("ne", NORTHEAST);
			put("e", EAST);
			put("se", SOUTHEAST);
			put("s", SOUTH);
			put("sw", SOUTHWEST);
			put("w", WEST);
			put("nw", NORTHWEST);
			put("st", STATIONARY);
		}
	};
}
