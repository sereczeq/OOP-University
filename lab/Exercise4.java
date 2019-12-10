package lab;

import java.util.Scanner;

public class Exercise4 {
	
	public static boolean notArray(String name, int[] arr)
	{
		
		if(arr == null || arr.length == 0)
		{
			System.out.println(name + ": no data detected");
			return true;
		}
		return false;
		
	}
	
	
	public static int[] readArray(Scanner scanner)
	{
		
		boolean initiated = false;
		int x = 0;
		int[] array = null;
		
		System.out.println("Enter an array of integers. First number is array's length.");
		while(scanner.hasNext())
		{
			if(scanner.hasNextInt())
			{
				int curr = scanner.nextInt();
				if(!initiated)
				{
					if(curr <= 0)
					{
						System.out.println("first number must be nonzero integer");
						continue;
					}
					
					array = new int[curr];
					initiated = true;
					continue;
				}
				array[x] = curr;
				x++;
				if(x >= array.length)
				{
					System.out.println("array created succesfully");
					return array;
				}
			}
			else if(scanner.next().equals("^Z"))
			{
				if(!initiated)
				{
					System.err.println("readArray: no first input");
					return null;
				}
				System.out.println("readArray: returning shortened array");
				int[] emergencyArray = new int[x];
				for(int k=0; k<x; k++) emergencyArray[k] = array[k];
				return emergencyArray;
			}
		}
		
		System.err.println("readArray: something went wrong");
		return null;
		
	}

	public static int[][] splitArray(int[] arr, int columns)
	{
		
		if(notArray("splitArray", arr)) return null;
		if(columns == 0)
		{
			System.out.println("splitArray: can't have 0 elements in a row");
			return null;
		}
		
		int rows = arr.length / columns;
		if(arr.length % columns != 0) rows++;
		
		int[][] array = new int[rows][columns];
		
		for(int x=0, where = 0; x<rows; x++)
		{
			for(int y=0; y<columns; y++)
			{
				array[x][y] = arr[where];
				where++;
				if(where >= arr.length) break;
			}
		}
		
		return array;
		
	}
	
	public static int[] joinArray(int[][] arr)
	{
		
		if(arr == null || arr.length == 0)
		{
			System.out.println("joinArray: nor data");
			return null;
		}
		
		int howLong = 0;
		
		for(int x=0; x<arr.length; x++)
		{
			for(int y=0; y<arr[x].length; y++)
			{
				howLong++;
			}
		}
		
		int[] array = new int[howLong];
		int where = 0;
		
		for(int x=0; x<arr.length; x++)
		{
			for(int y=0; y<arr[x].length; y++)
			{
				array[where] = arr[x][y];
				where++;
			}
		}
		
		return array;
		
	}

	public static int[] removeElement (int[] arr, int toRemove)
	{
		
		if(notArray("removeElement", arr)) return null;
		
		int howLong = arr.length;
		
		for(int x=0; x<arr.length; x++)
			if(arr[x] == toRemove) howLong--;
		
		int[] array = new int[howLong];
		
		for(int x=0, where=0; x<arr.length; x++)
		{
			if(arr[x] == toRemove) continue;
			array[where] = arr[x];
			where++;
		}
		
		return array;
	}
	
	public static int[] removeElements (int[] arr, int[] toRemove)
	{
		
		if(notArray("removeElements", arr)) return null;
		if(notArray("removeElements", toRemove)) return arr;
		
		int[] array = arr;
		
		for(int x=0; x<toRemove.length; x++)
		{
			array = removeElement(array, toRemove[x]);
		}
		
		return array;
	}

	public static void showArray(int[] arr, int columns)
	{
		
		if(notArray("showArray", arr)) return;
		
		//System.out.println("array values: ");
		for(int x=0; x<arr.length; x++)
		{
			if(x % columns == 0 && x != 0) System.out.println("");
			if(x == arr.length - 1) System.out.println(arr[x]);
			else System.out.print(arr[x] + ", ");
		}
		
	}

	public static void showSplitArray(int[][]arr)
	{
		
		if(arr == null || arr.length == 0)
		{
			System.out.println("showSplitArray: no data");
			return;
		}
		
		int columns = arr[0].length;
		
		//System.out.println("array values: ");
		for(int x=0; x<arr.length; x++)
		{
			for(int y=0; y<columns; y++)
			{
				if(x == arr.length - 1 && y == columns - 1)
				{
					System.out.print(arr[x][y]);
					break;
				}
				System.out.print(arr[x][y] + ", ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) 
	{
		int[] array = readArray(new Scanner(System.in));
		int[] toRemove = readArray(new Scanner(System.in));
		
		System.out.println("joined split");
		showArray(joinArray(splitArray(array, 4)), 5);
		System.out.println("removed");
		showArray(removeElements(array, toRemove), 100);
		System.out.println("split");
		showSplitArray(splitArray(array, 4));
		
	}
}
