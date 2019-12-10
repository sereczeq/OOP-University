package lab7;

import lab.lab6Task2ItemType.Item;

public class ShoppingBag extends Bag
{
	
	protected boolean extremelyRobust = true;
	
	public ShoppingBag()
	{
		
		super(30);
		
	}
	
	
	public ShoppingBag(boolean robustness)
	{
		
		super(30);
		extremelyRobust = robustness;
		
	}
	
	
	public double getTotalPrice()
	{
		
		double sum = 0;
		for (Item x : items)
		{
			sum += x.getTotalPrice();
		}
		return sum;
		
	}
	
	
	public String toString()
	{
		
		return (extremelyRobust ? "Extremely robust " : "") + "shopping " + super.toString();
		
	}
	
	
	public boolean equals(Object other)
	{
		
		if (other == null || !(other instanceof ShoppingBag)) return false;
		ShoppingBag otherBag = (ShoppingBag) other;
		return extremelyRobust == otherBag.extremelyRobust && super.equals(other);
		
	}
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
	}
	
}
