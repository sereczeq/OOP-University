package lab4;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Page Fault Frequency algorithm:
 * This algorithm takes the amount of faults into consideration.
 * If process is generating a lot of faults, more frames are given to it, in order to reduce the amount of faults
 * If process is generating very few faults, frames are taken from it, to give them to more needing processes
 * Important to note: this algorithm does not prevent faults; it keeps them in control, to ensure no trashing will happen
 */

public class PageFaultFrequency
{
	
	private LinkedList<Integer> faults = new LinkedList<Integer>();
	private int rate = 10;
	
	public PageFaultFrequency(ArrayList<Process> processes, int amountOfFrames, int rate)
	{
		
		this.rate = rate;
		
		int numberOfPages = 0;
		for (Process curelle : processes)
		{
			numberOfPages += curelle.getNumberOfPages();
		}
		
		prepare(processes, numberOfPages / amountOfFrames);
		calculateFaults(processes, rate);
		faults = results(processes);
		
	}
	
	
	private void prepare(ArrayList<Process> processes, int amountOfFramesGiven)
	{
		
		Process current;
		for (int i = 0; i < processes.size(); i++)
		{
			current = processes.get(i);
			current.setFrames(amountOfFramesGiven);
		}
		
	}
	
	
	private void calculateFaults(ArrayList<Process> processes, int rate)
	{
		
		boolean proceed = true;
		while (proceed)
		{
			for (int i = 0; i < processes.size(); i++)
			{
				proceed = false;
				if (processes.get(i).LRU(rate)) proceed = true;
			}
		}
		
	}
	
	
	private LinkedList<Integer> results(ArrayList<Process> processes)
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
		
		return "Page Fault Frequency with delta " + rate + ":\t" + faults.toString();
		
	}
	
}
