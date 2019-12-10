package lab8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class BinaryTest
{
	
	// 7 * 12 + 10 = 94 tests
	
	int[] testValues = {10, -10, 0, 69420, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE + 1 };
	
	@Test
	void createBinaryFromIntTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			assertTrue(bn.toString().contains(Integer.toBinaryString(x)));
		}
		
	}
	
	
	@Test
	void createBinaryFromBinaryTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			Binary newBn = new Binary(bn);
			assertTrue(bn.equals(newBn));
		}
		
	}
	
	
	@Test
	void createBinaryFromStringAndCharTest()
	{
		
		for (String x : new String[] {"1010", "00000000001", "0", "", "abc", "103010201", "\"", "\n",
				"I'm great programmer", "1111111111111111111111111111111111111111111111111111111111111" })
		{
			Binary bn1 = new Binary(x);
			Binary bn2 = new Binary(x.toCharArray());
			assertEquals(bn1.toString(), bn2.toString());
		}
		
	}
	
	
	@Test
	void getIntegerTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			assertEquals(x, bn.getInteger());
		}
		
	}
	
	
	@Test
	void calcANDTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			for (int y : testValues)
			{
				assertEquals(bn.calcAND(y).getInteger(), x & y);
			}
		}
		
	}
	
	
	@Test
	void calcORTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			for (int y : testValues)
			{
				assertEquals(bn.calcOR(y).getInteger(), x | y);
			}
		}
		
	}
	
	
	@Test
	void calcXORTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			for (int y : testValues)
			{
				assertEquals(bn.calcXOR(y).getInteger(), x ^ y);
			}
		}
		
	}
	
	
	@Test
	void calcANDBinaryTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			for (int y : testValues)
			{
				assertEquals(bn.calcAND(new Binary(y)).getInteger(), x & y);
			}
		}
		
	}
	
	
	@Test
	void calcORBinaryTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			for (int y : testValues)
			{
				assertEquals(bn.calcOR(new Binary(y)).getInteger(), x | y);
			}
		}
		
	}
	
	
	@Test
	void calcXORBinaryTest()
	{
		
		for (int x : testValues)
		{
			Binary bn = new Binary(x);
			for (int y : testValues)
			{
				assertEquals(bn.calcXOR(new Binary(y)).getInteger(), x ^ y);
			}
		}
		
	}
	
	
	@Test
	void equalsTest()
	{
		
		for (int x : testValues)
		{
			Binary bn1 = new Binary(x);
			Binary bn2 = new Binary(x);
			assertTrue(bn1.equals(bn2));
		}
		
	}
	
	
	@Test
	void equalsNullTest()
	{
		
		Binary bn = new Binary(10);
		assertFalse(bn.equals(null));
		
	}
	
	
	@Test
	void equalsStringTest()
	{
		
		Binary bn = new Binary(10);
		String string = "10";
		assertFalse(bn.equals(string));
		
	}
	
}
