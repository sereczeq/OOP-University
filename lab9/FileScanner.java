package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileScanner
{
	
	// File: D:\.Moje\Workspace\code\A.txt
	// File: D:\.Moje\Workspace\code\lab9.txt
	private Pattern regex;
	private String[] string;
	
	private final String fileRegex = "^File:\\s[A-Za-z0-9\\\\.:]+.txt$";
	private final String regexRegex = "^Regex:\\s.+";
	
	public void execution()
	{
		
		System.out.println("awaiting input, type \"Help\" for help");
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine())
		{
			String input = scanner.nextLine();
			
			if (isPattern("[Ee]xit", input)) break;
			else if (isPattern("^[Hh]elp$", input)) help();
			else if (isPattern(fileRegex, input)) fileToArray(input);
			else if (isPattern(regexRegex, input)) addRegex(input);
			else if (string == null) System.out.println("No file given"); // can't search or list if there's no file
			else if (isPattern("^Search$", input)) search();
			else if (isPattern("^List$", input)) list();
			else System.out.println("sorry, command unrecognized");
			System.out.println("awaiting input");
			
		}
		
		System.out.println("program closed");
		scanner.close();
		
	}
	
	
	private void fileToArray(String input)
	{
		
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new File(input.split("File: ")[1]));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("sorry, file not found");
			return;
		}
		List<String> list = new ArrayList<String>();
		while (scanner.hasNextLine())
		{
			list.add(scanner.nextLine());
		}
		scanner.close();
		string = list.toArray(new String[] { });
		
	}
	
	
	private void help()
	{
		
		System.out.println("to exit program type: Exit\n" + "to input a file type: File: file_path\n"
				+ "to list lines from file type: List\n" + "to search for an expression:\n"
				+ "  1) specify expression you want to find by typing: Regex: expression\n" + "  2) type: Search");
		
	}
	
	
	private void addRegex(String input)
	{
		
		try
		{
			regex = Pattern.compile(input.split("Regex: ")[1]);
			System.out.println("regex " + regex + " added succesfully");
		}
		catch (PatternSyntaxException ex)
		{
			System.err.println("pattern " + input.split("Regex: ")[1] + " couldn't be compiled");
		}
		
	}
	
	
	private void search()
	{
		
		boolean found = false;
		boolean foundAny = false;
		if (regex == null)
		{
			System.out.println("sorry, no regex to look for");
			return;
		}
		for (int x = 0; x < string.length; x++)
		{
			found = false;
			Matcher matcher = regex.matcher(string[x]);
			String outputString = "";
			while (matcher.find())
			{
				if (!found)
				{
					outputString = "[" + x + "]" + " {";
				}
				else outputString += ", ";
				found = true;
				foundAny = true;
				outputString += matcher.group();
			}
			if (found)
			{
				outputString += "} " + string[x];
				System.out.println(outputString);
			}
			
		}
		if (!foundAny) System.out.println("no regex in the file");
		
	}
	
	
	private void list()
	{
		
		for (int x = 0; x < string.length; x++)
		{
			System.out.println("[" + x + "]" + " " + string[x]);
		}
		
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
			return false;
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
