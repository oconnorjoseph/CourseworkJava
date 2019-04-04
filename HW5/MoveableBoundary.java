import java.awt.Point;
import java.awt.Rectangle;

/**
 * A rectangular boundary that can be moved around at a given velocity and
 * located as outlined by {@link Moveable}
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class MoveableBoundary implements Moveable {

	/**
	 * Constructs a MoveableBoundary of a size and location given by a
	 * {@link Rectangle} object and with a given velocity
	 * 
	 * @param rectangle
	 *            The {@link Rectangle} defining the boundary's size and
	 *            location
	 * @param velocity
	 *            The {@link Velocity} for how fast and in which direction this
	 *            should move
	 */
	public MoveableBoundary(Rectangle rectangle, Velocity velocity) {
		this.rectangle = rectangle;
		this.velocity = velocity;
		correctLocation(rectangle);
	}

	/**
	 * 
	 * @return The Rectangle representing this's boundary
	 */
	public Rectangle getBoundary() {
		Rectangle rectangle = new Rectangle(this.rectangle);
		correctLocation(rectangle);
		return rectangle;
	}

	/**
	 * @return The {@link Point} giving the northwest corner of this boundary
	 */
	public Point getCurrentLocation() {
		return rectangle.getLocation();
	}

	/**
	 * Moves this to the location of the given {@link Point}
	 * 
	 * @param Point
	 *            the location to which to move this
	 */
	public void setLocation(Point location) {
		rectangle.setLocation(location);
		correctLocation(rectangle);
	}

	private void correctLocation(Rectangle rectangle) {
		rectangle.translate(0, -rectangle.height);
	}

	/**
	 * Translates this at a distance of ({@link Velocity} * 1) pixels in the
	 * direction of the {@link Velocity}
	 * 
	 * @param wrapWidth
	 *            the width of the window on which this is moving; the maximum
	 *            x-value for this to be located at
	 * @param wrapHeight
	 *            the height of the window on which this is moving; the maximum
	 *            y-value for this to be located at
	 */
	public void move(int wrapWidth, int wrapHeight) {
		moveBeforeWrapping();
		handleWrapping(wrapWidth, wrapHeight);
	}

	private void handleWrapping(int wrapWidth, int wrapHeight) {
		if (rectangle.x + rectangle.width < 0) {
			rectangle.x = wrapWidth;
		} else if (rectangle.x > wrapWidth) {
			rectangle.x = 0;
		}
		if (rectangle.y < 0) {
			rectangle.y = wrapHeight;
		} else if (rectangle.y - rectangle.height > wrapHeight) {
			rectangle.y = 0;
		}
	}

	private void moveBeforeWrapping() {
		float toMoveEast = eastRemainder + velocity.getEastComponent();
		float toMoveNorth = northRemainder + velocity.getNorthComponent();
		rectangle.translate((int) toMoveEast, (int) toMoveNorth);
		eastRemainder = toMoveEast % 1;
		northRemainder = toMoveNorth % 1;
	}

	private Rectangle rectangle;
	private Velocity velocity;

	private float eastRemainder, northRemainder;
}
