package lab;

import java.math.BigInteger;

public class Lab5Iteration
{
	
	public static BigInteger factorialI(BigInteger number)
	{
		
		if (number == null) return BigInteger.ZERO;
		
		if (number.compareTo(BigInteger.ZERO) == -1 || number.compareTo(new BigInteger("3000")) == 1)
			return BigInteger.ZERO;
		
		BigInteger factorial = new BigInteger("1");
		
		for (BigInteger x = new BigInteger("1"); x.subtract(BigInteger.ONE).compareTo(number) == -1; x = x
				.add(new BigInteger("1")))
		{
			factorial = factorial.multiply(x);
		}
		
		return factorial;
		
	}
	
	
	public static BigInteger factorialR(BigInteger number)
	{
		
		if (number == null) return BigInteger.ZERO;
		
		if (number.compareTo(BigInteger.ZERO) == -1 || number.compareTo(new BigInteger("3000")) == 1)
			return BigInteger.ZERO;
		
		if (number.compareTo(BigInteger.ZERO) == 0) return BigInteger.ONE;
		return factorialR(number.subtract(BigInteger.ONE)).multiply(number);
		
	}
	
	
	public static BigInteger gCDI(BigInteger number1, BigInteger number2)
	{
		
		if (number1 == null || number2 == null) return BigInteger.ZERO;
		
		if (number1.compareTo(BigInteger.ZERO) == -1 || number2.compareTo(BigInteger.ZERO) == -1)
			return BigInteger.ZERO;
		
		if (number1.compareTo(number2) == -1)
		{
			BigInteger x = number2;
			number2 = number1;
			number1 = x;
		}
		
		while (!(number2.compareTo(BigInteger.ZERO) == 0))
		{
			BigInteger x = number2;
			number2 = number1.mod(number2);
			number1 = x;
		}
		
		return number1;
		
	}
	
	
	public static BigInteger gCDR(BigInteger number1, BigInteger number2)
	{
		
		if (number1 == null || number2 == null) return BigInteger.ZERO;
		
		if (number1.compareTo(BigInteger.ZERO) == -1 || number2.compareTo(BigInteger.ZERO) == -1)
			return BigInteger.ZERO;
		
		if (number1.compareTo(number2) == -1)
		{
			BigInteger x = number2;
			number2 = number1;
			number1 = x;
		}
		
		if (number1.compareTo(BigInteger.ZERO) == 0) return BigInteger.ZERO;
		
		if (number2.compareTo(BigInteger.ZERO) == 0) return number1;
		else return gCDR(number2, number1.mod(number2));
		
	}
	
	
	public static boolean notArray(BigInteger[] array)
	{
		
		if (array == null || array.length == 0) return true;
		return false;
		
	}
	
	
	public static BigInteger[] newArray(BigInteger[] array)
	{
		
		if (notArray(array)) return new BigInteger[] { };
		
		BigInteger[] newArray = new BigInteger[array.length - 1];
		
		for (int x = 1; x < array.length; x++)
		{
			newArray[x - 1] = array[x];
		}
		
		return newArray;
		
	}
	
	
	public static int numberOfPositivesI(BigInteger[] array)
	{
		
		if (notArray(array)) return 0;
		
		int howMany = 0;
		
		for (BigInteger x : array)
		{
			if (x != null && x.compareTo(BigInteger.ZERO) == 1) howMany++;
		}
		
		return howMany;
		
	}
	
	
	public static int numberOfPositivesR(BigInteger[] array)
	{
		
		if (notArray(array)) return 0;
		
		int check = 0;
		
		if (array[0] != null && array[0].compareTo(BigInteger.ZERO) == 1) check = 1;
		
		return numberOfPositivesR(newArray(array)) + check;
		
	}
	
	
	public static BigInteger sumOfPositivesI(BigInteger[] array)
	{
		
		if (notArray(array)) return BigInteger.ZERO;
		
		BigInteger sum = BigInteger.ZERO;
		
		for (BigInteger x : array)
		{
			if (x != null && x.compareTo(BigInteger.ZERO) == 1) sum = sum.add(x);
		}
		
		return sum;
		
	}
	
	
	public static BigInteger sumOfPositivesR(BigInteger[] array)
	{
		
		if (notArray(array)) return BigInteger.ZERO;
		
		BigInteger check = BigInteger.ZERO;
		if (array[0] != null && array[0].compareTo(BigInteger.ZERO) == 1) check = array[0];
		
		return sumOfPositivesR(newArray(array)).add(check);
		
	}
	
	
	public static void main(String[] args)
	{
		
		BigInteger[] arr = {new BigInteger("1234"), BigInteger.ONE, new BigInteger("12345"), new BigInteger("-12345"),
				null, new BigInteger("2382") };
		
		BigInteger[][] array = {arr, { }, null, {new BigInteger("-1"), new BigInteger("0"), new BigInteger("123") } };
		
		for (BigInteger x : arr)
		{
			System.out.println("Iterative factorial of " + x + " is: " + factorialI(x));
			System.out.println("Recursive factorial of " + x + " is: " + factorialR(x));
			System.out.println(
					"Iterative greatest common divisor of " + x + " and 10 is: " + gCDI(x, new BigInteger("10")));
			System.out.println(
					"Recursive greatest common divisor of " + x + " and 10 is: " + gCDR(x, new BigInteger("10")));
			System.out.println(
					"Iterative greatest common divisor of " + x + " and 0 is: " + gCDI(x, new BigInteger("0")));
			System.out.println(
					"Recursive greatest common divisor of " + x + " and 0 is: " + gCDR(x, new BigInteger("0")));
			System.out.println("\n\n");
		}
		
		for (BigInteger[] x : array)
		{
			System.out.println("Iterative number of positive values in array is: " + numberOfPositivesI(x));
			System.out.println("Recursive number of positive values in array is: " + numberOfPositivesR(x));
			System.out.println("Iterative sum of positive values in array is: " + sumOfPositivesI(x));
			System.out.println("Recursive sum of positive values in array is: " + sumOfPositivesR(x));
			System.out.println("\n\n");
			
		}
		
	}
	
}
