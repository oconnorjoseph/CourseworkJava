package model;

public class FieldCodePair<T> {

	public FieldCodePair(int inCount, T inElement) {
		count = inCount;
		element = inElement;
	}

	public FieldCodePair(T inElement) {
		this(0, inElement);
	}

	public int getCount() {
		return count;
	}

	public int setCountTo(int newCount) {
		assertIsValid(count);
		return count = newCount;
	}

	private void assertIsValid(int count) {
		if (count < 0) {
			throw new IndexOutOfBoundsException(
					"A FieldCode instance cannot have a count less than 0");
		}
	}

	public void incrementCount() {
		count++;
	}

	public void decrementCount() {
		count--;
	}

	public T getElement() {
		return element;
	}

	public void setElementTo(T newElement) {
		element = newElement;
	}

	private int count;
	private T element;
}
