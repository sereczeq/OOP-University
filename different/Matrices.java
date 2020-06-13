package different;

import java.util.Scanner;

public class Matrices
{
	
	private int[] numberA = new int[5];
	private int[] orderA = new int[5];
	private int[] numberB = new int[5];
	private int[] orderB = new int[5];
	
	public static void main(String[] args)
	{
		
		Matrices matrices = new Matrices();
		Scanner scanner = new Scanner(System.in);
		int[] sizesA = askForDimensions("A", scanner);
		int[] sizesB = askForDimensions("B", scanner);
		matrices.askForCoordinates(matrices.numberA, matrices.orderA, sizesA[3], scanner);
		matrices.askForCoordinates(matrices.numberB, matrices.orderB, sizesB[3], scanner);
		System.out.println("aaaaaaaaaaaa");
		
	}
	
	
	private static int[] askForDimensions(String letter, Scanner scanner)
	{
		
		int[] sizes = new int[4];
		System.out.println("How many rows in " + letter + " ?");
		sizes[1] = scanner.nextInt();
		System.out.println("How many columns in " + letter + " ?");
		sizes[2] = scanner.nextInt();
		sizes[3] = sizes[1] * sizes[2];
		return sizes;
		
	}
	
	
	private void askForCoordinates(int[] number, int[] order, int size, Scanner scanner)
	{
		
		// int[] number = numberB;
		// int[] order = orderB;
		// if (letter == "A")
		// {
		// number = numberA;
		// order = orderA;
		// }
		System.out.println("Input coordinates of matrice ");
		for (int x = 0, index = 0; x < size; x++)
		{
			int input = scanner.nextInt();
			if (input == 0)
			{
				order[index]++;
			}
			else
			{
				number[index] = input;
				order[index]++;
				order[index + 1] = order[index];
				index++;
			}
			if (index == number.length - 1)
			{
				int[] newNumber = new int[number.length + 5];
				int[] newOrder = new int[number.length + 5];
				for (int y = 0; y < number.length; y++) newNumber[y] = number[y];
				for (int y = 0; y < number.length; y++) newOrder[y] = order[y];
				number = newNumber.clone();
				order = newOrder.clone();
			}
		}
		
	}
	
}
