package class6SimpleInheritance;

public class ControlledInteger
{
	
	protected int min;
	protected int max;
	protected int value = (min + max) / 2;
	protected int maxValue = value;
	
	public ControlledInteger(int value)
	{
		
		if (setValue(value));
		
	}
	
	
	protected void setMax()
	{
		
		if (value > maxValue) maxValue = value;
		
	}
	
	
	public ControlledInteger(int min, int max)
	{
		
		if (min < max)
		{
			this.min = min;
			this.max = max;
		}
		else
		{
			this.min = min;
			this.max = min;
		}
		
		setValue((min + max) / 2);
		
	}
	
	
	public boolean setValue(int val)
	{
		
		if (min <= val && val <= max)
		{
			value = val;
			setMax();
			return true;
		}
		return false;
		
	}
	
	
	public boolean isOdd()
	{
		
		if (value % 2 != 0) return true;
		return false;
		
	}
	
	
	public boolean isPrimeNumber()
	{
		
		for (int x = 2; x < value; x++)
		{
			if (value % x == 0) return false;
		}
		
		return true;
		
	}
	
	
	public void multiplyBy(int v)
	{
		
		setValue(value * v);
		
	}
	
	
	public boolean isDivisibleBy(int v)
	{
		
		if (value % v == 0) return true;
		return false;
		
	}
	
	
	public int getMaxValueSoFar() // returns the maximal value that was ever set
	{
		
		return maxValue;
		
	}
	
	
	public String toString()
	{
		
		return "[" + min + ", " + max + "] " + value;
		
	}
	
	
	public boolean equals(Object other)
	{
		
		if (!(other instanceof ControlledInteger)) return false;
		ControlledInteger otherInt = (ControlledInteger) other;
		
		return (this.value == otherInt.value && this.min == otherInt.min && this.max == otherInt.max);
		
	}
	
	
	public static void main(String[] args)
	{
		
		ControlledInteger integer = new ControlledInteger(0, 10);
		System.out.println(integer);
		integer.setValue(8);
		System.out.println(integer);
		integer.setValue(3);
		System.out.println(integer);
		System.out.println(integer.isDivisibleBy(3));
		System.out.println(integer.isPrimeNumber());
		integer.multiplyBy(3);
		System.out.println(integer);
		integer.setValue(8);
		System.out.println(integer.getMaxValueSoFar());
		
		ControlledInteger integer2 = new ControlledInteger(0, 10);
		System.out.println(integer2);
		integer2.setValue(8);
		System.out.println(integer2);
		integer2.setValue(3);
		System.out.println(integer2);
		System.out.println(integer2.isDivisibleBy(3));
		System.out.println(integer2.isPrimeNumber());
		integer2.multiplyBy(3);
		System.out.println(integer2);
		integer2.setValue(8);
		System.out.println(integer2.getMaxValueSoFar());
		
		System.out.println(integer.equals(integer2));
		
	}
	
}
