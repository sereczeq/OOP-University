package lab7;

import lab.lab6Task1Enum.ProductType;
import lab.lab6Task2ItemType.Item;

public class HandHoldBag extends Bag
{
	
	protected boolean premiumQuality = true;
	
	public HandHoldBag()
	{
		
		super(5);
		
	}
	
	
	public String toString()
	{
		
		return (premiumQuality ? "Premium quality " : "") + "hand hold " + super.toString();
		
	}
	
	
	public boolean equals(Object other)
	{
		
		if (other == null || !(other instanceof HandHoldBag)) return false;
		HandHoldBag otherBag = (HandHoldBag) other;
		return premiumQuality == otherBag.premiumQuality && super.equals(other);
		
	}
	
	
	public static void main(String[] args)
	{
		
		HandHoldBag bag = new HandHoldBag();
		bag.putIn(new Item(ProductType.BREAD));
		System.out.println(bag);
		
	}
	
}
