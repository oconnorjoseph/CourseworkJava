import java.awt.Graphics2D;

/**
 * A drawable figure in the shape of a simplistic Unicycle that also supports
 * being moved before the next time it is drawn.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Unicycle implements MovingDrawable {

	/**
	 * Creates a new Unicycle instance with its upper-lefthand corner at Point
	 * {@code location} and based around a wheel radius of {@code wheelRadius}
	 * 
	 * @param x
	 *            the x-coordinate of the Point at which the Unicycle's
	 *            upper-lefthand corner will start
	 * @param y
	 *            the y-coordinate of the Point at which the Unicycle's
	 *            upper-lefthand corner will start
	 * @param wheelRadius
	 *            the radius of the wheel (outer circle) the Unicycle will have.
	 *            Note: The size of the rest of the Unicycle is proportionate to
	 *            this {@code wheelRadius}: @ see UnicycleProportions
	 * @param xMax
	 *            the actual x-coordinate after which the next translation will
	 *            make the x-coordinate of the Point for the Unicycle's
	 *            upper-lefthand corner revert to 0 (for wrapping left-right)
	 * @param yMax
	 *            the actual y-coordinate after which the next translation will
	 *            make the y-coordinate of the Point for the Unicycle's
	 *            upper-lefthand corner revert to 0 (for wrapping up-down)
	 * @throws IllegalArgumentException
	 *             if {@code wheelRadius} is negative, or if {@code xMax} is not
	 *             positive, or if {@code yMax} is not positive
	 */
	public Unicycle(int x, int y, int wheelRadius, int xMax, int yMax) {
		Asserter.assertIsNonNegative(wheelRadius, "wheelRadius",
				"the Unicycle constructor");
		Asserter.assertIsPositive(xMax, "xMax", "the Unicycle Constructor");
		Asserter.assertIsPositive(yMax, "yMax", "the Unicycle Constructor");
		this.x = x;
		this.xMax = xMax;
		this.y = y;
		this.yMax = yMax;
		this.wheelRadius = wheelRadius;
		artisan = new UnicycleArtisan(wheelRadius);
	}

	/**
	 * Prepares the Unicycle to be shifted xChange distance in the positive
	 * x-direction (rightward) and yChange distance in the positive y-direction
	 * (downward) for the next time the Unicycle is drawn and performs any
	 * left-right or up-down wrapping if necessary
	 * 
	 * @param xChange
	 *            the distance to shift the Unicycle in the positive x-direction
	 *            (rightward)
	 * @param yChange
	 *            the distance to shift the Unicycle in the positive y-direction
	 *            (downward)
	 */
	public void translate(int xChange, int yChange) {
		x += xChange;
		y += yChange;
		wrapIfNecessary();
	}

	private void wrapIfNecessary() {
		if (x > xMax) {
			x = -2 * wheelRadius;
		} else if (x < (-2 * wheelRadius)) {
			x = xMax;
		}

		if (y > yMax) {
			y = (int) (-(1 + UnicycleArtisan.Proportions.FRAME_LENGTH_MULTIPLIER + UnicycleArtisan.Proportions.SEAT_HEIGHT_MULTIPLIER) * wheelRadius);
		} else if (y < (int) (-(1 + UnicycleArtisan.Proportions.FRAME_LENGTH_MULTIPLIER + UnicycleArtisan.Proportions.SEAT_HEIGHT_MULTIPLIER) * wheelRadius)) {
			y = yMax;
		}
	}

	/**
	 * Draws the Unicycle incorporating all previous shifts by the
	 * {@link #translate} function
	 * 
	 * @param graphics2D
	 *            the Graphics2D object to use for drawing the Unicycle
	 */
	public void draw(Graphics2D graphics2D) {
		artisan.drawAt(x, y, graphics2D);
	}
	
	/**
	 * @return the maximum x-value after which left-right wrapping will occur
	 */
	public int getMaxX() {
		return xMax;
	}
	
	/**
	 * @return the maximum y-value after which up-down wrapping will occur
	 */
	public int getMaxY() {
		return yMax;
	}

	private int x, y, wheelRadius, xMax, yMax;
	private UnicycleArtisan artisan;
}
