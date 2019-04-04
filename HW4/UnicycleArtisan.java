import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Draws a Unicycle based around the to-be-drawn Unicycle's wheel radius given
 * at construction as well as the constant {@link Proportions} set for all
 * Unicycles
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class UnicycleArtisan {

	/**
	 * Constructs a new UnicycleArtisan for drawing Unicycles with a wheel
	 * radius of {@code wheelRadius} and the proper {@link Proportions}
	 * 
	 * @param wheelRadius
	 *            the radius of the wheel (outer circle) the Unicycle will have.
	 *            Note: The size of the rest of the Unicycle is proportionate to
	 *            this {@code wheelRadius}: @ see UnicycleProportions
	 */
	public UnicycleArtisan(int wheelRadius) {
		this.wheelRadius = wheelRadius;
		setDimensions();
	}

	private void setDimensions() {
		seatHeight = (int) (wheelRadius * Proportions.SEAT_HEIGHT_MULTIPLIER);
		seatWidth = (int) (wheelRadius * Proportions.SEAT_WIDTH_MULTIPLIER);
		frameLength = (int) (wheelRadius * Proportions.FRAME_LENGTH_MULTIPLIER);
		innerWheelRadius = (int) (wheelRadius * Proportions.INNER_WHEEL_RADIUS_MULTIPLIER);
	}

	/**
	 * Draws a Unicycle with its upper-lefthand corner at (x,y)
	 * 
	 * @param x
	 *            the x-coordinate of the Point of the upper-lefthand corner of
	 *            the Unicycle to be drawn
	 * @param y
	 *            the y-coordinate of the Point of the upper-lefthand corner of
	 *            the Unicycle to be drawn
	 * @param graphics2D
	 *            the Graphics2D object to use for drawing the Unicycle
	 */
	public void drawAt(int x, int y, Graphics2D graphics2D) {
		int centerX = x + wheelRadius;

		Rectangle2D seat = makeSeat(x, y);
		Line2D frame = makeFrame(centerX, y);
		Ellipse2D wheel = makeWheel(x, y);
		Ellipse2D innerWheel = makeInnerWheel(centerX, y);
		GeneralPath unicycle = aggegrateUnicycle(seat, frame, wheel, innerWheel);

		graphics2D.fill(seat);
		graphics2D.draw(unicycle);
	}

	private GeneralPath aggegrateUnicycle(Rectangle2D seat, Line2D frame,
			Ellipse2D wheel, Ellipse2D innerWheel) {
		GeneralPath unicycle = new GeneralPath();
		unicycle.append(seat, false);
		unicycle.append(frame, false);
		unicycle.append(wheel, false);
		unicycle.append(innerWheel, false);
		return unicycle;
	}

	private Ellipse2D makeWheel(int x, int y) {
		double wheelUpperLeftX = x;
		double wheelUpperLeftY = y + seatHeight + frameLength - wheelRadius;
		return new Ellipse2D.Double(wheelUpperLeftX, wheelUpperLeftY,
				wheelRadius * 2, wheelRadius * 2);
	}

	private Ellipse2D makeInnerWheel(int centerX, int y) {
		double innerWheelUpperLeftX = centerX - innerWheelRadius;
		double innerWheelUpperLeftY = y + seatHeight + frameLength
				- innerWheelRadius;
		return new Ellipse2D.Double(innerWheelUpperLeftX, innerWheelUpperLeftY,
				innerWheelRadius * 2, innerWheelRadius * 2);
	}

	private Line2D makeFrame(int centerX, int y) {
		double frameUpperX = centerX;
		double frameUpperY = y + seatHeight;
		double frameLowerX = centerX;
		double frameLowerY = frameUpperY + frameLength;
		return new Line2D.Double(frameUpperX, frameUpperY, frameLowerX,
				frameLowerY);
	}

	private Rectangle2D makeSeat(int x, int y) {
		double seatUpperLeftX = x + (seatWidth / 2);
		double seatUpperLeftY = y;
		return new Rectangle2D.Double(seatUpperLeftX, seatUpperLeftY,
				seatWidth, seatHeight);
	}

	private int wheelRadius;

	// The following fields are used in anticipatory evaluation to save
	// computation when drawing
	private int seatHeight, seatWidth, frameLength, innerWheelRadius;

	/**
	 * Info holder class containing the constant proportions used to draw
	 * {@link Unicycle}
	 * 
	 * @author Joseph O'Connor (jgo2115)
	 * 
	 */
	public class Proportions {
		/**
		 * Height of the seat of the Unicycle relative to the Unicycle's wheel
		 * (outer circle) radius. Observe that the Unicycle's seat height will
		 * be 0.5 times, or half, the Unicycle's wheel radius.
		 */
		public static final double SEAT_HEIGHT_MULTIPLIER = 0.5;
		/**
		 * Width of the seat of the Unicycle relative to the Unicycle's wheel
		 * (outer circle) radius. Observe that the Unicycle's seat width will be
		 * 1 times, or equal to, the Unicycle's wheel radius.
		 */
		public static final double SEAT_WIDTH_MULTIPLIER = 1;
		/**
		 * Length of the frame (line from seat to center of the Unicycle's
		 * wheel) relative to the Unicycle's wheel (outer circle) radius.
		 * Observe that the Unicycle's frame length will be 2 times, or twice,
		 * the Unicycle's wheel radius.
		 */
		public static final double FRAME_LENGTH_MULTIPLIER = 2;
		/**
		 * Radius of the inner circle of the Unicycle's wheel relative to the
		 * radius of the outer circle of the Unicycle's wheel. Observe that the
		 * Unicycle's wheel's inner circle will be 0.5, or half, the Unicycle's
		 * wheel radius.
		 */
		public static final double INNER_WHEEL_RADIUS_MULTIPLIER = 0.5;
		/**
		 * Height of the entire Unicycle (from bottom of Unicycle's wheel to top
		 * of Unicycle's seat) relative to the Unicycle's wheel (outer circle)
		 * radius.
		 */
		public static final double TOTAL_HEIGHT_MULTIPLIER = 1
				+ FRAME_LENGTH_MULTIPLIER + SEAT_HEIGHT_MULTIPLIER;
	}
}
