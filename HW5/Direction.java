/**
 * Contains information regarding cardinal direction-ality
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Direction {

	/**
	 * Constructs a Direction based on how much it is in the east direction and
	 * north direction
	 * 
	 * @param eastMultiplier
	 *            how much of this direction is in the eastward direction
	 * @param northMultiplier
	 *            how much of this direction is in the northward direction
	 */
	public Direction(float eastMultiplier, float northMultiplier) {
		this.eastMultiplier = eastMultiplier;
		this.northMultiplier = northMultiplier;
	}

	/**
	 * Get the eastward component of a magnitude in this direction
	 * 
	 * @param magnitude
	 *            The magnitude for which to calculate an eastward component
	 * @return The eastward component of the magnitude in this direction
	 */
	public float calcEastComponentOf(float magnitude) {
		return magnitude * eastMultiplier;
	}

	/**
	 * Get the northward component of a magnitude in this direction
	 * 
	 * @param magnitude
	 *            The magnitude for which to calculate an northward component
	 * @return The northward component of the magnitude in this direction
	 */
	public float calcNorthComponentOf(float magnitude) {
		return magnitude * northMultiplier;
	}

	private float eastMultiplier, northMultiplier;
}