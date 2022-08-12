package utilities;

/**
 * MyArrayList is an ADT of an array list
 * @author Nic Kelly
 *
 */
public class MyArrayList<E> implements ListADT<E> {
	private Object[] items;
	private int size;

	/**
	 * Construct a new array list, set the size to 0 and allocate space in the array
	 */
	public MyArrayList() {
		size = 0;
		items = (E[]) new Object[size];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		size = 0;
		items = (E[]) new Object[size];
		items[0] = null;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {

		if (checkIndex(index)) {
			throw new IndexOutOfBoundsException();
		}

		if (toAdd == null) {
			throw new NullPointerException();
		}

		E[] temp = (E[]) items;
		items = (E[]) new Object[size + 1];

		for (int i = 0; i < index; i++) {
			items[i] = temp[i];
		}

		items[index] = toAdd;

		for (int i = index + 1; i < size+1; i++) {
			items[i] = temp[i - 1];
		}

		size += 1;
		
		return true;
	}

	/**
	 * Check index to see if it is within reason of the current state of the list.
	 * @param index to check
	 * @return true if index is within bounds
	 */
	private boolean checkIndex(int index) {
		return !(index <= size && index >= 0);
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		E[] temp = (E[]) items;
		items = (E[]) new Object[size + 1];

		for (int i = 0; i < size; i++) {
			items[i] = temp[i];
		}

		items[size] = toAdd;

		size += 1;

		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		E[] temp = (E[]) items;
		int sizeTemp = size;
		size = size + toAdd.size();
		items = (E[]) new Object[size];
		for (int i = 0; i < sizeTemp; i++) {
			items[i] = temp[i];
		}

		for (int i = sizeTemp; i < size; i++) {
			items[i] = toAdd.toArray()[i];
		}

		return false;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		
		if(!(index < size && index >= 0)) {
			return null;
		}
		return (E) items[index];
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (checkIndex(index)) return null;
		
		Object returnObject = items[index];
		
		E[] temp = (E[]) items;
		items = (E[]) new Object[size - 1];
		
		
		for (int i = 0; i < index; i++) {
			items[i] = temp[i];
		}
		
		for (int i = index+1; i < size; i++) {
			items[i-1] = temp[i];
		}

		size -= 1;

		return (E) returnObject;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {

		Object temp = items[index];

		items[index] = toChange;

		return (E) temp;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		Boolean returnState = false;

		for (Object object : items) {
			if (object == (Object) toFind) {
				returnState = true;
			}
		}

		return returnState;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		return (E[]) items;
	}

	@Override
	public Object[] toArray() {
		return items;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator<E>((E[]) items, size);
	}

}
