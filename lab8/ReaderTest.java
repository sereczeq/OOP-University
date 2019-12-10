package lab8;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ReaderTest
{
	
	// (49 + 12*12 + 7 * 12 * 2) * 3 = 1083 tests
	
	private int[] testValuesInt = {10, -10, 0, 69420, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE + 1 };
	private String[] testValuesString = {"1010", "00000000001", "0", null, "", "abc", "103010201", "\"", "\n",
			"\"10010\"", "I'm a great programmer", "1111111111111111111111111111111111111111111111111111111111111" };
	
	@Test
	void ANDIntTest()
	{
		
		for (int x : testValuesInt)
		{
			for (int y : testValuesInt)
			{
				String s = x + " and " + y;
				assertEquals(Reader.calculator(s), new Binary(x).calcAND(y));
			}
		}
		
	}
	
	
	@Test
	void ANDStringTest()
	{
		
		for (String x : testValuesString)
		{
			for (String y : testValuesString)
			{
				String s = "\"" + x + "\"" + " and " + "\"" + y + "\"";
				if (y == null || x == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcAND(bn2).toString());
			}
		}
		
	}
	
	
	@Test
	void ANDStringIntTest()
	{
		
		for (String x : testValuesString)
		{
			for (int y : testValuesInt)
			{
				String s = "\"" + x + "\"" + " and " + y;
				if (x == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcAND(bn2).toString());
			}
		}
		
	}
	
	
	@Test
	void ANDIntStringTest()
	{
		
		for (int x : testValuesInt)
		{
			for (String y : testValuesString)
			{
				String s = x + " and " + "\"" + y + "\"";
				if (y == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcAND(bn2).toString());
			}
		}
		
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// OR tests
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Test
	void ORIntTest()
	{
		
		for (int x : testValuesInt)
		{
			for (int y : testValuesInt)
			{
				String s = x + " or " + y;
				assertEquals(Reader.calculator(s), new Binary(x).calcOR(y));
			}
		}
		
	}
	
	
	@Test
	void ORStringTest()
	{
		
		for (String x : testValuesString)
		{
			for (String y : testValuesString)
			{
				String s = "\"" + x + "\"" + " or " + "\"" + y + "\"";
				if (y == null || x == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcOR(bn2).toString());
			}
		}
		
	}
	
	
	@Test
	void ORStringIntTest()
	{
		
		for (String x : testValuesString)
		{
			for (int y : testValuesInt)
			{
				String s = "\"" + x + "\"" + " or " + y;
				if (x == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcOR(bn2).toString());
			}
		}
		
	}
	
	
	@Test
	void ORIntStringTest()
	{
		
		for (int x : testValuesInt)
		{
			for (String y : testValuesString)
			{
				String s = x + " or " + "\"" + y + "\"";
				if (y == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcOR(bn2).toString());
			}
		}
		
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	// XOR tests
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Test
	void XORIntTest()
	{
		
		for (int x : testValuesInt)
		{
			for (int y : testValuesInt)
			{
				String s = x + " xor " + y;
				assertEquals(Reader.calculator(s), new Binary(x).calcXOR(y));
			}
		}
		
	}
	
	
	@Test
	void XORStringTest()
	{
		
		for (String x : testValuesString)
		{
			for (String y : testValuesString)
			{
				String s = "\"" + x + "\"" + " xor " + "\"" + y + "\"";
				if (y == null || x == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcXOR(bn2).toString());
			}
		}
		
	}
	
	
	@Test
	void XORStringIntTest()
	{
		
		for (String x : testValuesString)
		{
			for (int y : testValuesInt)
			{
				String s = "\"" + x + "\"" + " xor " + y;
				if (x == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcXOR(bn2).toString());
			}
		}
		
	}
	
	
	@Test
	void XORIntStringTest()
	{
		
		for (int x : testValuesInt)
		{
			for (String y : testValuesString)
			{
				String s = x + " xor " + "\"" + y + "\"";
				if (y == null) continue;
				Binary bn1 = new Binary(x);
				Binary bn2 = new Binary(y);
				assertEquals(Reader.calculator(s).toString(), bn1.calcXOR(bn2).toString());
			}
		}
		
	}
	
}
