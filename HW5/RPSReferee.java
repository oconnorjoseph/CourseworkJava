import java.awt.Point;
import java.awt.Rectangle;

/**
 * Manages collisions between {@link StringFigure}s according to the rules of
 * RPSLKH.
 * 
 * @author Joseph O'Connor
 * 
 */
public class RPSReferee extends Activator<StringFigure> {

	public RPSReferee(Playground playground, Mover mover, Artisan artisan,
			StringFigure... figures) {
		super(figures);
		this.mover = mover;
		this.artisan = artisan;
		this.playground = playground;
	}

	public void run() {
		for (int i = 0; i < observers.size(); i++) {
			StringFigure collidingFigure = getFirstCollider(i);
			if (collidingFigure != null) {
				StringFigure currentFigure = observers.get(i);
				eliminateLoser(currentFigure, collidingFigure);
				run();
				return;
			}
		}
	}

	private void eliminateLoser(StringFigure currentFigure,
			StringFigure collidingFigure) {
		Match match = new Match(currentFigure, collidingFigure);
		displayMessage(match);
		StringFigure loosingFigure = match.getLoosingFigure();
		eliminate(loosingFigure);
	}

	private void displayMessage(Match match) {
		String matchOutcome = match.outcomeAsString();
		StringFigure winningFigure = match.getWinningFigure();
		Point collisionLocation = winningFigure.getCurrentLocation();
		System.out.println(collisionLocation);
		playground.displayMessage(matchOutcome, collisionLocation,
				MATCH_MESSAGE_DISPLAY_MILLIS);
	}

	private StringFigure getFirstCollider(int index) {
		Rectangle currentBoundary = getBoundaryForObserver(index);
		for (int i = (index + 1); i < observers.size(); i++) {
			StringFigure otherFigure = observers.get(i);
			Rectangle otherBoundary = otherFigure.getBoundary();
			if (currentBoundary.intersects(otherBoundary)) {
				return otherFigure;
			}
		}
		return null;
	}

	private void eliminate(StringFigure collidingFigure) {
		mover.unregister(collidingFigure);
		artisan.unregister(collidingFigure);
		unregister(collidingFigure);
	}

	private Rectangle getBoundaryForObserver(int index) {
		StringFigure figure = observers.get(index);
		return figure.getBoundary();
	}

	private Mover mover;
	private Artisan artisan;
	private Playground playground;

	private static final int MATCH_MESSAGE_DISPLAY_MILLIS = 2500;

}
