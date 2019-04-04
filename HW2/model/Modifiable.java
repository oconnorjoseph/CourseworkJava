package model;

public interface Modifiable<T, N extends Number> extends Insertable<T, N>, Removable<T, N> {
	
	public void replaceAt(N index, T newElement) throws IndexOutOfBoundsException;
	public void replaceFirst(T oldElement, T newElement) throws IllegalArgumentException;
	public void replaceLast(T oldElement, T newElement) throws IllegalArgumentException;
	public void replaceAll(T oldElement, T newElement) throws IllegalArgumentException;
}
