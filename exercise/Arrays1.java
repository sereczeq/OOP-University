package exercise;

public class Arrays1
{

	public static void main(String[] args)
	{
		
		int [] [] testArray = {{1,4,17},{7,12},{3,15,19,22}};
		showMax(testArray);
		showAboveAverage(testArray);
	}
	
	private static void showMax(int[][] arr)
	{
		if(arr == null || arr.length <= 0)
			{
				System.out.println("showMax: no data");
				return;
			}
		//TODO if arr[0] has no elements an error will occur, fix it
		int max = 0;
		boolean isMaxAssigned = false;
		for(int x=0; x<arr.length; x++)
		{
			if(arr[x].length == 0) continue;
			for(int y=0; y<arr[x].length; y++)
			{
				if(!isMaxAssigned)
				{
					 max = arr[x][y];
					 isMaxAssigned = true;
				}
				if(arr[x][y] > max) max = arr[x][y];
			}
		}
		System.out.println("Maximum number in array is: " + max);
	}
	
	private static double getAverageValue(int[][] arr)
	{
		if(arr == null || arr.length <= 0)
		{
			System.out.println("getAverageValue: no data");
			return 0;
		}
		
		int sum = 0, quantity = 0;
		
		for(int x=0; x<arr.length; x++)
		{
			for(int y=0; y<arr[x].length; y++)
			{
				sum += arr[x][y];
				quantity++;
			}
		}
		return (double) sum/quantity;
	}
	
	private static void showAboveAverage(int[][] arr)
	{
		if(arr == null || arr.length <= 0)
		{
			System.out.println("showAboveAverage: no data");
			return;
		}
		
		double average = getAverageValue(arr);
		
		System.out.print("values in array above average (" + average + "): ");
		for(int x=0; x<arr.length; x++)
		{
			for(int y=0; y<arr[x].length; y++)
			{
				if(arr[x][y] > average) System.out.print(arr[x][y] + ", ");
			}
		}
		System.out.println("");
	}
	
}
