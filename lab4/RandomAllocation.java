package lab4;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Equal algorithm distributes frames evenly to every process, no matter how many frames it needs
 * This is not ideal, because sometimes it may even leave some unused frames, if processes don't need this many,
 * while other processes might not get enough frames
 */
public class RandomAllocation // can't be named random, because of java internal class
{
	
	private LinkedList<Integer> faults = new LinkedList<Integer>();
	
	public RandomAllocation(ArrayList<Process> processes, int amountOfFrames)
	{
		
		int numberOfPages = 0;
		for (Process curelle : processes)
		{
			numberOfPages += curelle.getNumberOfPages();
		}
		
		setAllFrames(processes, numberOfPages / amountOfFrames);
		calculateFaults(processes);
		faults = result(processes);
		
	}
	
	
	private void setAllFrames(ArrayList<Process> processes, int amountOfFramesGiven)
	{
		
		Process current;
		for (int i = 0; i < processes.size(); i++)
		{
			current = processes.get(i);
			current.setFrames(amountOfFramesGiven);
		}
		
	}
	
	
	private void calculateFaults(ArrayList<Process> processes)
	{
		
		boolean proceed = true;
		while (proceed)
		{
			for (int i = 0; i < processes.size(); i++)
			{
				proceed = false;
				if (processes.get(i).LRU(-1)) proceed = true;
			}
		}
		
	}
	
	
	private LinkedList<Integer> result(ArrayList<Process> processes)
	{
		
		LinkedList<Integer> results = new LinkedList<Integer>();
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
		
		return "Radnom:\t" + faults.toString();
		
	}
	
}
