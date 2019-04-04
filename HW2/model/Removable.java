package model;

public interface Removable<T, N extends Number> {
	public void remove(N index);
	public void removeFirst(T element);
	public void removeLast(T element);
	public void removeAll(T element);
}
