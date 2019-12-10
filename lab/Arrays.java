package lab;

public class Arrays {

	//from lab4
	/*
	
	
	public static int[] uniqueElementsBetter(int[] arr1, int[] arr2)
	{
		
		//checking how many unique elements so I can create an array long enough;
		int howLong = 0;
		
		for(int x=0; x<arr1.length; x++)
		{
			//checking if value is repeating in the same array
			boolean wasUsed = false;
			
			for(int y=0; y<x; y++)
			{
				if(arr1[x] == arr1[y])
				{
					wasUsed = true;
					break;
				}
			}
			if(wasUsed) continue;
			
			//checking if value is repeating in other array
			boolean unique = true;
			
			for(int y=0; y<arr2.length; y++)
			{
				if(arr1[x] == arr2[y])
				{
					unique = false;
					break;
				}
			}
			if(unique) howLong++;
		}
		
		//and the other way around
		for(int x=0; x<arr2.length; x++)
		{
			boolean wasUsed = false;
			
			for(int y=0; y<x; y++)
			{
				if(arr2[x] == arr2[y])
				{
					wasUsed = true;
					break;
				}
			}
			if(wasUsed) continue;
			
			boolean unique = true;
			
			for(int y=0; y<arr1.length; y++)
			{
				if(arr2[x] == arr1[y])
				{
					unique = false;
					break;
				}
			}
			if(unique) howLong++;
		}
		
		//putting values in array
		//
		//
		
		int[] array = new int[howLong];
		int where = 0;
		
		for(int x=0; x<arr1.length; x++)
		{
			boolean wasUsed = false;
			
			for(int y=0; y<x; y++)
			{
				if(arr1[x] == arr1[y])
				{
					wasUsed = true;
					break;
				}
			}
			if(wasUsed) continue;
			
			boolean unique = true;
			
			for(int y=0; y<arr2.length; y++)
			{
				if(arr1[x] == arr2[y])
				{
					unique = false;
					break;
				}
			}
			if(unique)
			{
				array[where] = arr1[x];
				where++;
			}
		}
		
		for(int x=0; x<arr2.length; x++)
		{
			boolean wasUsed = false;
			
			for(int y=0; y<x; y++)
			{
				if(arr2[x] == arr2[y])
				{
					wasUsed = true;
					break;
				}
			}
			if(wasUsed) continue;
			
			boolean unique = true;
			
			for(int y=0; y<arr1.length; y++)
			{
				if(arr2[x] == arr1[y])
				{
					unique = false;
					break;
				}
			}
			if(unique)
			{
				array[where] = arr2[x];
				where++;
			}
		}
		
		return array;
		
	}

	
	*/
	public static void printArray(int[] arr, int no) {
		if(arr.length <= 0)
		{
			System.out.println("No data to print");
			return;
		}
		
		for(int x=0; x<arr.length; x++)
		{
			if(x == no)
			{
				System.out.println("");
				no *= 2;
			}
			System.out.print(arr[x] + " ");
		}
		
		System.out.println("");
	}
	public static void createArray(int size) {
		int[] array = new int[size];
		for(int x=0, k=11; x<array.length; x++, k+=2)
		{
			array[x] = k;
		}
		printArray(array, 3);
	}
	public static double findAvarage(int[] inArr) {
		int sum=0, quantity=0;
		for(int x=0; x<inArr.length; x++)
		{
			sum += inArr[x];
			quantity++;
		}
		if(quantity>0) return (double) sum/quantity;
		else
		{
			//System.out.print("findAvarage: no numbers detected. ");
			return 0;
		}
	}
	public static int[] selectGreaterThan(int[] inArr, int limit) {
		int length = 0;
		for(int x=0; x<inArr.length; x++)
		{
			if(inArr[x] > limit) length++;
		}
		int[] array = new int[length];
		for(int x=0, k=0; x<inArr.length; x++)
		{
			if(inArr[x]>limit)
			{
				array[k] = inArr[x];
				k++;
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
		int[] testArray = {7, 12, 1, 0, 5, 8};
		int[] emptyArray = {};
		int[] anotherArray;
		printArray(testArray, 3);
		printArray(emptyArray, 3);
		createArray(8);
		System.out.println("test avarage: " + findAvarage(testArray));
		System.out.println("empty avarage: " + findAvarage(emptyArray));
		anotherArray = selectGreaterThan(testArray, 7);
		System.out.print(">7 ");
		printArray(anotherArray, 5);
		anotherArray = selectGreaterThan(testArray, 77);
		System.out.print(">77 ");
		printArray(anotherArray, 5);
	}
}
