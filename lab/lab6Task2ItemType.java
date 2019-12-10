package lab;

import lab.lab6Task1Enum.ProductType;

public class lab6Task2ItemType
{
	
	public static class Item
	{
		
		protected ProductType type;
		protected int quantity = 1;
		protected String comment = "noComment";
		
		public Item(ProductType product, int quantity, String comment)
		{
			
			type = product;
			if (quantity > 0) this.quantity = quantity;
			if (comment != null) this.comment = comment;
			else this.comment = "noComment";
			
		}
		
		
		public Item(ProductType product)
		{
			
			type = product;
			
		}
		
		// public Item(ProductType)
		
		
		public void addItems(int quantity)
		{
			
			if (quantity > 0) this.quantity += quantity;
			
		}
		
		
		public boolean remove(int number)
		{
			
			if (type != null && quantity - number >= 0)
			{
				quantity -= number;
				return true;
			}
			return false;
			
		}
		
		
		public int getQuantity()
		{
			
			return quantity;
			
		}
		
		
		public void changeQuantity(int quantity)
		{
			
			if (quantity > 0);
			this.quantity = quantity;
			
		}
		
		
		public double getTotalPrice()
		{
			
			if (type == null) return 0;
			return type.getPrice() * getQuantity();
			
		}
		
		/*
		 * public double getTotalWeight() {
		 * 
		 * }
		 */
		
		
		public ProductType getType()
		{
			
			return type;
			
		}
		
		
		public String getComment()
		{
			
			return comment;
			
		}
		
		
		public String toString()
		{
			
			if (type == null) return "noItem";
			return type.show() + " quantity: " + quantity + ", comment: " + comment;
			
		}
		
		
		public Boolean equals(Item other)
		{
			
			if (other == null) return false;
			if (type == other.type && quantity == other.quantity) return true;
			return false;
			
		}
		
	}
	
	public static void main(String[] args)
	{
		
		Item bread1 = new Item(ProductType.BREAD, 7, "this is bread1"); // correct values
		Item bread2 = new Item(ProductType.BREAD, -12, "this is bread2"); // -2 which should get replaced by 1
		Item tea = new Item(null, Integer.MAX_VALUE, null); // null values
		
		Item[] array1 = {bread1, bread2, tea };
		Item[] array2 = new Item[array1.length];
		for (int x = 0; x < array2.length; x++)
		{
			array2[x] = array1[x];
		}
		
		for (Item x : array2)
		{
			if (x == null) continue;
			System.out.println(x);
			x.addItems(5);
			System.out.println("After adding 5 elements: " + x);
			x.addItems(-100);
			System.out.println("After adding -100 elements: " + x);
			System.out.println("Can we remove 100 items? " + x.remove(100));
			System.out.println("Can we remove 7 items?  " + x.remove(7));
			System.out.println("After removing 7 items (if possible): " + x);
			System.out.println("Total price is: " + x.getTotalPrice());
			System.out.println("\n\n");
		}
		
	}
	
}
