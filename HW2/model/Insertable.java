package model;

public interface Insertable<T, N extends Number> {
	public void insert(T newElement, N index);
	public void append(T newElement);
	public void prepend(T newElement);
}
