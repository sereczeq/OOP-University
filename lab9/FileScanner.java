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
	
	private final String fileRegex = "^File:\\s[A-Za-z\\\\.:]+.txt$";
	private final String regexRegex = "^Regex:\\s.+";
	
	public void execution()
	{
		
		System.out.println("awaiting input, type \"Help\" for help");
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine())
		{
			String input = scanner.nextLine();
			
			if (input.contains("Exit"))
			{
				System.out.println("program closed");
				break;
			}
			
			if (isPattern("^Help$", input)) help();
			else if (isPattern(fileRegex, input)) fileToArray(input);
			else if (string == null) System.out.println("No file given");
			else if (isPattern(regexRegex, input)) addRegex(input);
			else if (isPattern("^Search$", input)) search();
			else if (isPattern("^List$", input)) list();
			else System.out.println("sorry, command unrecognized");
			System.out.println("awaiting input");
			
		}
		
	}
	
	
	private void fileToArray(String input)
	{
		
		FileReader fileReader = null;
		try
		{
			fileReader = new FileReader(input.split("File: ")[1]);
		}
		catch (FileNotFoundException ex)
		{
			System.err.println("wrong file path");
			return;
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
			try
			{
				buf.close();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		try
		{
			buf.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		regex = input.split("Regex: ")[1];
		System.out.println("regex " + regex + " added succesfully");
		
	}
	
	
	private void search()
	{
		
		boolean found = false;
		if (regex == null)
		{
			System.out.println("sorry, no regex to look for");
			return;
		}
		for (int x = 0; x < string.length; x++)
		{
			if (isPattern(regex, string[x]))
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
				Matcher matcher = pattern.matcher(string[x]);
				
				String outputString = "[" + x + "]" + " ";
				boolean matcherFound = false;
				while (matcher.find())
				{
					if (!matcherFound) outputString += "{";
					else outputString += ", ";
					matcherFound = true;
					outputString += matcher.group();
				}
				if (matcherFound) outputString += "} ";
				
				outputString += string[x];
				
				System.out.println(outputString);
				found = true;
			}
		}
		if (!found) System.out.println("no regex in the file");
		
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
