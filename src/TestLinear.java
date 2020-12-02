import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Unit test for testing linear probing.
 * 
 * @author Greg Gagne - April 2017.
 *
 */
public class TestLinear {

	@Test
	public void testAdd() {
		DictionaryInterface<String, Integer> d = new HashDictionaryLinear<String, Integer>();

		// some basic additions
		assertEquals(new Integer(16),d.put("Jay", 16));
		assertEquals(new Integer(19),d.put("Tom", 19));
		assertEquals(new Integer(1),d.put("Sydney", 1));
		assertEquals(new Integer(31),d.put("Nate", 31));
		assertEquals(new Integer(31),d.put("Emily", 31));

		// now test that put() replaces an existing mapping
		assertEquals(new Integer(20), d.put("Tom", 20));
		assertEquals(5,d.size());

		// replace an existing entry with the same value
		// should have no effect
		assertEquals(new Integer(31),d.put("Emily", 31));
		assertEquals(5,d.size());
	}

	@Test
	public void testGet() {
		DictionaryInterface<String, Integer> d = new HashDictionaryLinear<String, Integer>();

		assertNull(d.get("Tom"));

		assertEquals(new Integer(19),d.put("Tom", 19));

		assertEquals(new Integer(19), d.get("Tom"));
	}

	@Test
	public void testContains() {
		DictionaryInterface<String, Integer> d = new HashDictionaryLinear<String, Integer>();

		assertFalse(d.contains("Tom"));

		assertEquals(new Integer(19),d.put("Tom", 19));

		assertTrue(d.contains("Tom"));

	}

	@Test
	public void testAddandRemove() {
		DictionaryInterface<Integer, String> d = new HashDictionaryLinear<Integer, String>();     
		d.put(0, "Jay");
		d.put(13, "Tom"); // default size is 13.
		d.put(26, "Sydney");
		d.remove(13);
		d.put(26,"Sydney_Modify");
		assertEquals(2,d.size());  // size is not 3!       
	}

	@Test
	public void testStressfulAdd() {
		DictionaryInterface<String, Integer> d = new HashDictionaryLinear<String, Integer>();

		// some basic additions
		assertEquals(new Integer(16),d.put("Jay", 16));
		assertEquals(new Integer(19),d.put("Tom", 19));
		assertEquals(new Integer(1),d.put("Sydney", 1));
		assertEquals(new Integer(31),d.put("Nate", 31));
		assertEquals(new Integer(31),d.put("Emily", 31));

		// now test that put() replaces an existing mapping
		assertEquals(new Integer(20), d.put("Tom", 20));
		assertEquals(5,d.size());

		// replace an existing entry with the same value
		// should have no effect
		assertEquals(new Integer(31),d.put("Emily", 31));
		assertEquals(5,d.size());

		// some basic retrievals
		assertEquals(new Integer(16), d.get("Jay"));
		assertEquals(new Integer(20), d.get("Tom"));
		assertEquals(new Integer(1), d.get("Sydney"));
		assertEquals(new Integer(31), d.get("Nate"));
		assertEquals(new Integer(31), d.get("Emily"));

		assertNull(d.get("Paul"));

		// now trigger rehashing the table
		assertEquals(new Integer(25),d.put("Andrew", 25));
		assertEquals(new Integer(6),d.put("Liam", 6));
		assertEquals(new Integer(2),d.put("Tilda", 2));
		assertEquals(new Integer(35),d.put("Becky", 35));

		// this should cause the rehash
		assertEquals(new Integer(12),d.put("Mitzi", 12));

		assertEquals(10,d.size());

		// now ensure everything is present
		assertTrue(d.contains("Jay"));
		assertTrue(d.contains("Tom"));
		assertTrue(d.contains("Sydney"));
		assertTrue(d.contains("Nate"));
		assertTrue(d.contains("Emily"));
		assertTrue(d.contains("Andrew"));
		assertTrue(d.contains("Liam"));
		assertTrue(d.contains("Tilda"));
		assertTrue(d.contains("Becky"));
		assertTrue(d.contains("Mitzi"));

		assertFalse(d.contains("Henry"));

		// now a stress test
		d = new HashDictionaryLinear<String, Integer>();

		for (int i = 0; i < 50; i++) {
			assertEquals(new Integer(i), d.put(Integer.toString(i), i) );
		}

		for (int i = 0; i < 50; i++) {
			assertTrue(d.contains(Integer.toString(i)));
		}

		assertEquals(50,d.size());
	}

