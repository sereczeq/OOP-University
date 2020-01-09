package lab10;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ManyItemsTest
{
	
	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private static final PrintStream originalOut = System.out;
	private static final PrintStream originalErr = System.err;
	private static final InputStream oryginalIn = System.in;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
		
		System.setOut(originalOut);
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
		
		System.setOut(originalOut);
		System.setErr(originalErr);
		System.setIn(oryginalIn);
		
	}
	
	
	@Test
	void test()
	{
		
		ManyItems.main(new String[] {"D:\\.Moje\\Workspace\\code\\B.txt", "type" });
		String s = "bread [3.5] quantity: 3, comment: \"This is normal\"  weight: 1.5\r\n"
				+ "milk [2.2] quantity: 1, comment: \"This is good\"  weight: 5.0\r\n"
				+ "tea [15.0] quantity: 5, comment: \"This is good\"  weight: 2.4\r\n"
				+ "tea [15.0] quantity: 3, comment: \"This is excellent\"  weight: 1.0\r\n";
		assertEquals(s, outContent.toString());
		
	}
	
}
