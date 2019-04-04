import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Launches and executes several test cases of the Assignment 4 application for
 * easy use by a Runner class
 * 
 * NOTE: Private test case methods (e.g. {@link l} are not included in the
 * corresponding UML for brevity sake.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class Tester {

	/**
	 * Launches and executes all test cases for the Assignment 4 application
	 */
	public static void test() {
		// Step 1
		testSingleUnicycles();
		// Step 2
		testUnicycleRaces();
		// Step 3
		testUnicyclePackRaces();
	}

	private static void testUnicyclePackRaces() {
		testOnePackRace();
		testMultiplePacksRace();
		testMixedPacksRace();
		testSubPacksRace();
	}

	private static void testSubPacksRace() {
		Pack pack1 = makePack(2, -1);
		Pack pack2 = makePack(4, 2.356);
		MovingDrawable pack3 = makePack(3, 2);
		MovingDrawable loner = new Unicycle(100, 0, WHEEL_RADIUS, FRAME_WIDTH, FRAME_HEIGHT);
		pack2.add(pack1);
		pack1.add(loner);
		
		Race race = new Race(pack2, pack3);
		race.launch(3);
	}

	private static void testMixedPacksRace() {
		MovingDrawable pack1 = makePack(1, -1);
		MovingDrawable pack2 = makePack(2, 1.333);
		MovingDrawable pack3 = makePack(3, 2);
		MovingDrawable pack4 = makePack(4, 2.356);
		MovingDrawable loner = new Unicycle(100, 0, WHEEL_RADIUS, FRAME_WIDTH, FRAME_HEIGHT);
		
		Race race = new Race(pack1, pack2, pack3, loner, pack4);
		race.launch(3);
	}

	private static void testMultiplePacksRace() {
		MovingDrawable pack1 = makePack(2, 0);
		MovingDrawable pack2 = makePack(3, 0.5);
		MovingDrawable pack3 = makePack(4, 1);
		MovingDrawable pack4 = makePack(5, 2);
		Race race = new Race(pack1, pack2, pack3, pack4);
		race.launch(2);
	}

	private static void testOnePackRace() {
		MovingDrawable pack = makePack(3, 0.5);
		Race race = new Race(pack);
		race.launch(2);
	}

	private static Pack makePack(int num, double velocityMultiplier) {
		Pack unicyclePack = new UnicyclePack(velocityMultiplier);
		int xStart = 0;
		double sizeMultiplier = 1;
		for (int i = 0; i < num; i++) {
			int yStart = (int) (WHEEL_RADIUS * (1 - sizeMultiplier) * 3.5);
			unicyclePack.add(new Unicycle(xStart, yStart, (int) (WHEEL_RADIUS * sizeMultiplier), FRAME_WIDTH, FRAME_HEIGHT));
			xStart += WHEEL_RADIUS;
			sizeMultiplier *= 0.8;
		}
		return unicyclePack;
	}

	private static void testSingleUnicycles() {
		testSingleUnicyclesLeftRight();
		testSingleUnicyclesUpDown();
		testSingleUnicyclesDiagonal();
	}

	private static void testUnicycleRaces() {
		testUnicycleRace(3, 1);
		testUnicycleRace(4, 0);
		testUnicycleRace(5, -1);
		testUnicycleRace(6, 30);
		testUnicycleRace(7, -23);
	}

	private static void testUnicycleRace(int numCompetitors, int initialVelocity) {
		Race randomRace = Race.raceRandomUnicycles(numCompetitors,
				WHEEL_RADIUS, FRAME_WIDTH);
		randomRace.launch(initialVelocity);
	}

	private static void testSingleUnicyclesDiagonal() {
		System.out
				.println("Launching new JFrame containing a diagonally moving unicycle that wraps up to down and left to right in the frame...");
		launchUnicycle(0, 0, 5, -5);
		System.out
				.println("Launching another new JFrame containing a single, fast diagonally moving unicycle that wraps up to down and left to right in the frame...");
		launchUnicycle(FRAME_WIDTH, FRAME_HEIGHT, -3, 10);
		System.out
				.println("Launching yet another new JFrame containing a single diagonally moving unicycle that wraps up to down and left to right in the frame...");
		launchUnicycle(19, 17, 1, -2);
	}

	private static void testSingleUnicyclesUpDown() {
		System.out
				.println("Launching new JFrame containing a single downward moving unicycle that wraps up to down in the frame...");
		launchUnicycle(0, 0, 0, 1);
		System.out
				.println("Launching new JFrame containing a single, fast downward moving unicycle that wraps up to down in the frame...");
		launchUnicycle(50, -10, 0, 10);
		System.out
				.println("Launching new JFrame containing a single upward moving unicycle that wraps up to down in the frame...");
		launchUnicycle(50, FRAME_HEIGHT + 10, 0, -1);
		System.out
				.println("Launching new JFrame containing a single, fast upward moving unicycle that wraps up to down in the frame...");
		launchUnicycle(0, 19, 0, -10);
	}

	private static void testSingleUnicyclesLeftRight() {
		System.out
				.println("Launching new JFrame containing a single rightward moving unicycle that wraps right to left in the frame...");
		launchUnicycle(0, 0, 1, 0);
		System.out
				.println("Launching new JFrame containing a single, fast rightward moving unicycle that wraps right to left in the frame...");
		launchUnicycle(-10, 20, 10, 0);
		System.out
				.println("Launching new JFrame containing a single leftward moving unicycle that wraps right to left in the frame...");
		launchUnicycle(FRAME_WIDTH + 10, 20, -1, 0);
		System.out
				.println("Launching new JFrame containing a single, fast leftward moving unicycle that wraps right to left in the frame...");
		launchUnicycle(17, 0, -10, 0);
	}

	private static void launchUnicycle(int x, int y, final int xVelocity,
			final int yVelocity) {
		JFrame frame = new JFrame();

		final MovingDrawable unicycle = new Unicycle(x, y, WHEEL_RADIUS,
				FRAME_WIDTH, FRAME_HEIGHT);
		final MovingIcon unicycleIcon = new MovingIcon(unicycle, FRAME_WIDTH,
				FRAME_HEIGHT);
		final JLabel unicycleLabel = new JLabel(unicycleIcon);
		frame.add(unicycleLabel);

		frame.setLayout(new FlowLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		Timer refreshTimer = new Timer(REFRESH_DELAY, new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				unicycle.translate(xVelocity, yVelocity);
				unicycleLabel.repaint();
			}
		});
		refreshTimer.start();
	}

	private static final int WHEEL_RADIUS = 40;
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 150;

	private static final int REFRESH_DELAY = 30;
}