	@Test
	public void testRemove() {
		DictionaryInterface<String, Integer> d = new HashDictionaryLinear<String, Integer>();

		assertEquals(new Integer(16),d.put("Jay", 16));
		assertEquals(new Integer(19),d.put("Tom", 19));
		assertEquals(new Integer(1),d.put("Sydney", 1));
		assertEquals(new Integer(31),d.put("Nate", 31));
		assertEquals(new Integer(31),d.put("Emily", 31));

		assertEquals(5, d.size());

		// now remove

		assertEquals(new Integer(31),d.remove("Nate"));

		assertEquals(4,d.size());

		assertFalse(d.contains("Nate"));

		// new mapping for Nate
		assertEquals(new Integer(35), d.put("Nate", 35));

		assertEquals(5, d.size());

		assertTrue(d.contains("Nate"));

		assertEquals(new Integer(51),d.put("Kim",51));
		assertEquals(new Integer(53),d.put("Kris", 53));
		assertEquals(new Integer(55),d.put("Doug", 55));
		assertEquals(new Integer(54),d.put("Mike", 54));

		assertEquals(new Integer(51),d.remove("Kim"));
		assertEquals(8, d.size());
		assertFalse(d.contains("Kim"));
	}

	@Test
	public void testKeySet() {
		DictionaryInterface<String, Integer> d = new HashDictionaryLinear<String, Integer>();

		assertEquals(new Integer(16),d.put("Jay", 16));
		assertEquals(new Integer(19),d.put("Tom", 19));
		assertEquals(new Integer(1),d.put("Sydney", 1));
		assertEquals(new Integer(31),d.put("Nate", 31));
		assertEquals(new Integer(31),d.put("Emily", 31));

		Set<String> keys = d.keySet();

		assertEquals(5,keys.getSize());

		assertTrue(keys.contains("Jay"));
		assertTrue(keys.contains("Tom"));
		assertTrue(keys.contains("Sydney"));
		assertTrue(keys.contains("Nate"));
		assertTrue(keys.contains("Emily"));

		// now remove an item and make sure the key does not appear in the set

		assertEquals(new Integer(31),d.remove("Nate"));

		keys = d.keySet();

		assertEquals(4,keys.getSize());

		assertTrue(keys.contains("Jay"));
		assertTrue(keys.contains("Tom"));
		assertTrue(keys.contains("Sydney"));
		assertTrue(keys.contains("Emily"));
	}

	@Test
	public void testValueSet() {
		DictionaryInterface<String, Integer> d = new HashDictionaryLinear<String, Integer>();

		assertEquals(new Integer(16),d.put("Jay", 16));
		assertEquals(new Integer(19),d.put("Tom", 19));
		assertEquals(new Integer(1),d.put("Sydney", 1));
		assertEquals(new Integer(31),d.put("Nate", 31));
		assertEquals(new Integer(31),d.put("Emily", 31));

		Set<Integer> values = d.valueSet();

		assertEquals(4,values.getSize());

		assertTrue(values.contains(16));
		assertTrue(values.contains(19));
		assertTrue(values.contains(1));
		assertTrue(values.contains(31));

		// now remove an item and make sure the key does not appear in the set

		assertEquals(new Integer(31),d.remove("Nate"));

		values = d.valueSet();

		assertEquals(4,values.getSize());

		assertTrue(values.contains(16));
		assertTrue(values.contains(19));
		assertTrue(values.contains(1));
		assertTrue(values.contains(31));
	}
}
