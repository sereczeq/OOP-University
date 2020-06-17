package lab4;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Working set algorithm:
 * To make page replacing more efficient, a working set can be declared.
 * From all pages we choose ones that will be in used constantly, and ones that will be free.
 * When process needs to use more frames: instead of deleting currently used frame and replacing it with new needed one,
 * the algorithm gives free frame to the process, so it can do whatever it needs to do, and then frees some other frame, speeding up the process
 */
public class WorkingSet
{
	
	private int delta = 10;
	private LinkedList<Integer> faults = new LinkedList<Integer>();
	
	public WorkingSet(ArrayList<Process> processes, int amountOfFrames, int delta)
	{
		
		this.delta = delta;
		int numberOfPages = 0;
		for (Process curelle : processes)
		{
			numberOfPages += curelle.getNumberOfPages();
		}
		
		prepare(processes, numberOfPages / amountOfFrames);
		calculateFaults(processes, amountOfFrames, delta);
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
	
	
	private void calculateFaults(ArrayList<Process> processes, int amountOfFrames, int delta)
	{
		
		ArrayList<Integer> giveAway = new ArrayList<>();
		boolean goOn = true;
		while (goOn)
		{
			for (int i = 0; i < processes.size(); i++)
			{
				goOn = false;
				if (!regulateWorkingSet(processes, amountOfFrames, delta))
				{
					if (processes.get(i).LRU(-1)) goOn = true;
					more(giveAway, processes);
				}
				else
				{
					giveAway = less(processes, processes.get(i).getFramesSize(), i);
				}
			}
		}
		
	}
	
	
	private ArrayList<Integer> less(ArrayList<Process> processes, int frames, int jump)
	{
		
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < frames && i < processes.size(); i++)
		{
			if (i != jump) processes.get(i).addFrame();
			list.add(i);
		}
		return list;
		
	}
	
	
	private void more(ArrayList<Integer> list, ArrayList<Process> processes)
	{
		
		for (int i = 0; i < list.size(); i++)
		{
			processes.get(list.remove(0)).removeFrame();
		}
		
	}
	
	
	private boolean regulateWorkingSet(ArrayList<Process> processes, int amountOfFrames, int delta)
	{
		
		int sum = 0;
		for (int i = 0; i < processes.size(); i++)
		{
			sum += processes.get(i).getDivide(delta).size();
		}
		if (sum > amountOfFrames) return true;
		return false;
		
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
		
		return "WorkingSet with delta " + delta + ":\t" + faults.toString();
		
	}
	
}
