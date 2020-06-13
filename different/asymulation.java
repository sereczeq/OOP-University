package different;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class asymulation
{
	
	public static void main(String[] args)
	{
		
		// TODO Auto-generated method stub
		asymulation a = new asymulation();
		ArrayList<Double> list = a.addElements();
		System.out.println("Randomly chosen units of time:\n" + " " + list);
		System.out.println("Rotation alorithm-average time");
		System.out.println(a.rotation(list));
		System.out.println("Shortest Job first algorithm-average time:  ");
		System.out.println(a.shortestJobFirst(list));
		System.out.println("First come first served algorithm-average time:  ");
		System.out.println(a.firstComeFirstServe(list));
		
	}
	
	protected static Scanner scan = new Scanner(System.in);
	protected ArrayList<Double> list1 = new ArrayList<>();
	
	protected ArrayList<Double> addElements()
	{
		
		int x = 0;
		while (x < 10)
		{
			double randomElement = (int) (Math.random() * 38490) + 1;
			list1.add(randomElement);
			x++;
		}
		return list1;
		
	}
	
	
	protected double averageTime(ArrayList<Double> list)
	{
		
		double sum = 0;
		double average = 0;
		
		int n = 1;
		for (int x = 0; x < list.size() - 1; x++)
		{
			sum = sum + (list.get(x) * (list.size() - n));
			n++;
		}
		average = (double) sum / list.size();
		return average;
		
	}
	
	
	protected double firstComeFirstServe(ArrayList<Double> list)
	{
		
		Collections.shuffle(list);
		return averageTime(list);
		
	}
	
	
	protected double shortestJobFirst(ArrayList<Double> list)
	{
		
		Collections.sort(list);
		System.out.println(list);
		return averageTime(list);
		
	}
	
	
	protected double rotation(ArrayList<Double> list)
	{
		
		double randomTime = (int) (Math.random() * 20) + 1;
		System.out.println("(Randomly chosen time difference for ROT algorithm: " + randomTime + ")");
		return (rotate(randomTime));
		
	}
	
	
	protected double rotate(double timeDifference)
	{
		
		double averageTime = 0;
		double sumOfTime = 0;
		double average = 0;
		double element;
		double spare = 0;
		int x = 0;
		while (x < list1.size())
		{
			element = list1.get(x);
			while (element > 0)
			{
				element = element - timeDifference;
				sumOfTime = sumOfTime + element;
				averageTime = averageTime + timeDifference;
				if (element - timeDifference < 0)
				{
					sumOfTime = sumOfTime + element;
					spare = spare + (timeDifference - element);
				}
				
			}
			x++;
		}
		average = averageTime / list1.size();
		return average;
		
	}
	
}
