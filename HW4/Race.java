import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.Timer;

/**
 * Capable of launching an interactable window displaying multiple
 * {@link MovingDrawable}s moving at the same x-velocity determined by the latest user
 * input into the interactable window
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Race {

	/**
	 * Constructs a new Race for launching interactable windows
	 * displaying the given {@code competitors} {@link MovingDrawable}s
	 * 
	 * @param competitors
	 *            the {@link MovingDrawable}s all launches of interactable windows
	 *            will display NOTE: All competitors should
	 *            have the same maxX and maxY for proper left-right wrapping
	 */
	public Race(MovingDrawable... competitors) {
		this.competitors = competitors;
	}

	/**
	 * Builds a new Race for launching interactable windows with
	 * {@code numCompetitors} number of psuedo-randomly-sized and
	 * psuedo-randomly-spaced {@link Unicycle} competitors
	 * 
	 * @param numCompetitors
	 *            number of psuedo-randomly-size and psuedo-randomly-spaced
	 *            competitors
	 * @param normalWheelRadius
	 *            the wheel radius to which all psudeo-randomly generated
	 *            {@link Unicycle} competitors will have a wheel radius of at
	 *            least half the size of and at most twice the size of
	 * @param raceWidth the width of the window for the race to be displayed on
	 * @return
	 */
	public static Race raceRandomUnicycles(int numCompetitors,
			int normalWheelRadius, int raceWidth) {
		Asserter.assertIsNonNegative(numCompetitors, "numCompetitors",
				"Race#raceRandomCompetitors");
		Asserter.assertIsNonNegative(normalWheelRadius, "normalWheelRadius",
				"Race#raceRandomCompetitor");
		Asserter.assertIsNonNegative(raceWidth, "raceWidth",
				"Race#raceRandomCompetitor");
		int maxHeight = (int) (normalWheelRadius
				* UnicycleArtisan.Proportions.TOTAL_HEIGHT_MULTIPLIER * MAX_RANDOM_SIZE_MULTIPLIER);

		MovingDrawable[] competitors = new Unicycle[numCompetitors];
		for (int i = 0; i < numCompetitors; i++) {
			competitors[i] = nextRandomUnicycle(maxHeight, normalWheelRadius,
					raceWidth);
		}
		return new Race(competitors);
	}

	private static Unicycle nextRandomUnicycle(int maxHeight,
			int normalWheelRadius, int raceWidth) {
		int wheelRadius = (int) (normalWheelRadius * nextRandomDouble(
				MIN_RANDOM_SIZE_MULTIPLIER, MAX_RANDOM_SIZE_MULTIPLIER));
		int xStart = (int) nextRandomDouble(0, raceWidth);
		int yStart = (int) (maxHeight - (wheelRadius * UnicycleArtisan.Proportions.TOTAL_HEIGHT_MULTIPLIER));
		return new Unicycle(xStart, yStart, wheelRadius, raceWidth, maxHeight);
	}

	private static double nextRandomDouble(double min, double max) {
		double shift = min;
		double range = max - min;
		return Math.random() * range + shift;
	}

	/**
	 * Launches a window displaying the race of {@link MovingDrawable} competitors
	 * (all moving at the same x-velocity) with a JSlider aligned along the
	 * bottom for user interaction to control the x-velocity of those competitors
	 * 
	 * 
	 * @param initialVelocity
	 *            the initial, or default, x-velocity for all {@link MovingDrawable}
	 *            competitors to move at
	 */
	public void launch(int initialVelocity) {
		JFrame frame = new JFrame();
		JLabel[] competitorLabels = getPreparedCompetitorLabels(frame);
		JSlider velocitySlider = getPreparedVelocitySlider(initialVelocity,
				frame);

		displayFrame(frame);
		prepareVelocityManager(initialVelocity, competitorLabels,
				velocitySlider);
	}

	private JSlider getPreparedVelocitySlider(int initialVelocity, JFrame frame) {
		JSlider velocitySlider = new JSlider(MIN_VELOCITY, MAX_VELOCITY,
				initialVelocity);
		frame.add(velocitySlider);
		return velocitySlider;
	}

	private JLabel[] getPreparedCompetitorLabels(JFrame frame) {
		// NOTE: The following assumes all Unicycle competitors will have the
		// same maxX and maxY, which the should for proper wrapping
		int raceWidth = competitors[0].getMaxX();
		int raceHeight = competitors[0].getMaxY();

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(raceWidth, raceHeight));

		JLabel[] competitorLabels = new JLabel[competitors.length];
		for (int i = 0; i < competitorLabels.length; i++) {
			MovingIcon competitorIcon = new MovingIcon(competitors[i],
					raceWidth, raceHeight);
			competitorLabels[i] = new JLabel(competitorIcon);
			competitorLabels[i].setBounds(0, 0, raceWidth, raceHeight);
			layeredPane.add(competitorLabels[i]);
		}
		frame.add(layeredPane);

		return competitorLabels;
	}

	private void displayFrame(JFrame frame) {
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void prepareVelocityManager(int defaultVelocity,
			JLabel[] competitorLabels, JSlider velocitySlider) {
		VelocityManager velocityManager = new VelocityManager(defaultVelocity,
				competitors, competitorLabels);
		velocitySlider.addChangeListener(velocityManager);
		Timer refreshTimer = new Timer(REFRESH_DELAY, velocityManager);
		refreshTimer.start();
	}

	private MovingDrawable[] competitors;

	private static final int REFRESH_DELAY = 30, MAX_VELOCITY = 30,
			MIN_VELOCITY = -MAX_VELOCITY;
	private static final double MIN_RANDOM_SIZE_MULTIPLIER = 0.5,
			MAX_RANDOM_SIZE_MULTIPLIER = 2;
}
