package exercise9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class IntegerReader
{
	
	private HashSet<Integer> numbers = new HashSet<Integer>();
	private int numOfDuplicates = 0;
	
	private final static String fileRegex = "^[A-Za-z0-9\\\\.:]+.txt$";
	private final String integerRegex = "\\b\\d+\\b";
	
	public void addFromLine(String line)
	{
		
		Pattern regex = Pattern.compile(integerRegex);
		Matcher m = regex.matcher(line);
		if (m.find())
		{
			int current = Integer.getInteger(m.group());
			if (numbers.contains(current)) numOfDuplicates++;
			numbers.add(current);
		}
		
	}
	
	
	public boolean addFromFile(String file)
	{
		
		if (isPattern(fileRegex, file))
		{
			FileReader fileReader = null;
			try
			{
				fileReader = new FileReader(file);
			}
			catch (FileNotFoundException ex)
			{
				System.err.println("wrong file path");
				return false;
			}
			BufferedReader buf = new BufferedReader(fileReader);
			String line;
			try
			{
				while ((line = buf.readLine()) != null)
				{
					addFromLine(line);
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
					System.err.println("bufferer couldn'y be closed");
				}
				return false;
			}
			try
			{
				buf.close();
			}
			catch (IOException e)
			{
				System.err.println("bufferer couldn'y be closed");
				return false;
			}
			return true;
		}
		else return false;
		
	}
	
	
	public int getUniqueNumber()
	{
		
		return numbers.size() - numOfDuplicates;
		
	}
	
	
	public int getDuplicateNumber()
	{
		
		return numOfDuplicates;
		
	}
	
	
	public void showNumbers()
	{
		
		System.out.println(numbers.toString());
		
	}
	
	
	private boolean isPattern(String regex, String string)
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
		
		IntegerReader intR = new IntegerReader();
		for (String x : args)
		{
			if (intR.addFromFile(x))
			{
				break;
			}
		}
		
	}
	
}
