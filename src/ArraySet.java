import java.util.Iterator;

@SuppressWarnings("unchecked")

public class ArraySet<T> implements Set<T>
{
	public static final int DEFAULT_CAPACITY = 5;
	public static final int CAPACITY_MULTIPLIER = 2;
	
	private int size = 0;
	private int numberOfElements = 0;
	private T[] elements;
	
	public ArraySet() 
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ArraySet(int size)
	{
		if(size < 0)
		{
			throw new IllegalArgumentException("Size must be >= 0");
		}
		
		this.size = size;
		elements = (T[])new Object[size];
	}

	public void add(T element) 
	{
		ensureCapacity();
		if(this.contains(element) != true)
		{
			elements[numberOfElements] = element;
			numberOfElements++;
		}
		
	}

	public void addAll(T[] elements) 
	{
		for(int i = 0; i < elements.length; i++)
		{
			add(elements[i]);
		}
	}

	public boolean contains(T element) 
	{
		if(indexOf(element) > -1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getSize() 
	{
		return numberOfElements;
	}

	public void remove(T element) 
	{
		int index = indexOf(element);
		
		if (index > -1) 
		{
			numberOfElements--;
			elements[index] = elements[numberOfElements];
		}

		return;
	}
	
	private int indexOf(T element) 
	{
		int index = -1;
		for (int i = 0; i < numberOfElements; i++) 
		{
			if (elements[i].equals(element)) 
			{
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	private void ensureCapacity() 
	{
		if (numberOfElements == size) 
		{
			T[] newArray = (T[])new Object[(numberOfElements+1) * CAPACITY_MULTIPLIER];
			System.arraycopy(elements,0,newArray,0,numberOfElements);
			elements = newArray;
		}
	}
	
	public T get(int i)
	{
		return elements[i];
	}
	
	public Set<T> union(Set<T> anotherSet) 
	{	
		Iterator<T> itr = this.iterator();
		Set<T> resultSet = anotherSet;
		while(itr.hasNext())
		{
			T next = itr.next();
			if(!resultSet.contains(next))
			{
				resultSet.add(next);
			}
		}
		
		return resultSet;
	}

	public Set<T> intersection(Set<T> anotherSet) 
	{
		Set<T> resultSet = new ArraySet<T>();
		
		for(int i = 0; i < this.numberOfElements; i++)
		{
				if(anotherSet.contains(this.get(i)))
				{
					resultSet.add(this.get(i));
				}
		}
		
		return resultSet;
	}

	public Set<T> difference(Set<T> anotherSet) 
	{
		Set<T> resultSet = new ArraySet<T>();
		
		for(int i = 0; i < this.getSize(); i++)
		{
			resultSet.add(this.get(i));
		}
		
		for(int i = 0; i < this.numberOfElements; i++)
		{
				if(anotherSet.contains(this.get(i)))
				{
					resultSet.remove(this.get(i));
				}
		}
		
		return resultSet;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new ArraySetIterator();
	}

	private class ArraySetIterator implements Iterator<T>
	{
		private int index = 0;
		
		/**
		 * Determines if there are more elements
		 * in the iteration.
		 * 
		 * @return true if there are more elements, false otherwise.
		 */
		public boolean hasNext() {
			if (index < numberOfElements)
				return true;
			else
				return false;
		}

		/**
		 * Returns the next element in the iteration.
		 * 
		 * @throws java.util.NoSuchElementException if there are no more elements in the iteration.
		 */
		public T next() {
			if (hasNext()) {
				T nextItem = elements[index];
				index++;
				
				return nextItem;
			}
			else
				throw new java.util.NoSuchElementException("No items remaining in the iteration.");
			
		}

		/**
		 * The remove() operation is not supported.
		 * @throws UnsupportedOperationException if involed.
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
