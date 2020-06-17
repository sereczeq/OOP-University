package lab4;

import java.util.ArrayList;
import java.util.Random;

public class Main
{
	
	public static void main(String[] args)
	{
		
		int howManyProcesses = 30;
		int howManyFrames = 400;
		int howManyPages = 90;
		int range = 50;
		int controlRatePer10Pages = 5;
		int localityDelta = 30;
		allocate(howManyProcesses, howManyFrames, howManyPages, range, controlRatePer10Pages, localityDelta);
		
	}
	
	
	private static void allocate(int numberOfProcesses, int nummberOfFrames, int numberOfPages, int range,
			int controlRatePer10Pages, int localityDelta)
	{
		
		ArrayList<Process> processes = generateProcesses(numberOfProcesses, numberOfPages, range);
		
		RandomAllocation randomAllocation = new RandomAllocation(processes, nummberOfFrames);
		Proportional proportional = new Proportional(processes, nummberOfFrames);
		PageFaultFrequency pageFaultFrequency = new PageFaultFrequency(processes, nummberOfFrames,
				controlRatePer10Pages);
		WorkingSet workingSet = new WorkingSet(processes, nummberOfFrames, localityDelta);
		
		for (Process p : processes) System.out.println(p + ", ");
		
		System.out.println("\n\nAmount of faults for each process, for different algorithms\n");
		System.out.println(proportional);
		System.out.println(randomAllocation);
		System.out.println(pageFaultFrequency);
		System.out.println(workingSet);
		
	}
	
	
	private static ArrayList<Process> generateProcesses(int amountOfProcesses, int amountOfPages, int range)
	{
		
		ArrayList<Process> processes = new ArrayList<>();
		Random random = new Random();
		
		for (int i = 0; i < amountOfProcesses; i++)
		{
			processes.add(new Process(generatePages(amountOfPages + random.nextInt(amountOfPages), range)));
		}
		return processes;
		
	}
	
	
	private static ArrayList<Integer> generatePages(int amount, int range)
	{
		
		ArrayList<Integer> list = new ArrayList<>();
		Random random = new Random();
		
		for (int i = 0; i < amount; i++)
		{
			list.add(random.nextInt(range));
		}
		return list;
		
	}
	
}
