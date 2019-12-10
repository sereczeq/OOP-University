package lab;

public class lab6Task1Enum
{
	
	public enum ProductType
	{
		
		BREAD(3.5), MILK(2.2), TEA(15.0), BUTTER(7.5), OTHER(0.0);
		
		private double unitPrice;
		
		public static ProductType fromString(String string)
		{
			
			string = string.toLowerCase().trim();
			switch (string)
			{
				case "bread":
					return BREAD;
				case "milk":
					return MILK;
				case "tea":
					return TEA;
				case "butter":
					return BUTTER;
				default:
					return OTHER;
			}
			
		}
		
		
		private ProductType(double price)
		{
			
			unitPrice = price;
			
		}
		
		
		public double getPrice()
		{
			
			return unitPrice;
			
		}
		
		
		public String show()
		{
			
			String name = this + " [" + unitPrice + "]";
			return name.toLowerCase();
			
		}
		
		
		public static void testMe()
		{
			
			ProductType p1 = ProductType.BREAD;
			System.out.println(p1);
			p1 = ProductType.fromString("tea");
			System.out.println(p1);
			for (ProductType p : ProductType.values())
			{
				System.out.println(p.show());
			}
			
		}
		
	}
	
	public static void main(String[] args)
	{
		
		ProductType.testMe();
		
	}
	
}
