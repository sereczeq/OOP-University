package different;

public class ArrayUpdated
{
	
	public static void printArray(int[] arr, int no)
	{
		
		if (arr == null || arr.length == 0)
		{
			System.out.println("printArray: no data to print");
			return;
		}
		
		for (int x = 0; x < arr.length; x++)
		{
			if (x == no)
			{
				System.out.println("");
				no *= 2;
			}
			System.out.print(arr[x] + " ");
		}
		
		System.out.println("");
		
	}
	
	
	public static int[] createArray(int size)
	{
		
		int[] array = new int[size];
		
		for (int x = 0, k = 11; x < array.length; x++, k += 2)
		{
			array[x] = k;
		}
		return array;
		
	}
	
	
	public static double findAvarage(int[] inArr)
	{
		
		if (inArr == null)
		{
			System.err.println("findAverage: empty array");
			return 0;
		}
		
		int sum = 0, quantity = 0;
		
		for (int x = 0; x < inArr.length; x++)
		{
			sum += inArr[x];
			quantity++;
		}
		if (quantity > 0) return (double) sum / quantity;
		else return 0;
		
	}
	
	
	public static int[] selectGreaterThan(int[] inArr, int limit)
	{
		
		if (inArr == null)
		{
			System.err.print("selectGreaterThan: empty array");
			return null;
		}
		
		int length = 0;
		
		for (int x = 0; x < inArr.length; x++)
		{
			if (inArr[x] > limit) length++;
		}
		
		int[] array = new int[length];
		
		for (int x = 0, k = 0; x < inArr.length; x++)
		{
			if (inArr[x] > limit)
			{
				array[k] = inArr[x];
				k++;
			}
		}
		return array;
		
	}
	
	
	public static void main(String[] args)
	{
		
		int[] testArray = {7, 12, 1, 0, 5, 8 };
		int[] emptyArray = { };
		int[] anotherArray;
		printArray(testArray, 3);
		printArray(emptyArray, 3);
		printArray(null, 3);
		anotherArray = createArray(8);
		printArray(anotherArray, 4);
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
