package model;

import java.util.List;

public interface Retrievable<T, N extends Number> {
	public boolean isEmpty();
	
	public T getAt(N index) throws IndexOutOfBoundsException;
	
	public List<T> getAll();
	public List<T> getFrom(N startIndex) throws IndexOutOfBoundsException;
	public List<T> getTo(N endIndex) throws IndexOutOfBoundsException;
	public List<T> getBetween(N startIndex, N endIndex) throws IndexOutOfBoundsException;
}
