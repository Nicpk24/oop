package utilities;

import java.util.NoSuchElementException;

/**
 * MyArrayListIterator is an iterator that is used by the linked list as well as the array list
 * @author Nic Kelly
 */
public class MyArrayListIterator<E> implements Iterator<E> {
	private int size;
	private int index;
	private E[] items;
	
	/**
	 * Create an iterator for a list of items that has a known size
	 * @param items to iterate over
	 * @param size of items
	 */
	public MyArrayListIterator(E[] items, int size) {
		this.items = items;
		this.size = size;
		this.index = 0;
	}
	
	@Override
	public boolean hasNext() {
		return (index < size);
	}

	@Override
	public E next() throws NoSuchElementException {
		if (!(hasNext())) {
			throw new NoSuchElementException();
		}
		index ++;
		return items[index-1];
	}

}
