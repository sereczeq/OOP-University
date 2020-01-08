package lab9;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileScannerTest
{
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private final InputStream oryginalIn = System.in;
	
	private final String help = "awaiting input, type \"Help\" for help\r\n";
	private final String closed = "\r\nprogram closed\r\n";
	private final String file = "File: D:\\.Moje\\Workspace\\code\\A.txt";
	
	@BeforeEach
	public void setUpStreams()
	{
		
		System.setOut(originalOut);
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		
	}
	
	
	private void Sinn(String string)
	{
		
		ByteArrayInputStream in = new ByteArrayInputStream(string.getBytes());
		System.setIn(in);
		
	}
	
	
	private String Sout(String string)
	{
		
		return help + string + closed;
		
	}
	
	
	@Test
	void testExit()
	{
		
		FileScanner f = new FileScanner();
		Sinn("Exit");
		f.execution();
		assertEquals(help + "program closed\r\n", outContent.toString());
		
	}
	
	
	@Test
	void testFile()
	{
		
		FileScanner f = new FileScanner();
		Sinn(file);
		f.execution();
		assertEquals(Sout("awaiting input"), outContent.toString());
		
	}
	
	
	@Test
	void testWrongFile()
	{
		
		FileScanner f = new FileScanner();
		Sinn(file + ".exe");
		f.execution();
		assertEquals(Sout("No file given\r\n" + "awaiting input"), outContent.toString());
		
	}
	
	
	@AfterEach
	public void restoreStreams()
	{
		
		System.setOut(originalOut);
		System.setErr(originalErr);
		System.setIn(oryginalIn);
		
	}
	
}
