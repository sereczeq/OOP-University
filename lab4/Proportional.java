package lab4;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Proportional algorithm distributes frames proportionally to process' needs
 * Thanks to this smaller processes aren't taking too many frames
 */
public class Proportional
{
	
	private LinkedList<Integer> faults = new LinkedList<Integer>();
	
	public Proportional(ArrayList<Process> processes, int amountOfFrames)
	{
		
		int numberOfPages = 0;
		for (Process curelle : processes)
		{
			numberOfPages += curelle.getNumberOfPages();
		}
		
		prepareFrames(processes, numberOfPages, amountOfFrames);
		calculateFaults(processes);
		faults = results(processes);
		
	}
	
	
	public void prepareFrames(ArrayList<Process> processes, int numberOfAllPages, int framesAmount)
	{
		
		for (int i = 0; i < processes.size(); i++)
		{
			processes.get(i).setFrames(processes.get(i).getNumberOfPages() * framesAmount / numberOfAllPages);
		}
		
	}
	
	
	public void calculateFaults(ArrayList<Process> processes)
	{
		
		boolean curelle = true;
		while (curelle)
		{
			for (int i = 0; i < processes.size(); i++)
			{
				curelle = false;
				if (processes.get(i).LRU(-1)) curelle = true;
			}
		}
		
	}
	
	
	public LinkedList<Integer> results(ArrayList<Process> processes)
	{
		
		LinkedList<Integer> results = new LinkedList<>();
		for (int i = 0; i < processes.size(); i++)
		{
			results.add(processes.get(i).getNumberOfReplacements());
			processes.get(i).reset();
		}
		return results;
		
	}
	
	
	@Override
	public String toString()
	{
		
		return "Proportional:\t" + faults.toString();
		
	}
	
}
