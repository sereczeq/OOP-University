package exercise9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifierReader
{
	
	private Vector<String> identifiers = new Vector<String>();
	private boolean ignoreCases;
	private final Pattern identifierRegexCase = Pattern.compile("^[A-Z][a-z_]+$");
	private final Pattern identifierRegex = Pattern.compile("^[A-Za-z_]+$");
	
	public void addFromFile(String file)
	{
		
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new File(file));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Sorry, wrong filepath given");
			return;
		}
		while (scanner.hasNextLine())
		{
			addLine(scanner.nextLine().trim());
		}
		sort();
		scanner.close();
		
	}
	
	
	private void addLine(String line)
	{
		
		Matcher matcher = null;
		if (ignoreCases) matcher = identifierRegex.matcher(line);
		else matcher = identifierRegexCase.matcher(line);
		if (matcher.find())
		{
			identifiers.add(matcher.group());
		}
		
	}
	
	
	public void sort(Comparator<String> comparator)
	{
		
		Collections.sort(identifiers, comparator);
		
	}
	
	
	public void sort()
	{
		
		Collections.sort(identifiers);
		
	}
	
	
	public static void show(Vector<String> vec)
	{
		
		vec.toString();
		
	}
	
	
	public void show()
	{
		
		for (String x : identifiers)
		{
			System.out.println(x);
		}
		
	}
	
	
	public void show(String from, String to)
	{
		
		boolean show = false;
		for (String x : identifiers)
		{
			if (x.equals(from) && show == false) show = true;
			if (x.contentEquals(to) && show == true) show = false;
			if (show) System.out.println(x);
		}
		
	}
	
	
	public static void show(Vector<String> vec, String from, String to)
	{
		
		boolean show = false;
		for (String x : vec)
		{
			if (x.equals(from) && show == false) show = true;
			if (x.contentEquals(to) && show == true) show = false;
			if (show) System.out.print(x + "\t;");
		}
		
	}
	
	
	public void showUnique()
	{
		
		Vector<String> returnVector = new Vector<String>();
		boolean show = false;
		for (String x : identifiers)
		{
			show = true;
			for (String y : identifiers)
			{
				if (x.equals(y)) show = false;
			}
			if (show) returnVector.add(x);
		}
		show(returnVector);
		
	}
	
	
	public void showUnique(String from, String to)
	{
		
		Vector<String> returnVector = new Vector<String>();
		boolean show = false;
		for (String x : identifiers)
		{
			show = true;
			for (String y : identifiers)
			{
				if (x.equals(y)) show = false;
			}
			if (show) returnVector.add(x);
		}
		show(returnVector, from, to);
		
	}
	
	
	public Vector<String> getLongest()
	{
		
		String maxS = "";
		int maxI = 0;
		for (String x : identifiers)
		{
			if (x.length() > maxI)
			{
				maxI = x.length();
				maxS = x;
			}
		}
		Vector<String> returnVector = new Vector<String>();
		returnVector.add(maxS);
		return returnVector;
		
	}
	
	
	public void setCaseSensitivity(boolean set)
	{
		
		ignoreCases = !set;
		
	}
	
	
	public void getCaseSensitivity()
	{
		
		System.out.println(!ignoreCases);
		
	}
	
	
	// D:\.Moje\Workspace\code\identifiers.txt
	public static void main(String[] args)
	{
		
		IdentifierReader reader = new IdentifierReader();
		reader.setCaseSensitivity(false);
		
		if (args != null && args.length >= 1 && args[0] != null)
		{
			reader.addFromFile(args[0]);
		}
		
		reader.show();
		System.out.println("\nfrom Abcde to Zzzz:");
		reader.show("Abcde", "Zzzz");
		
	}
	
}
