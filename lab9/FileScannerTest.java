package lab9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class FileScannerTest
{
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUpStreams()
	{
		
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		
	}
	
	
	@After
	public void restoreStreams()
	{
		
		System.setOut(originalOut);
		System.setErr(originalErr);
		
	}
	
	
	@Test
	void test()
	{
		
		FileScanner f = new FileScanner();
		f.execution();
		assertEquals("awaiting input, type \"Help\" for help\nprogram closed", outContent.toString());
		
	}
	
}
