import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * Runs an internal list of {@link Runnable}s periodically
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class ClockTicker {

	/**
	 * Constructs a new ClockTicker to run its internal list of {@link Runnable}
	 * s every given number of milliseconds
	 * 
	 * @param tickLengthMillis
	 *            The length of time between which all of the runnables are
	 *            called. All of the runnables are called at once (but one after
	 *            another on the same thread)
	 * @param inActions
	 *            {@link Runnable} objects to add to the internal list of
	 *            {@link Runnable}s
	 */
	public ClockTicker(int tickLengthMillis, Runnable... inActions) {
		for (Runnable action : inActions) {
			subscribe(action);
		}
		clock = new Timer(tickLengthMillis, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Runnable action : actions) {
					action.run();
				}
			}
		});
	}

	/**
	 * Stops running the internal list of {@link Runnable} objects for a given
	 * period of time, and executes two {@link Runnable}s: one at the beginning
	 * of the interruption and one at the end of the interruption
	 * 
	 * @param beforeInterruption
	 *            Runnable to run at the beginning of the interruption
	 * @param afterInterruption
	 *            Runnable to run at the end of the interruption
	 * @param interruptTimeMillis
	 *            How long to interrupt for in milliseconds
	 */
	public void interrupt(Runnable beforeInterruption,
			Runnable afterInterruption, int interruptTimeMillis) {
		stop();
		beforeInterruption.run();
		startAfter(interruptTimeMillis, afterInterruption);
	}

	private void startAfter(int interruptTimeMillis,
			final Runnable afterInterruption) {
		final Timer delay = new Timer(interruptTimeMillis,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						afterInterruption.run();
						start();
						((Timer) e.getSource()).stop();
					}
				});
		delay.start();
	}

	/**
	 * 
	 * @param action The {@link Runnable} to add to the internal list of {@link Runnable}s
	 */
	public void subscribe(Runnable action) {
		actions.add(action);
	}

	/**
	 * Starts executing the internal list of {@link Runnable}s periodically
	 */
	public void start() {
		clock.start();
	}

	/**
	 * Stops execution of the internal list of {@link Runnable}s
	 */
	public void stop() {
		clock.stop();
	}

	private List<Runnable> actions = new ArrayList<Runnable>();
	private Timer clock;
}
