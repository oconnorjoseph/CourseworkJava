package func;

import java.util.ArrayList;
import java.util.List;

import model.FieldCodePair;

public class RLEConverter<T> {

	public static <T> List<T> toAPI(List<FieldCodePair<T>> fieldCodePairs) {
		List<T> elements = new ArrayList<T>();
		for (FieldCodePair<T> fieldCodePair : fieldCodePairs) {
			allocateElements(fieldCodePair, elements);
		}
		return elements;
	}
	
	private static <T> void allocateElements(FieldCodePair<T> fieldCodePair, List<T> elements){
		for (int i = 0; i < fieldCodePair.getCount(); i++) {
			elements.add(fieldCodePair.getElement());
		}
	}
	
	public static <T> List<T> toAPI(FieldCodePair<T>... fieldCodePairs) {
		// Does not use other #toAPI method to avoid computational inefficiency of Array#asList
		List<T> elements = new ArrayList<T>();
		for (FieldCodePair<T> fieldCodePair : fieldCodePairs) {
			allocateElements(fieldCodePair, elements);
		}
		return elements;
	}
	
	public static <T> List<FieldCodePair<T>> toSpace(List<T> elements) {
		List<FieldCodePair<T>> fieldCodePairs = new ArrayList<FieldCodePair<T>>();
		if (elements.isEmpty()) {
			return fieldCodePairs;
		}
		FieldCodePair<T> currentFieldCodePair = allocateFieldCodePair(elements.get(0), fieldCodePairs);
		for (int i = 1; i < elements.size(); i++) {
			if(elements.get(i).equals(currentFieldCodePair.getElement())) {
				currentFieldCodePair.incrementCount();
			} else {
				currentFieldCodePair = allocateFieldCodePair(elements.get(0), fieldCodePairs);
			}
		}
		return fieldCodePairs;
	}
	
	private static <T> FieldCodePair<T> allocateFieldCodePair(T element, List<FieldCodePair<T>> trackingFieldCodePairs) {
		FieldCodePair<T> tempFieldCodePair = new FieldCodePair<T>(1, element);
		trackingFieldCodePairs.add(tempFieldCodePair);
		return tempFieldCodePair;
	}
	
	public static <T> List<FieldCodePair<T>> toSpace(T... elements) {
		// Does not use other #toSpace method to avoid computational inefficiency of Array#asList
		List<FieldCodePair<T>> fieldCodePairs = new ArrayList<FieldCodePair<T>>();
		if (elements.length == 0) {
			return fieldCodePairs;
		}
		FieldCodePair<T> currentFieldCodePair = allocateFieldCodePair(elements[0], fieldCodePairs);
		for (int i = 1; i < elements.length; i++) {
			if(elements[i].equals(currentFieldCodePair.getElement())) {
				currentFieldCodePair.incrementCount();
			} else {
				currentFieldCodePair = allocateFieldCodePair(elements[0], fieldCodePairs);
			}
		}
		return fieldCodePairs;
	}
}
