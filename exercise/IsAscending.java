package exercise;

public class IsAscending
{
	
	public static boolean isAscending(int[] arr)
	{
		
		if(arr == null || arr.length <= 0)
		{
			System.err.println("isAsending: no data");
			return false;
		}
		if(arr.length == 1) return false;
		
		int max = arr[0];
		
		for(int x=1; x<arr.length; x++)
		{
			if(arr[x] > max) max = arr[x];
			else return false;
		}
		return true;
	}

	public static void main(String[] args) {

		int[] array = {};
		
		if(isAscending(array)) System.out.println("Is ascending indeed");
		else System.out.println("Not ascending");
	}

}
