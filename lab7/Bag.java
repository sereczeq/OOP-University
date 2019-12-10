package lab7;

import lab.lab6Task1Enum.ProductType;
import lab.lab6Task2ItemType.Item;

public class Bag
{
	
	protected int howMany = 0;
	protected int capacity;
	protected Item[] items = { };
	
	public Bag(int capacity)
	{
		
		this.capacity = capacity > 0 ? capacity : 1;
		
	}
	
	
	public void removeAllItems()
	{
		
		items = new Item[] { };
		howMany = 0;
		
	}
	
	
	public Item isItThere(Item item)
	{
		
		for (int x = 0; x < items.length; x++)
		{
			if (items[x].getType() == item.getType())
			{
				Item newItem = new Item(item.getType(), item.getQuantity() + items[x].getQuantity(),
						items[x].getComment());
				remove(item.getType());
				return newItem;
			}
		}
		return item;
		
	}
	
	
	public boolean putIn(Item item)
	{
		
		if (item == null) return false;
		
		if (item.getQuantity() + howMany > capacity) return false;
		
		item = isItThere(item);
		
		Item[] newList = new Item[items.length + 1];
		
		for (int x = 0; x < items.length; x++)
		{
			newList[x] = items[x];
		}
		newList[newList.length - 1] = item;
		
		howMany += item.getQuantity();
		
		items = newList;
		
		return true;
		
	}
	
	
	public boolean remove(ProductType product)
	{
		
		if (product == null) return false;
		
		for (int x = 0; x < items.length; x++)
		{
			if (items[x].getType() == product)
			{
				Item[] newList = new Item[items.length - 1];
				for (int y = 0; y < newList.length; y++)
				{
					newList[y] = items[y >= x ? y + 1 : y];
				}
				howMany -= items[x].getQuantity();
				items = newList;
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public boolean remove(ProductType product, int quantity)
	{
		
		if (product == null) return false;
		
		for (int x = 0; x < items.length; x++)
		{
			if (items[x].getType() == product)
			{
				if (items[x].getQuantity() - quantity <= 0) return remove(product);
				items[x] = new Item(product, items[x].getQuantity() - quantity, items[x].getComment());
				howMany -= quantity;
				return true;
			}
		}
		return false;
		
	}
	
	
	public String toString()
	{
		
		String string = "";
		
		if (items.length == 0) string = "nothing";
		
		for (Item x : items)
		{
			string += x.getType() + " in quantity of: " + x.getQuantity() + "\n";
		}
		
		return "Bag with capacity of " + capacity + " contains: \n" + string;
		
	}
	
	
	public boolean equals(Object other)
	{
		
		if (other == this) return true;
		if (!(other instanceof Bag)) return false;
		
		Bag otherBag = (Bag) other;
		
		if (capacity != otherBag.capacity) return false;
		if (items == null || otherBag.items == null) return false;
		if (items.length != otherBag.items.length) return false;
		for (int x = 0; x < items.length; x++)
		{
			if (!items[x].equals(otherBag.items[x])) return false;
		}
		return true;
		
	}
	
	
	public static void main(String[] args)
	{
		
		Bag bag = new Bag(5);
		Item bread = new Item(ProductType.BREAD, 3, "bread");
		Item tea = new Item(ProductType.TEA, 1, "tea");
		Item milk = new Item(ProductType.MILK, 4, "milk");
		bag.putIn(bread);
		System.out.println(bag);
		bag.putIn(tea);
		bag.putIn(milk);
		System.out.println(bag);
		bag.remove(ProductType.BREAD);
		bag.putIn(milk);
		System.out.println(bag);
		bag.remove(ProductType.MILK, 3);
		System.out.println(bag);
		// bag.removeAllItems();
		System.out.println(bag);
		
		Bag bag2 = new Bag(5);
		Item bread2 = new Item(ProductType.BREAD, 3, "bread");
		Item tea2 = new Item(ProductType.TEA, 1, "tea");
		Item milk2 = new Item(ProductType.MILK, 4, "milk");
		bag2.putIn(bread2);
		System.out.println(bag2);
		bag2.putIn(tea2);
		bag2.putIn(milk2);
		System.out.println(bag2);
		bag2.remove(ProductType.BREAD);
		bag2.putIn(milk2);
		System.out.println(bag2);
		bag2.remove(ProductType.MILK, 3);
		System.out.println(bag2);
		// bag2.removeAllItems();
		System.out.println(bag2);
		
		System.out.println(bag.equals(bag2));
		
	}
	
}
