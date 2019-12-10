package exercise;

public class List5Iterations {


	public static boolean notArray(int[] array)
	{

		if(array == null || array.length == 0) return true;
		return false;

	}

	public static int[] newArray(int[] array)
	{

		if(notArray(array)) return null;

		int[] newArray = new int[array.length-1];

		for(int x=1; x < array.length; x++)
		{
			newArray[x-1] = array[x];
		}

		return newArray;

	}


	public static int maxValueI(int[] array)
	{

		if(notArray(array)) return Integer.MIN_VALUE;

		int max = Integer.MIN_VALUE;

		for(int x = 0; x < array.length; x++)
		{
			if(array[x] > max) max = array[x];
		}

		return max;
	}

	public static int max(int[] array, int index)
	{
		
		if(index >= array.length ) return 0;
		
		int max = max(array, index + 1);
		
		if(max > array[index]) return max;
		else return array[index];
		
		
	}
		
	public static int maxValueR(int[] array)
	{

		if(notArray(array)) return Integer.MIN_VALUE;

		int max = maxValueR(newArray(array));

		if(array[0] > max) return array[0];
		return max;

	}

	public static int[] selectGreaterThanAverage(int[] array)
	{

		if(notArray(array)) return null;

		int sum = 0;

		for(int x = 0; x < array.length; x++)
		{
			sum += array[x];
		}

		double average = (double) sum / array.length;
		int howMany = 0;

		for(int x = 0; x < array.length; x++)
		{
			if(array[x] > average) howMany++;
		}

		int[] averageArray = new int[howMany];
		int where = 0;

		for(int x = 0; x < array.length; x++)
		{
			if(array[x] > average)
			{
				averageArray[where] = array[x];
				where++;
			}
		}

		return averageArray;
	}

	public static int sumOfRangeI(int[] array, int upperLimit, int lowerLimit)
	{

		if(notArray(array)) return 0;

		int sum = 0;

		for(int x = 0; x < array.length; x++)
		{
			if(lowerLimit < array[x] && array[x] < upperLimit) sum += array[x];
		}

		return sum;

	}

	public static int sumOfRangeR(int[] array, int upperLimit, int lowerLimit, int number)
	{

		if(notArray(array)) return 0;

		if(number >= array.length) return 0;
		
		int check = 0;

		if(lowerLimit < array[number] && array[number] < upperLimit) check = array[number];

		return sumOfRangeR(array, upperLimit, lowerLimit, number + 1) + check;

	}

	public static int fibonacciI(int number)
	{

		if(number < 0) return Integer.MIN_VALUE;

		int prev = 1;
		int fib = 0;

		for(int x = 0; x < number; x++)
		{
			int a = fib;
			fib += prev;
			prev = a;
		}

		return fib;
	}

	public static int fibonacciR(int number)
	{

		if(number < 0) return Integer.MIN_VALUE;
		if(number == 0) return 0;
		if(number == 1) return 1;
		return fibonacciR(number - 1) + fibonacciR(number - 2);

	}

	public static void main(String[] args) 
	{

		int[] array = {1, 2, 3, 1};
		System.out.println(max(array, 0));
		System.out.println(fibonacciR(3));
		System.out.println(sumOfRangeR(array, 10, -1, 0));

	}

}
