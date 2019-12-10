package lab;

public class List
{
	
	protected List next;
	protected int value;
	
	public List(int val)
	{
		
		value = val;
		next = null;
		
	}
	
	
	public static int sumI(List start)
	{
		
		int sum = 0;
		
		while (start != null)
		{
			if (start.value > 0) sum += start.value;
			start = start.next;
		}
		
		return sum;
		
	}
	
	
	public static int sumR(List start)
	{
		
		if (start == null) return 0;
		
		int check = 0;
		
		if (start.value > 0) check += start.value;
		
		return sumR(start.next) + check;
		
	}
	
	
	public static List addElement(List start, int elem)
	{
		
		List list = new List(elem);
		list.next = start;
		
		return list;
		
	}
	
	
	public static List createFromArray(int[] array)
	{
		
		if (array == null || array.length == 0) return null;
		
		List start = null;
		
		for (int x = 0; x < array.length; x++)
		{
			start = addElement(start, array[x]);
		}
		
		return start;
		
	}
	
	
	public static String toString(List list)
	{
		
		if (list == null) return "[END]\n";
		return "[" + list.value + "]\t" + toString(list.next);
		
	}
	
	
	public static void main(String[] args)
	{
		
		// TODO delete this comment
		int[] array1 = {1, 2, 3, 4, 5, -7, 6 };
		int[] array2 = null;
		int[] array3 = { };
		int[] array4 = {-1, -5, 0 };
		int[][] array = {array1, array2, array3, array4 };
		for (int[] x : array)
		{
			List list = createFromArray(x);
			System.out.println(toString(list));
			System.out.println("Iterative sum of positives: " + sumI(list));
			System.out.println("Recurive sum of posisives: " + sumR(list));
			System.out.println("\n\n");
		}
		
	}
	
}
