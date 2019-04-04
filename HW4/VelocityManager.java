import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Responsible for moving an array of {@link MovingDrawable}s at an x-velocity
 * subject to change according to a JSlider; listens to changes in the value of
 * a JSlider and then translates an array of {@link MovingDrawables}s along the
 * x-axis according to the last value of the JSlider.
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 */
public class VelocityManager implements ChangeListener, ActionListener {

	/**
	 * Constructs a new VelocityManager with a default x-velocity of
	 * {@code initialVelocity} to use for translating each
	 * {@link MovingDrawable} in the array of MovingDrawable
	 * {@code movingDrawables} along the x-axis
	 * 
	 * @param initialVelocity
	 *            the default, or initial, x-velocity for the given array of
	 *            MovingDrawables to move at
	 * @param movingDrawables
	 *            the array of MovingDrawables to translate at the x-velocity
	 * @param labels
	 *            the array of JLabels corresponding to the array of
	 *            MovingDrawables
	 */
	public VelocityManager(int initialVelocity,
			MovingDrawable[] movingDrawables, JLabel[] labels) {
		velocity = initialVelocity;
		this.movingDrawables = movingDrawables;
		this.labels = labels;
	}

	/**
	 * Translates each of the {@link MovingDrawable}s in the array of
	 * MovingDrawables s provided at construction along the x-axis at the last
	 * cached x-velocity
	 * 
	 * @param e
	 *            IGNORED
	 */
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < movingDrawables.length; i++) {
			movingDrawables[i].translate(velocity, 0);
			labels[i].repaint();
		}
	}

	/**
	 * Updates the cached x-velocity at which each of the array of
	 * {@link Unicycle}s should be translated next time
	 * {@link #actionPerformed(ActionEvent)} is called
	 * 
	 * @param e
	 *            IGNORED
	 */
	public void stateChanged(ChangeEvent e) {
		velocity = ((JSlider) e.getSource()).getValue();
	}

	private int velocity;
	private MovingDrawable[] movingDrawables;
	private JLabel[] labels;

}
