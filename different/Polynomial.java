package different;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import lab.ArrayList4;

public class Polynomial
{
	
	private int[] coeficients;
	private int[] powers;
	
	private Polynomial(int[] coef, int[] pow)
	{
		
		System.out.println("here");
		
		if (coef.length == pow.length)
		{
			coeficients = coef;
			powers = pow;
		}
		if (coef.length > pow.length)
		{
			coeficients = coef;
			powers = new int[coef.length];
			for (int x = 0; x < pow.length; x++)
			{
				powers[x] = pow[x];
			}
		}
		if (pow.length > coef.length)
		{
			powers = pow;
			coeficients = new int[pow.length];
			for (int x = 0; x < coeficients.length; x++)
			{
				coeficients[x] = 1;
			}
			for (int x = 0; x < coef.length; x++)
			{
				coeficients[x] = coef[x];
			}
		}
		sum();
		sort();
		
	}
	
	
	private float solveFor(int x)
	{
		
		float sum = 0;
		for (int y = 0; y < powers.length; y++)
		{
			sum += Math.pow(x, powers[y]) * coeficients[y];
		}
		return sum;
		
	}
	
	
	private String findRoots()
	{
		
		Vector<Float> returnVector = new Vector<Float>();
		int[] top = find(coeficients[coeficients.length - 1]);
		int[] bot = find(coeficients[0]);
		for (int x : top)
		{
			for (int y : bot)
			{
				if (solveFor(x / y) == 0)
				{
					returnVector.add((float) (x / y));
				}
			}
		}
		Float[] array = Arrays.copyOf(returnVector.toArray(), returnVector.toArray().length, Float[].class);
		String string = "";
		
		for (Float x : array)
		{
			if (!string.contains(x + " ")) string += x + " ";
		}
		return string;
		
	}
	
	
	private int[] find(int number)
	{
		
		int num = Math.abs(number);
		int[] returnArray = { };
		for (int x = 1; x <= num; x++)
		{
			if (num % x == 0)
			{
				returnArray = ArrayList4.appendArrays(returnArray, new int[] {x, -x });
			}
		}
		return returnArray;
		
	}
	
	
	private void sort()
	{
		
		for (int x = 0; x < powers.length; x++)
		{
			for (int y = x; y < powers.length; y++)
			{
				if (powers[y] > powers[x])
				{
					int temp = powers[x];
					powers[x] = powers[y];
					powers[y] = temp;
					temp = coeficients[x];
					coeficients[x] = coeficients[y];
					coeficients[y] = temp;
					x = 0;
					y = 0;
				}
			}
		}
		
	}
	
	
	private void sum()
	{
		
		for (int x = 0; x < powers.length; x++)
		{
			for (int y = x + 1; y < powers.length; y++)
			{
				if (powers[x] == powers[y])
				{
					coeficients[x] += coeficients[y];
					powers[y] = 0;
					coeficients[y] = 0;
				}
			}
		}
		
	}
	
	
	public String toString()
	{
		
		String returnString = "";
		for (int x = 0; x < coeficients.length; x++)
		{
			if (coeficients[x] == 0) continue;
			if (coeficients[x] != 1 || (coeficients[x] == 1 && powers[x] == 0)) returnString += coeficients[x];
			if (powers[x] != 0)
			{
				returnString += "x";
				if (powers[x] != 1) returnString += "^" + powers[x];
			}
			if (x != coeficients.length - 1 && coeficients[x + 1] != 0) returnString += " + ";
		}
		return returnString;
		
	}
	
	
	// All of below is copied from array class XD
	public static int[] Input(Scanner scanner)
	{
		
		while (!scanner.hasNextInt())
		{
			if (scanner.next().contentEquals("^Z")) return null;
		}
		
		return ArrayList4.appendArrays(new int[] {scanner.nextInt() }, Input(scanner));
		
	}
	
	
	public static void main(String[] args)
	{
		
		System.out.print("Input coeficients: ");
		int[] coef = Input(new Scanner(System.in));
		System.out.print("Input powers: ");
		int[] pow = Input(new Scanner(System.in));
		Polynomial pn = new Polynomial(coef, pow);
		System.out.println(pn);
		System.out.println(pn.findRoots());
		
	}
	
}
