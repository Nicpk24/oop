package utilities;


/**
 * MyStack is a stack wher elements are added in order. The order the elements are added / accessed in is first in last out
 * @author Nic Kelly
 */
public class MyStack<E> implements StackADT{
	private int size;
	private MyArrayList<E> list;
	
	/**
	 * Create an empty stack
	 */
	public MyStack() {
		size = 0;
		list = new MyArrayList<E>();
	}
	
	/**
	 * @return the list
	 */
	public MyArrayList<E> getList() {
		return list;
	}
	
	@Override
	public void push(Object e) {
		list.add((E) e);
		size += 1;
	}

	@Override
	public Object pop() {
		
		Object returnObject = list.remove(size-1);
		size -= 1;
		
		return returnObject;
	}

	@Override
	public Object peek() {
		return list.get(size-1);
	}

	@Override
	public Boolean isEmpty() {
		return (size < 1);
	}

	@Override
	public int size() {
		return size;
	}
}
