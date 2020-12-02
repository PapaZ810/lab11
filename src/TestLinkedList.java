import static org.junit.Assert.*;

import org.junit.Test;


public class TestLinkedList {

	/**
	 * This method tests add() and how it works with the
	 * isEmpty(), getLength(), and getFrequency() methods.
	 */
	@Test
	public void testAdd() {
		List<String> list = new LinkedList<String>();
		
		assertTrue(list.isEmpty());
		
		assertEquals(0,list.getLength());
		
		// now add several items to the List
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.add("Donut");
		list.add("Cherry");
		list.add("Donut");
		list.add("Cherry");
		
		assertFalse(list.isEmpty());
		assertEquals(7, list.getLength());
		
		assertEquals(0,list.getFrequency("Eggs"));
		assertEquals(1,list.getFrequency("Apple"));
		assertEquals(1,list.getFrequency("Banana"));
		assertEquals(3,list.getFrequency("Cherry"));
		assertEquals(2,list.getFrequency("Donut"));
	}
	
	/**
	 * This method tests add() and how it works with the
	 * isEmpty(), getLength(), and getFrequency() methods.
	 */
	@Test
	public void testRemove() {
		List<String> list = new LinkedList<String>();
		
		list.remove("Fig");
		
		assertTrue(list.isEmpty());
		
		assertEquals(0,list.getLength());
		
		list.add("Fig");
		assertFalse(list.isEmpty());
		assertEquals(1,list.getLength());
		list.remove("Fig");
		assertTrue(list.isEmpty());
		assertEquals(0,list.getLength());
		
		
		// now add several items to the List
		list.add("Apple");
		list.add("Banana");
		list.add("Cherry");
		list.add("Donut");
		list.add("Cherry");
		list.add("Donut");
		list.add("Cherry");
		
		list.remove("Fig");
		assertFalse(list.isEmpty());
		assertEquals(7, list.getLength());
		
		list.remove("Apple");
		
		assertFalse(list.isEmpty());
		assertFalse(list.contains("Apple"));
		assertEquals(6, list.getLength());
		
		list.remove("Cherry");
		
		assertFalse(list.isEmpty());
		assertTrue(list.contains("Cherry"));
		assertEquals(2,list.getFrequency("Cherry"));
		assertEquals(5, list.getLength());
		
		list.remove("Cherry");
		list.remove("Cherry");
		
		assertFalse(list.isEmpty());
		assertFalse(list.contains("Cherry"));
		assertEquals(3, list.getLength());
		
		list.remove("Donut");
		list.remove("Donut");
		
		assertFalse(list.isEmpty());
		assertFalse(list.contains("Donut"));
		assertEquals(1, list.getLength());
		
		list.remove("Banana");
		
		assertTrue(list.isEmpty());
		assertEquals(0,list.getLength());
		
	}
	

}
