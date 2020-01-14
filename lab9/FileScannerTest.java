package lab9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
	void testProper()
	{
		
		FileScanner f = new FileScanner();
		Sinn(file + "\nRegex: abc" + "\nSearch");
		f.execution();
		assertEquals(Sout("awaiting input\r\n" + "regex abc added succesfully\r\n" + "awaiting input\r\n"
				+ "[1] {abc, abc, abc, abc} daghjkabchaskgdaabcakjdl asdjkhabc ajjabcjka\r\n" + "awaiting input"),
				outContent.toString());
		
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
		Sinn("File: D:\\\\.Moje\\\\Workspace\\\\code\\\\A.exe");
		f.execution();
		assertEquals(Sout("No file given\r\n" + "awaiting input"), outContent.toString());
		
	}
	
	
	@Test
	void testFileDNE()
	{
		
		FileScanner f = new FileScanner();
		Sinn("File: D:\\.Moje\\Workspace\\code\\FileDNE.txt");
		f.execution();
		assertEquals("sorry, file not found\r\n", errContent.toString());
		
	}
	
	
	@Test
	void testCommandUnrecognized()
	{
		
		FileScanner f = new FileScanner();
		String[] commands = {"regex", "a", null, "\\\\", "\n" }; // empty string "" doesn't work
		for (String c : commands)
		{
			Sinn(file + "\n" + c);
			f.execution();
			assertEquals(Sout("awaiting input\r\n" + "sorry, command unrecognized\r\n" + "awaiting input"),
					outContent.toString());
			outContent.reset();
		}
		
	}
	
	
	@Test
	void testCommandRecognition()
	{
		
		FileScanner f = new FileScanner();
		String[] commands = {"Regex: regex", "Search", "Help", "help", "List", "exit", "Exit" };
		for (String c : commands)
		{
			Sinn(file + "\n" + c);
			f.execution();
			assertFalse(outContent.toString().contains("sorry, command unrecognized"));
		}
		
	}
	
	
	@Test
	void testRegexRecognitionCorrect()
	{
		
		FileScanner f = new FileScanner();
		String[] commands = {"Regex: regex", "Regex: abc", "Regex: a\b/b", "Regex: \\\\" };
		for (String c : commands)
		{
			Sinn(file + "\n" + c);
			f.execution();
			assertEquals(Sout("awaiting input\r\n" + "regex " + c.split("Regex: ")[1] + " added succesfully\r\n"
					+ "awaiting input"), outContent.toString());
			outContent.reset();
		}
		
	}
	
	
	@Test
	void testSearchIncorrect()
	{
		
		FileScanner f = new FileScanner();
		String[] commands = {"shagk", "12345", "oiluktyjfg" };
		for (String c : commands)
		{
			Sinn(file + "\n" + "Regex: " + c + "\nSearch");
			f.execution();
			assertEquals(Sout("awaiting input\r\n" + "regex " + c + " added succesfully\r\n" + "awaiting input\r\n"
					+ "no regex in the file\r\n" + "awaiting input" + ""), outContent.toString());
			outContent.reset();
			
		}
		
	}
	
	
	@AfterEach
	public void restoreStreams()
	{
		
		System.setOut(originalOut);
		System.setErr(originalErr);
		System.setIn(oryginalIn);
		
	}
	
}
