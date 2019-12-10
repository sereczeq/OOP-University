package exercise;
import java.util.Scanner;

public class SumOfAllSmallerThanN 
{
	public static void main(String[] args) 
	{
		
		//sum of natural numbers < N
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter a number");
		while (scanner.hasNext())
		{
			if(scanner.hasNextInt()) break;
			System.out.println(scanner.next() + " is not an integer, enter an integer");
		}
		
		if(!scanner.hasNextInt())
		{
			System.out.println("no numbers entered");
			System.exit(1);
		}
		
		int N = scanner.nextInt();
		scanner.close();
		int sum = 0;
		
		for(int x=1; x<N; x++) sum += x;
		System.out.println("sum of natural numbers smaller than " + N + " is: " + sum);
	}
	
}
