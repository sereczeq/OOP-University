package lab10;

import lab.lab6Task1Enum.ProductType;
import lab.lab6Task2ItemType.Item;

public class ItemW extends Item
{
	
	protected float weight = 1;
	
	public ItemW(ProductType product, int quantity, String comment, float weight)
	{
		
		super(product, quantity, comment);
		this.weight = weight;
		
	}
	
	
	public String toString()
	{
		
		return super.toString() + " weight: " + weight;
		
	}
	
	
	public Boolean equals(ItemW other)
	{
		
		if (other == null) return false;
		if (type == other.type && quantity == other.quantity && weight == other.weight) return true;
		return false;
		
	}
	
}
