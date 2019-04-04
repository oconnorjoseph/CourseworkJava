import java.util.ArrayList;
import java.util.List;

/**
 * {@link Runnable} that can hold observer objects
 * 
 * @author Joseph O'Connor (jgo2115)
 * 
 * @param <T>
 *            the type of the observers
 */
public abstract class Activator<T> implements Runnable {

	/**
	 * Constructs a new Activator with the given array of observer objects of
	 * generic type T
	 * 
	 * @param inObservers
	 *            objects of generic type T to be treated as observers
	 */
	public Activator(T... inObservers) {
		for (T observer : inObservers) {
			register(observer);
		}
	}

	/**
	 * Adds an observer object of generic type T to the internal list of
	 * observers
	 * 
	 * @param observer
	 *            the observer of generic type T to be added to the internal
	 *            list of observers
	 */
	public void register(T observer) {
		observers.add(observer);
	}

	/**
	 * Removes an observer object of generic type T to the internal list of
	 * observers only if that observer object is actually in the internal list
	 * of observers
	 * 
	 * @param observer
	 *            the observer of generic type T to be removed from the internal
	 *            list of observers
	 */
	public void unregister(T observer) {
		observers.remove(observer);
	}

	/**
	 * Internal list of observer objects of generic type T
	 */
	protected List<T> observers = new ArrayList<T>();
}
