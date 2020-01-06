package lab;

import java.util.Scanner;

public class ArrayList4
{
	
	public static boolean notArray(String name, int[] arr)
	{
		
		if (arr == null || arr.length == 0) return true;
		return false;
		
	}
	
	
	public static int[] readArrayInfinite(Scanner scanner)
	{
		
		while (!scanner.hasNextInt())
		{
			if (scanner.next().contentEquals("^Z")) return null;
		}
		
		return appendArrays(new int[] {scanner.nextInt() }, readArrayInfinite(scanner));
		
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
	
	
	public static int getMaximalElement(int[] arr)
	{
		
		if (notArray("getMaximalElement", arr)) return Integer.MIN_VALUE;
		
		int max = arr[0];
		
		for (int x = 1; x < arr.length; x++)
		{
			if (arr[x] > max) max = arr[x];
		}
		
		return max;
		
	}
	
	
	public static int getMinimalElement(int[] arr)
	{
		
		if (notArray("getMinimalElement", arr)) return Integer.MAX_VALUE;
		
		int min = arr[0];
		
		for (int x = 1; x < arr.length; x++)
		{
			if (arr[x] < min) min = arr[x];
		}
		
		return min;
		
	}
	
	
	public static int[] getGreaterThan(int[] arr, int limit)
	{
		
		return getGreaterThan(arr, limit, 0);
		
	}
	
	
	public static int[] getGreaterThan(int[] arr, int limit, int index)
	{
		
		if (index >= arr.length) return null;
		
		if (arr[index] > limit) return appendArrays(new int[] {arr[index] }, getGreaterThan(arr, limit, index + 1));
		return appendArrays(null, getGreaterThan(arr, limit, index + 1));
		
	}
	
	
	public static int[] getLessThan(int[] arr, int limit)
	{
		
		return getLessThan(arr, limit, 0);
		
	}
	
	
	public static int[] getLessThan(int[] arr, int limit, int index)
	{
		
		if (index >= arr.length) return null;
		
		if (arr[index] < limit) return appendArrays(new int[] {arr[index] }, getLessThan(arr, limit, index + 1));
		return appendArrays(null, getLessThan(arr, limit, index + 1));
		
	}
	
	
	public static int[] getRange(int[] arr, int lowerLimit, int upperLimit)
	{
		
		return getLessThan(getGreaterThan(arr, lowerLimit), upperLimit);
		
	}
	
	
	public static boolean isAnElement(int[] arr, int element)
	{
		
		if (notArray("isAnElement", arr)) return false;
		
		for (int x = 0; x < arr.length; x++) if (arr[x] == element) return true;
		return false;
		
	}
	
	
	public static int[] uniqueElements(int[] arr1, int[] arr2)
	{
		
		int[] arr = appendArrays(arr1, arr2);
		int[] returnArray = null;
		
		for (int x = 0; x < arr.length; x++)
		{
			
			boolean unique = true;
			
			for (int y = 0; y < arr.length; y++)
			{
				if (arr[x] == arr[y] && x != y)
				{
					unique = false;
					break;
				}
			}
			if (unique) returnArray = appendArrays(returnArray, new int[] {arr[x] });
		}
		
		return returnArray;
		
	}
	
	
	public static int[] commonElements(int[] arr1, int[] arr2)
	{
		
		int[] returnArray = null;
		
		for (int x : arr1)
		{
			for (int y : arr2)
			{
				if (x == y) returnArray = appendArrays(returnArray, new int[] {x });
			}
		}
		
		return uniqueElements(returnArray, null);
		
	}
	
	
	public static void showArray(int[] arr)
	{
		
		if (notArray("showArray", arr)) return;
		
		for (int x = 0; x < arr.length; x++)
		{
			if (x % 5 == 0 && x != 0) System.out.println("");
			if (x == arr.length - 1) System.out.println(arr[x]);
			else System.out.print(arr[x] + ", ");
		}
		
	}
	
	
	public static void main(String[] args)
	{
		
		showArray(commonElements(readArrayInfinite(new Scanner(System.in)), readArrayInfinite(new Scanner(System.in))));
		
		// showArray(readArrayBetter(new Scanner(System.in)));
		/*
		 * int[] arr1 = readArray(new Scanner(System.in)); int[] arr2 = readArray(new
		 * Scanner(System.in));
		 * 
		 * int[] arr3 = {1, 2, 3, 4, 0, 1624};
		 * 
		 * System.out.println("append:"); showArray(appendArrays(arr3,appendArrays(arr1,
		 * arr2))); System.out.println("getMax:");
		 * System.out.println(getMaximalElement(arr1)); System.out.println("getMin");
		 * System.out.println(getMinimalElement(arr1));
		 * System.out.println("greater: 5"); showArray(getGreaterThan(arr1, 5));
		 * System.out.println("smaller: 5"); showArray(getLessThan(arr1, 5));
		 * System.out.println("range 3<x<7:"); showArray(getRange(arr1, 3, 7));
		 * System.out.println("is 5 element?"); System.out.println(isAnElement(arr1,
		 * 5)); System.out.println("unique:"); showArray(uniqueElements(arr3,
		 * uniqueElements(arr1, arr2))); System.out.println("common:");
		 * showArray(commonElements(arr3, commonElements(arr1, arr2)));
		 */
	}
	
}
