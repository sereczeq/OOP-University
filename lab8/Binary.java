package lab8;

public class Binary
{
	
	private char[] binary = new char[32];
	
	public Binary(int number)
	{
		
		int value = number;
		
		for (int x = binary.length - 1; x >= 0; x--)
		{
			binary[x] = value % 2 == 0 ? '0' : '1';
			value /= 2;
		}
		
		if (number < 0) twosComplement();
		
	}
	
	
	public Binary(char[] binary)
	{
		
		binary = getRidOfSlash(binary);
		
		if (binary != null && binary.length > 0 && binary.length <= this.binary.length)
		{
			for (int x = 0; x < binary.length; x++)
			{
				this.binary[this.binary.length - 1 - x] = binary[binary.length - 1 - x];
			}
		}
		
		for (int x = 0; x < this.binary.length; x++)
		{
			// changing all non binary values to zeros
			if (this.binary[x] != '0' && this.binary[x] != '1') this.binary[x] = '0';
		}
		
	}
	
	
	public Binary(Binary bn)
	{
		
		if (bn != null && bn instanceof Binary) this.binary = bn.binary;
		
	}
	
	
	public Binary(String string)
	{
		
		this(string != null ? string.toCharArray() : null);
		
	}
	
	
	private void twosComplement()
	{
		
		for (int x = 0; x < binary.length; x++)
		{
			binary[x] = binary[x] == '1' ? '0' : '1';
		}
		// "adding" one to the last bit
		for (int x = binary.length - 1; x >= 0; x--)
		{
			if (binary[x] == '0')
			{
				binary[x] = '1';
				return;
			}
			binary[x] = '0';
		}
		
	}
	
	
	private char[] getRidOfSlash(char[] array)
	{
		
		int howLong = array.length;
		for (char x : array)
		{
			if (x == '\"') howLong--;
		}
		
		char[] returnArray = new char[howLong];
		int where = 0;
		
		for (char x : array)
		{
			if (x != '\"')
			{
				returnArray[where] = x;
				where++;
			}
		}
		
		return returnArray;
		
	}
	
	
	public int getInteger()
	{
		
		int sum = 0;
		int sign = 1;
		if (binary[0] == '1')
		{
			// temporarily changing binary to positive
			twosComplement();
			sign = -1;
		}
		for (int x = 0; x < binary.length; x++)
		{
			sum += (binary[x] - 48) * Math.pow(2, binary.length - 1 - x) * sign;
		}
		if (sign == -1) twosComplement();
		return sum;
		
	}
	
	
	public Binary calcAND(Binary other)
	{
		
		if (notBinary(other)) return new Binary(0);
		return new Binary(getInteger() & other.getInteger());
		
	}
	
	
	public Binary calcAND(int value)
	{
		
		return calcAND(new Binary(value));
		
	}
	
	
	public static Binary calcAND(int first, int second)
	{
		
		return new Binary(first).calcAND(new Binary(second));
		
	}
	
	
	public Binary calcOR(Binary other)
	{
		
		if (notBinary(other)) return new Binary(0);
		return new Binary(getInteger() | other.getInteger());
		
	}
	
	
	public Binary calcOR(int value)
	{
		
		return calcOR(new Binary(value));
		
	}
	
	
	public Binary calcXOR(Binary other)
	{
		
		return new Binary(getInteger() ^ other.getInteger());
		
	}
	
	
	public Binary calcXOR(int value)
	{
		
		return calcXOR(new Binary(value));
		
	}
	
	
	private boolean notBinary(Binary bin)
	{
		
		if (bin == null || !(bin instanceof Binary)
				|| (bin instanceof Binary && (bin.binary == null || bin.binary.length != 32)))
			return true;
		return false;
		
	}
	
	
	public String toString()
	{
		
		if (notBinary(this)) return "null";
		return String.copyValueOf(binary);
		
	}
	
	
	public boolean equals(Object other)
	{
		
		if (other == null || !(other instanceof Binary)) return false;
		Binary bn = (Binary) other;
		return this.toString().equals(bn.toString());
		
	}
	
}
