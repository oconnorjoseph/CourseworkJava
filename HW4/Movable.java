/**
 * Something that can be moved
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public interface Movable {
	/**
	 * Prepares or directly has this shifted xChange distance in the positive
	 * x-direction and yChange distance in the positive y-direction
	 * 
	 * @param xChange
	 *            the distance to shift this in the positive x-direction
	 * @param yChange
	 *            the distance to shift this in the positive y-direction
	 */
	public void translate(int xChange, int yChange);
}
