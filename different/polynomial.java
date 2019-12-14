package different;

import java.util.Scanner;

public class polynomial
{
	
	private int[] coeficients;
	private int[] powers;
	
	private polynomial(int[] coef, int[] pow)
	{
		
		coeficients = coef;
		powers = pow;
		
	}
	
	
	public String toString()
	{
		
		String returnString = "";
		for (int x = 0; x < coeficients.length; x++)
		{
			if (coeficients[x] != 1) returnString += coeficients[x];
			returnString += "x^";
			returnString += powers[x];
			if (x != coeficients.length - 1) returnString += " + ";
		}
		return returnString;
		
	}
	
	
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
		
		int[] coef = Input(new Scanner(System.in));
		int[] pow = Input(new Scanner(System.in));
		polynomial pn = new polynomial(coef, pow);
		System.out.println(pn);
		
	}
	
}
