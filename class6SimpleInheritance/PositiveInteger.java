package class6SimpleInheritance;

public class PositiveInteger extends ControlledInteger
{
	
	public PositiveInteger()
	{
		
		super(1, 100);
		
	}
	
	
	public PositiveInteger(int max)
	{
		
		super(1, max);
		positiveCheck();
		
	}
	
	
	public PositiveInteger(int min, int max)
	{
		
		super(min, max);
		positiveCheck();
		
	}
	
	
	public PositiveInteger(int min, int max, int val)
	{
		
		super(min, max);
		positiveCheck();
		setValue(val);
		
	}
	
	
	protected void positiveCheck()
	{
		
		if (min <= 0) min = 1;
		if (max <= 0) max = 1;
		if (max < min) max = min;
		setValue((min + max / 2));
		
	}
	
	
	public static void main(String[] args)
	{
		
		PositiveInteger integer = new PositiveInteger(-30, 15, 3);
		System.out.println(integer);
		
	}
	
}
