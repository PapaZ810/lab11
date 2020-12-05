/**
 * Implementation of the List interface.
 * 
 * This implementation involves a single-linked list.
 * 
 * @author Greg Gagne - February 2017
 *
 */
public class LinkedList<T> implements List<T> 
{
	// reference to the head of the linked list
	private Node head;

	// number of elements in the list
	private int numberOfElements;

	public LinkedList() 
	{
		head = null;
	}

	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private T data;
		private Node next;

		private Node(T data) 
		{
			this(data,null);
		}

		private Node (T data, Node next) 
		{
			this.data = data;
			this.next = next;
		}
		
	}

	// Methods

	@Override
	public void add(T item) 
	{

		// adds (appends) an item to the rear of the list

		Node newNode = new Node(item);
		Node current = head;

		if (isEmpty()) 
		{
			// special case - first element being added to the list
			head = newNode;
		}
		else 
		{
			while (current.next != null) 
			{
				current = current.next;
			}

			// current now references the last item in the list
			current.next = newNode;
		}

		newNode.next = null;
		++numberOfElements;
	}

	@Override
	public boolean add(T item, int index) 
	{
		Node newNode = new Node(item);
		
		if (index == 0) 
		{
	        newNode.next = head;
	        head = newNode;
	        ++numberOfElements;
	    } 
		else 
	    {
	        Node node = head;
	        while (--index > 0) 
	        {
	            node = node.next;
	        }
	        newNode.next = node.next;
	        node.next = newNode;
	        ++numberOfElements;
	    }
		
		return false;
	}

	@Override
	public boolean contains(T item) 
	{
		Node current = head;
		boolean found = false;

		while (current != null && !found) 
		{
			if (current.data.equals(item)) 
			{
				found = true;
			}

			current = current.next;
		}

		return found;

	}

	@Override
	public T get(int index) 
	{
		Node node = head;
		
		if(index >= this.getLength())
		{
			return null;
		}
		
		for(int i = 0; i <= index; i++)
		{
			if(i == index)
				return node.data;
			else
				node = node.next;
		}
		return null;
	}

	@Override
	public boolean remove(T item) 
	{
		
		if(!this.contains(item))
		{
			return false;
		}
		else if(head.data == item)
		{
			this.remove(0);
			return true;
		}
		else
		{
			for(int i = 0; i < this.getLength(); i++)
			{
				if(this.get(i).equals(item))
				{
					this.remove(i);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public T remove(int index) 
	{
		T rv = null;

		if (isEmpty() || index >= numberOfElements) 
		{
			rv = null;
		}
		else if (index == 0) 
		{
			// special case - first element in the list
			rv = head.data;
			head = head.next;
			numberOfElements--;
		}
		else 
		{
			int currentIndex = 0;
			Node current = head;

			while (currentIndex < (index - 1)) 
			{
				current = current.next;
				currentIndex++;
			}

			// current references the node we want to remove
			rv = current.next.data;
			current.next = current.next.next;
			numberOfElements--;
		}

		return rv;
	}

	@Override
	public int getLength() 
	{
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() 
	{
		if(head == null)
			return true;
		else 
			return false;

	}

	@Override
	public int getFrequency(T item) 
	{
		int count = 0;
		
		for(int i = 0; i < this.getLength(); i++)
		{
			if(this.get(i).equals(item))
			{
				count++;
			}
		}

		return count;
	}

	@Override
	public void clear() 
	{
		Node node = head;
		
		if(node != null)
		{
			for(int i = this.getLength(); i >= 0; i--)
			{
				this.remove(i);
			}
		}
	}

}
