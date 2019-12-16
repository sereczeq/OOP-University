package different;

import java.util.Scanner;

public class polynomial
{
	
	private int[] coeficients;
	private int[] powers;
	
	private polynomial(int[] coef, int[] pow)
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
	
	
	public String toString()
	{
		
		String returnString = "";
		for (int x = 0; x < coeficients.length; x++)
		{
			if (coeficients[x] == 0) continue;
			if (coeficients[x] != 1) returnString += coeficients[x];
			if (powers[x] != 0)
			{
				returnString += "x";
				if (powers[x] != 1) returnString += "^" + powers[x];
			}
			if (x != coeficients.length - 1) returnString += " + ";
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
		
		return appendArrays(new int[] {scanner.nextInt() }, Input(scanner));
		
	}
	
	
	public static int[] appendArrays(int[] arr1, int[] arr2)
	{
		
		if (notArray("appendArrays", arr1) && !notArray("appendArrays", arr2)) return arr2;
		else if (notArray("appendArrays", arr2) && !notArray("appendArrays", arr1)) return arr1;
		else if (notArray("appendArrays", arr1) && notArray("appendArrays", arr2)) return new int[] { };
		
		if (arr1 == null || arr1.length == 0) return arr2;
		if (arr2 == null || arr2.length == 0) return arr1;
		
		int[] array = new int[arr1.length + arr2.length];
		
		for (int x = 0; x < arr1.length + arr2.length; x++)
		{
			if (x < arr1.length) array[x] = arr1[x];
			else array[x] = arr2[x - arr1.length];
		}
		
		return array;
		
	}
	
	
	public static boolean notArray(String name, int[] arr)
	{
		
		if (arr == null || arr.length == 0) return true;
		return false;
		
	}
	
	
	public static void main(String[] args)
	{
		
		System.out.print("Input coeficients: ");
		int[] coef = Input(new Scanner(System.in));
		System.out.println("Input powers: ");
		int[] pow = Input(new Scanner(System.in));
		polynomial pn = new polynomial(coef, pow);
		System.out.println(pn);
		
	}
	
}