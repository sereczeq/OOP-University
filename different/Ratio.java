package different;

public class Ratio
{
	
	protected int up;
	protected int down;
	
	public Ratio(int up, int down)
	{
		
		if (down == 0)
		{
			up = 0;
			down = 1;
		}
		
		this.up = up;
		this.down = down;
		
		makeGood();
		
	}
	
	
	public Ratio add(Ratio other)
	{
		
		return new Ratio(up * other.down + other.up * down, down * other.down);
		
	}
	
	
	public Ratio subtract(Ratio other)
	{
		
		return add(new Ratio(-other.up, other.down));
		
	}
	
	
	public Ratio multiply(Ratio other)
	{
		
		return new Ratio(up * other.up, down * other.down);
		
	}
	
	
	public Ratio divide(Ratio other)
	{
		
		return new Ratio(up * other.down, down * other.up);
		
	}
	
	
	public Ratio compare(Ratio other) // returns bigger ratio
	{
		
		if (this.subtract(other).up > 0) return this;
		return other;
		
	}
	
	
	public void makeGood()
	{
		
		for (int x = 2; (x <= Math.max(-up, up)) && x <= down; x++)
		{
			if (Math.max(-up, up) % x == 0 && down % x == 0)
			{
				up /= x;
				down /= x;
				x = 2;
			}
		}
		
	}
	
	
	public String toString()
	{
		
		return up + " / " + down;
		
	}
	
	
	public static void main(String[] args)
	{
		
		Ratio ratio1 = new Ratio(11, 33);
		Ratio ratio2 = new Ratio(3, 4);
		System.out.println(ratio1.add(ratio2));
		System.out.println(ratio1.subtract(ratio2));
		System.out.println(ratio1.multiply(ratio2));
		System.out.println(ratio1.divide(ratio2));
		System.out.println(ratio1.compare(ratio2));
		
	}
	
}
