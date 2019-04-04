
/**
 * Encapsulates the magnitude and direction of something's movement
 * 
 * @author Joseph O'Connor (jgo2115)
 *
 */
public class Velocity {
	
	/**
	 * @param magnitude The speed of this velocity
	 * @param direction The {@link Direction} at which this velocity points
	 */
	public Velocity(float magnitude, Direction direction) {
		eastComponent = direction.calcEastComponentOf(magnitude);
		northComponent = direction.calcNorthComponentOf(magnitude);
	}
	
	/**
	 * 
	 * @return The component of this velocity in the eastward direction. If negative, then this points westward.
	 */
	public float getEastComponent() {
		return eastComponent;
	}

	/**
	 * 
	 * @return The component of this velocity in the northward direction. If negative, then this points southward.
	 */
	public float getNorthComponent() {
		return northComponent;
	}

	private float eastComponent, northComponent;
}
