package different;

import java.math.BigInteger;
import java.util.LinkedList;

public class HashTable
{
	
	LinkedList arr[];
	private final static int defaultInitSize = 8;
	private final static double defaultMaxLoadFactor = 0.7;
	private int size2;
	private final double maxLoadFactor;
	private int numElements;
	
	public HashTable()
	{
		
		this(defaultInitSize);
		
	}
	
	
	public HashTable(int size)
	{
		
		this(size, defaultMaxLoadFactor);
		
	}
	
	
	public HashTable(int initCapacity, double maxLF)
	{
		
		this.size2 = initCapacity;
		this.maxLoadFactor = maxLF;
		arr = new LinkedList[size2];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = new LinkedList<>();
		}
		numElements = 1;
		
	}
	
	
	public boolean add(Object elem)
	{
		
		// TODO
		BigInteger hc = ((Document) elem).hashcode();
		int hv = hashValue(hc, size2);
		if (arr[hv].contains(elem))
		{
			return false;
		}
		if (((double) numElements) / size2 < maxLoadFactor)
		{
			arr[hv].add(elem);
		}
		else
		{
			doubleArray();
			hv = hashValue(hc, size2);
			arr[hv].add(elem);
		}
		numElements++;
		return true;
		
	}
	
	
	private void doubleArray()
	{
		
		// TODO Auto-generated method stub
		LinkedList arr2[] = new LinkedList[size2 * 2];
		for (int i = 0; i < arr2.length; i++)
		{
			arr2[i] = new LinkedList<>();
		}
		int i, j, idx;
		Object elem;
		for (i = 0; i < size2; i++)
		{
			for (j = 0; j < arr[i].size(); j++)
			{
				elem = arr[i].get(j);
				idx = hashValue(((Document) elem).hashcode(), 2 * size2);
				arr2[idx].add(elem);
			}
		}
		arr = null;
		size2 = size2 * 2;
		arr = new LinkedList[size2];
		for (i = 0; i < arr.length; i++)
		{
			arr[i] = new LinkedList<>();
		}
		for (i = 0; i < size2; i++)
		{
			for (j = 0; j < arr2[i].size(); j++)
			{
				elem = arr2[i].get(j);
				arr[i].add(elem);
			}
		}
		arr2 = null;
		
	}
	
	
	private int hashValue(BigInteger hc, int size2)
	{
		
		// TODO Auto-generated method stub
		return hc.mod(BigInteger.valueOf(size2)).intValue();
		
	}
	
	
	@Override
	public String toString()
	{
		
		// TODO
		String res = "";
		int i, j;
		for (i = 0; i < arr.length; i++)
		{
			res += (i + ":");
			if (arr[i].size() == 0)
			{
				res += "\n";
			}
			else
			{
				res += " ";
				res += ((IWithName) arr[i].get(0)).getName();
				for (j = 1; j < arr[i].size(); j++)
				{
					res += ", ";
					res += ((IWithName) arr[i].get(j)).getName();
				}
				res += "\n";
			}
		}
		return res;
		
	}
	
	
	public Object get(Object toFind)
	{
		
		// TODO
		int hc = hashValue(((Document) toFind).hashcode(), size2);
		if (!(arr[hc].contains(toFind)))
		{
			return null;
		}
		for (int i = 0; i < arr[hc].size(); i++)
		{
			if (arr[hc].get(i).equals(toFind))
			{
				return arr[hc].get(i);
			}
		}
		return null;
		
	}
	
}
