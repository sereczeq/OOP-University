package ex8;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex8
{
	
	public static Vector<String> findPattern(String s, String toFind, boolean ignoreCases)
	{
		
		Vector<String> result = new Vector<String>();
		Pattern pattern;
		if (ignoreCases) pattern = Pattern.compile(toFind, Pattern.CASE_INSENSITIVE);
		else pattern = Pattern.compile(toFind);
		Matcher matcher = pattern.matcher(s);
		while (matcher.find())
		{
			String string = matcher.group();
			result.add(string);
		}
		return (result);
		
	}
	
	
	public static Vector<String> findPattern(String s, String toFind)
	{
		
		return findPattern(s, toFind, false);
		
	}
	
	
	public static void main(String[] args)
	{
		
		String s = "dskjdaaABC12345asAB234ghdhfkFGS12343234cvBG 1234sbd";
		String numberPlate = "([A-Z]{1,3}\\d{3,5})|([A-Z]{1,3}\\s\\d{3,5})";
		System.out.println("number plates: " + findPattern(s, numberPlate));
		
		String postalCode = "\\d\\d-\\d\\d\\d\\s[A-Z][a-z]+";
		s = "36-047 Niechobrz 213hdga asvdga38712-345 Abc";
		System.out.println("postal codes: " + findPattern(s, postalCode));
		
		String lastName = "[A-Z][a-z]+";
		s = "Seredynski dsalkjd asdKowalKowalski";
		System.out.println("last names: " + findPattern(s, lastName));
		
		String emailAdress = "[a-z0-9._%+-]+@[a-z0-9-]+\\.[a-z]{2,6}";
		s = "email@email.com qwe.rty@weqjio.sdajlk.dajk ASlkj.%dajgv@Gmail.Pol";
		System.out.println("emails: " + findPattern(s, emailAdress, true));
		
	}
	
}
