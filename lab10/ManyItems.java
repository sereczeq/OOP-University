package lab10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lab.lab6Task1Enum.ProductType;

public class ManyItems
{
	
	private Vector<ItemW> data = new Vector<ItemW>();
	public final int notProperProductType = 1;
	public final int notProperQuantity = 2;
	public final int otherError = 3;
	public final int lineOk = 10;
	private int rejectedCount = 0;
	private String[] currentItemString;
	
	public ManyItems()
	{
		
	}
	
	
	public int addItem(String oneLine)
	{
		
		if (isItem(oneLine))
		{
			if (isQuantityProper())
			{
				data.add(stringToItem());
				return lineOk;
			}
			else return notProperQuantity;
		}
		else return notProperProductType;
		
	}
	
	
	public int addItemsFromFile(String fileName) // number of accepted lines
	{
		
		int numberOfAcceptedLines = 0;
		FileReader fileReader = null;
		try
		{
			fileReader = new FileReader(fileName);
		}
		catch (FileNotFoundException ex)
		{
			System.err.println("wrong file path");
			return otherError;
		}
		
		BufferedReader buf = new BufferedReader(fileReader);
		String line;
		try
		{
			while ((line = buf.readLine()) != null)
			{
				if (addItem(line) == 10) numberOfAcceptedLines++;
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
				System.err.println("bufferer couldn't be closed");
			}
			return otherError;
		}
		try
		{
			buf.close();
		}
		catch (IOException e)
		{
			System.err.println("bufferer couldn't be closed");
		}
		return numberOfAcceptedLines;
		
	}
	
	
	public boolean isItem(String line)
	{
		
		String string = "^(Bread|Milk|Tea|Butter|Other)\\s\\d+(\\.\\d+)?\\s[+-]?\\d+\\s\"[A-Za-z0-9\\s]+\"$";
		Pattern regex = Pattern.compile(string);
		Matcher matcher = regex.matcher(line);
		if (matcher.find())
		{
			currentItemString = matcher.group().split("\\s");
			return true;
		}
		else return false;
		
	}
	
	
	public boolean isQuantityProper()
	{
		
		return !currentItemString[2].contains("-");
		
	}
	
	
	public ItemW stringToItem()
	{
		
		String[] s = currentItemString;
		String comment = "";
		for (int x = 3; x < s.length; x++)
		{
			comment += s[x] + " ";
		}
		ItemW item = new ItemW(ProductType.fromString(s[0]), Integer.parseInt(s[2]), comment, Float.parseFloat(s[1]));
		return item;
		
	}
	
	
	public int getErrorNumber() // number of rejected lines
	{
		
		return rejectedCount;
		
	}
	
	
	// public void sortByWeight()
	// public void sortByType()
	// public void sortByQualityAndWeight()
	public void showData()
	{
		
		for (ItemW x : data) System.out.println(x);
		
	}
	
	
	public static void main(String[] args)
	{
		
		ManyItems m = new ManyItems();
		m.addItem("Tea 2.4 5 \"Good quality\"");
		m.addItem("Bread 1 1 \"good bread\"");
		m.addItem("Milk 12.5 32 \"old milk but drinkable\"");
		m.addItem("dsjkl");
		System.out.println(m.isItem("Tea 2.4 5 \"Good quality\""));
		System.out.println(m.isQuantityProper());
		m.showData();
		
	}
	
}
