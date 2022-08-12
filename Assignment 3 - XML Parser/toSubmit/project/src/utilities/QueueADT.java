package utilities;


import exceptions.NoSuchElementException;

/**
 * QueueADT is an interface that acts as a contract for a class to represent a queue.
 * @author Nic Kelly
 */
public interface QueueADT<E> {
	/**
	 * Adds an element to the end of the queue.
	 * 
	 * Pre condition: an element send from the caller.
	 * Post condition: the element is added to the queue.
	 * 
	 * @param toAdd the element to add to the queue.
	 */
	public void push(E toAdd);

	/**
	 * Remove an element from the beginning of the queue and return it.
	 * 
	 * Pre condition: the queue has at lest one element.
	 * Post condition: the queue has the first value removed and it is returned.
	 * 
	 * @return The element at the beginning of the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public E pop() throws NoSuchElementException;

	/**
	 * Return the next element in the queue without removing it.
	 * 
	 * Pre condition: the queue has at least one element.
	 * Post condition: the queue is the same and the next element is returned.
	 * 
	 * @return The next element in the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Returns true if the queue has no elements
	 * 
	 * Pre condition: None.
	 * Post condition: A boolean is returned representing the state of the queue, true for an empty queue.
	 * 
	 * @return true if the queue has no elements
	 */
	public Boolean isEmpty();

	/**
	 * The size method will return the size of the queue.
	 * 
	 * Pre condition: None.
	 * Post condition: The size of the stack is returned.
	 * 
	 * @return the number of elements in the queue.
	 */
	public int size();
}
