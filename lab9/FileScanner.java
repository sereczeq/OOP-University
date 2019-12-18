package lab9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileScanner
{
	
	// File: D:\.Moje\Workspace\code\A.txt
	private String regex;
	private String[] string;
	
	private final String fileRegex = "File:\\s[A-Za-z\\\\.:]+.txt";
	private final String regexRegex = "Regex:\\s.+";
	
	public void execution()
	{
		
		System.out.println("awaiting input, type \"Help\" for help");
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine())
		{
			System.out.println("awaiting input");
			String input = scanner.nextLine();
			if (input.contains("Exit"))
			{
				System.out.println("program closed");
				break;
			}
			
			if (isPattern("^Help$", input))
			{
				System.out.println("to exit program type: Exit\n" + "to input a file type: File: file_path\n"
						+ "to list lines from file type: List\n" + "to search for an expression:\n"
						+ "  1) specify expression you want to find by typing: Regex: expression\n"
						+ "  2) type: Search");
			}
			else if (isPattern(fileRegex, input))
			{
				string = fileToArray(input);
			}
			else if (string == null)
			{
				System.out.println("No file given");
				continue;
			}
			else if (isPattern(regexRegex, input))
			{
				regex = input.split("Regex: ")[1];
			}
			else if (isPattern("^Search$", input))
			{
				if (regex == null) System.out.println("sorry, no regex to look for");
				for (int x = 0; x < string.length; x++)
				{
					if (isPattern(regex, string[x]))
					{
						System.out.println("[" + x + "]" + " " + string[x]);
					}
				}
			}
			else if (isPattern("^List$", input))
			{
				for (int x = 0; x < string.length; x++)
				{
					System.out.println("[" + x + "]" + " " + string[x]);
				}
			}
			else System.out.println("sorry, command unrecognized");
			
		}
		
	}
	
	
	private String[] fileToArray(String input)
	{
		
		FileReader fileReader = null;
		try
		{
			fileReader = new FileReader(input.split("File: ")[1]);
		}
		catch (FileNotFoundException ex)
		{
			System.err.println("wrong file path");
		}
		
		List<String> list = new ArrayList<String>();
		BufferedReader buf = new BufferedReader(fileReader);
		String line;
		try
		{
			while ((line = buf.readLine()) != null)
			{
				list.add(line);
			}
		}
		catch (IOException e)
		{
			System.err.println("IOException in bufferer");
		}
		return list.toArray(new String[] { });
		
	}
	
	
	private static boolean isPattern(String regex, String string)
	{
		
		Pattern pattern = null;
		try
		{
			pattern = Pattern.compile(regex);
		}
		catch (PatternSyntaxException ex)
		{
			System.err.println("pattern " + regex + " couldn't be compiled");
		}
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()) return true;
		return false;
		
	}
	
	
	public static void main(String[] args)
	{
		
		FileScanner fileScanner = new FileScanner();
		fileScanner.execution();
		
	}
	
}
