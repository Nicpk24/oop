package utilities;


import exceptions.NoSuchElementException;

/**
 * StackADT is an interface that acts as a contract for a class to represent a stack.
 * @author Nic Kelly
 */
public interface StackADT<E> {
	/**
	 * Adds an element to the top of the stack.
	 * 
	 * Pre condition: an element send from the caller.
	 * Post condition: the element is added to the stack.
	 * 
	 * @param toAdd the element to add to the stack.
	 */
	public void push(E toAdd);

	/**
	 * Remove an element from the top of the stack and return it.
	 * 
	 * Pre condition: the stack has at lest one element.
	 * Post condition: the stack has the top value removed and it is returned.
	 * 
	 * @return The element at the top of the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	public E pop() throws NoSuchElementException;

	/**
	 * Return the next element in the stack without removing it.
	 * 
	 * Pre condition: the stack has at least one element.
	 * Post condition: the stack is the same and the next element is returned.
	 * 
	 * @return The top element in the stack.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Returns true if the stack has no elements
	 * 
	 * Pre condition: None.
	 * Post condition: A boolean is returned representing the state of the stack, true for an empty stack.
	 * 
	 * @return true if the stack has no elements
	 */
	public Boolean isEmpty();

	/**
	 * The size method will return the size of the stack.
	 * 
	 * Pre condition: None.
	 * Post condition: The size of the stack is returned.
	 * 
	 * @return the number of elements in the stack.
	 */
	public int size();
}
