package utilities;


/**
 * MyQueue is a queue where elements are added and removed in order. The queue orders these values as first in first out.
 * @author Nic Kelly
 */
public class MyQueue<E> implements QueueADT{
	private int size;
	private MyArrayList<E> list;
	
	/**
	 * Construct an empty queue
	 */
	public MyQueue() {
		size = 0;
		list = new MyArrayList<E>();
	}
	
	@Override
	public void push(Object e) {
		list.add(size, (E) e);
		size += 1;
	}

	@Override
	public E pop() {
		size -= 1;
		return list.remove(0);
	}		

	@Override
	public E peek() {
		return list.get(0);
	}

	@Override
	public Boolean isEmpty() {
		return (size <= 0);
	}

	@Override
	public int size() {
		return size;
	}
}
