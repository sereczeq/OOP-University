package lab7;

import lab.lab6Task1Enum.ProductType;
import lab.lab6Task2ItemType.Item;

public class BagTester
{
	
	public static void main(String[] args)
	{
		
		Bag bag = new Bag(12);
		HandHoldBag handBag = new HandHoldBag();
		ShoppingBag shoppingBag = new ShoppingBag();
		
		Item bread = new Item(ProductType.BREAD, 5, "bread");
		Item tea = new Item(ProductType.TEA, 2, "tea");
		
		Bag[] array = {bag, handBag, shoppingBag };
		
		for (Bag x : array)
		{
			x.putIn(bread);
			System.out.println("after adding bread: " + x);
			x.putIn(tea);
			System.out.println("after adding tea: " + x);
			x.putIn(tea);
			System.out.println("after adding another tea: " + x);
			x.putIn(null);
			System.out.println("after adding null: " + x);
			x.remove(ProductType.TEA, 5);
			System.out.println("after removing 5 teas: " + x);
			x.remove(null, 1);
			System.out.println("after removing 1 null: " + x);
			x.remove(ProductType.TEA, 2);
			System.out.println("after removing 2 teas: " + x);
			x.removeAllItems();
			System.out.println("after removing all items: " + x);
			x.putIn(new Item(ProductType.MILK, 100, "milk"));
			for (int y = 0; y < 100; y++)
			{
				x.putIn(new Item(ProductType.MILK, 1, "milk"));
			}
			System.out.println("after adding 100 milks one by one: " + x);
			
			System.out.println("\n\n");
		}
		
		HandHoldBag handBag2 = new HandHoldBag();
		ShoppingBag shoppingBag2 = new ShoppingBag(false);
		Bag[] array2 = {handBag2, shoppingBag2 };
		
		for (Bag x : array2)
		{
			x.putIn(bread);
			x.putIn(tea);
			x.putIn(tea);
			x.putIn(null);
			x.remove(ProductType.TEA, 5);
			x.remove(null, 1);
			x.remove(ProductType.TEA, 2);
			x.removeAllItems();
			x.putIn(new Item(ProductType.MILK, 100, "milk"));
			for (int y = 0; y < 100; y++)
			{
				x.putIn(new Item(ProductType.MILK, 1, "milk"));
			}
			
		}
		
		System.out.println(handBag.equals(handBag2));
		System.out.println(shoppingBag.equals(shoppingBag2));
		
	}
	
}
