package different;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

interface IWithName
{
	
	String getName();
	
}

interface IList<E> extends Iterable<E>
{
	
	boolean add(E e); // add element to the list on proper position
	
	void add(int index, E element) throws NoSuchElementException; // not implemented
	
	void clear(); // delete all elements
	
	boolean contains(E element); // is list containing an element (equals())
	
	E get(int index) throws NoSuchElementException; // get element from position
	
	E set(int index, E element) throws NoSuchElementException; // not implemented
	
	int indexOf(E element); // where is element (equals())
	
	boolean isEmpty();
	
	Iterator<E> iterator();
	
	ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator
	
	E remove(int index) throws NoSuchElementException; // remove element from position index
	
	boolean remove(E e); // remove element
	
	int size();
	
}

class HashTable<E>
{
	
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize = 8;
	private final static double defaultMaxLoadFactor = 0.7;
	private int size;
	private int elemAmount;
	private final double maxLoadFactor;
	
	public HashTable()
	{
		
		this(defaultInitSize);
		
	}
	
	
	public HashTable(int size)
	{
		
		this(size, defaultMaxLoadFactor);
		
	}
	
	
	public HashTable(int initCapacity, double maxLF)
	{
		
		// TODO
		this.arr = new LinkedList[size];
		this.size = initCapacity;
		this.maxLoadFactor = maxLF;
		
		int x = 0;
		while (x < initCapacity)
		{
			arr[x] = new LinkedList<E>();
			x++;
		}
		
	}
	
	
	public boolean add(Object elem)
	{
		
		Document document = (Document) elem;
		BigInteger bigInt = document.hashCodeBig();
		int k = bigInt.mod(BigInteger.valueOf(size)).intValue();
		if (arr[k].contains(elem))
		{
			return false;
		}
		arr[k].add(elem);
		elemAmount = +elemAmount;
		if (elemAmount > (float) size * maxLoadFactor)
		{
			doubleArray();
		}
		
		return true;
		
		// czy to dobrze?
	}
	
	
	private void doubleArray()
	{
		
		size = size * 2;
		elemAmount = 0;
		LinkedList[] temp = arr;
		arr = new LinkedList[size];
		
		int x = 0;
		while (x < size)
		{
			arr[x] = new LinkedList<E>();
			x++;
		}
		for (LinkedList y : temp)
		{
			for (Object obj : y)
			{
				add(obj);
			}
		}
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		for (int x = 0; x < size; x++)
		{
			string += x + ":";
			Iterator it = arr[x].iterator();
			if (it.hasNext())
			{
				string += " " + ((Document) it.next()).getName();
				while (it.hasNext()) string += ", " + ((Document) it.next()).getName();
			}
			string += "\n";
		}
		return string;
		
	}
	
	
	public Object get(Object toFind)
	{
		
		Document doc = (Document) toFind;
		BigInteger bigKey = doc.hashCodeBig();
		int key = bigKey.mod(BigInteger.valueOf(size)).intValue();
		for (Object x : arr[key]) if (x.equals(toFind)) return x;
		return null;
		
	}
	
	
	private int hash(int key)
	{
		
		return key % size;
		
	}
	
	
	private int hashValue(BigInteger hc, int size)
	{
		
		return hc.mod(BigInteger.valueOf(size)).intValue();
		
	}
	
}

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>
{
	
	private class Element
	{
		
		public Element(E e)
		{
			
			object = e;
			
		}
		
		
		public Element(E e, Element next, Element prev)
		{
			
			object = e;
			this.prev = prev;
			this.next = next;
			
		}
		
		
		// add element e after this
		public void addAfter(Element elem)
		{
			
			Element currElem = next;
			next = elem;
			elem.prev = this;
			currElem.prev = elem;
			elem.next = currElem;
			
		}
		
		
		// assert it is NOT a sentinel
		public E remove()
		{
			
			if (object == null)
			{
				throw new NoSuchElementException("can't remove sentinel / element with null object");
			}
			if (next != null)
			{
				next.prev = prev;
			}
			if (prev != null)
			{
				prev.next = next;
			}
			
			return object;
			
		}
		
		E object;
		Element next = null;
		Element prev = null;
		
	}
	
	Element sentinel;
	int size;
	
	private class InnerIterator implements Iterator<E>
	{
		
		Element currElem;
		
		public InnerIterator()
		{
			
			currElem = sentinel;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return currElem.next != sentinel;
			
		}
		
		
		@Override
		public E next()
		{
			
			if (!hasNext())
			{
				throw new NoSuchElementException();
				
			}
			else
			{
				currElem = currElem.next;
				return currElem.object;
			}
			
		}
		
	}
	
	private class InnerListIterator implements ListIterator<E>
	{
		
		Element currElem;
		
		public InnerListIterator()
		{
			
			currElem = sentinel;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return currElem.next != sentinel;
			
		}
		
		
		@Override
		public E next()
		{
			
			if (!hasNext())
			{
				throw new NoSuchElementException();
				
			}
			else
			{
				currElem = currElem.next;
				return currElem.object;
			}
			
		}
		
		
		@Override
		public void add(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			return !isEmpty();
			
		}
		
		
		@Override
		public int nextIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public E previous()
		{
			
			if (!hasPrevious())
			{
				throw new NoSuchElementException();
				
			}
			else
			{
				currElem = currElem.prev;
				
			}
			return currElem.object;
			
		}
		
		
		@Override
		public int previousIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public void remove()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public void set(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	public TwoWayCycledOrderedListWithSentinel()
	{
		
		sentinel = new Element(null);
		sentinel.prev = sentinel.next = sentinel;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		
		if (isEmpty())
		{
			sentinel.next = sentinel.prev = new Element(e, sentinel, sentinel);
			return true;
		}
		else if (e instanceof Comparable)
		{
			
			Element element = sentinel.next;
			int x = 0;
			while (x < size())
			
			{
				
				if (((Comparable<E>) element.object).compareTo(e) > 0)
				{
					element.prev.addAfter(new Element(e));
					return true;
				}
				element = element.next;
				x++;
			}
			
		}
		sentinel.prev.addAfter(new Element(e));
		return true;
		
	}
	
	
	private Element getElement(int index)
	{
		
		Element elem = sentinel.next;
		
		int x = 0;
		while (elem != sentinel && x <= index)
		{
			
			if (x == index)
			{
				return elem;
			}
			x++;
			elem = elem.next;
		}
		throw new NoSuchElementException();
		
	}
	
	
	@SuppressWarnings("unused")
	private Element getElement(E obj)
	{
		
		return getElement(indexOf(obj));
		
	}
	
	
	@Override
	public void add(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	@Override
	public void clear()
	{
		
		sentinel.next = sentinel.prev = sentinel;
		
	}
	
	
	@Override
	public boolean contains(E element)
	{
		
		Element element1 = sentinel.next;
		while (element1 != sentinel)
		{
			if (element1.object.equals(element))
			{
				return true;
			}
			element1 = element1.next;
		}
		return false;
		
	}
	
	
	@Override
	public E get(int index)
	{
		
		return getElement(index).object;
		
	}
	
	
	@Override
	public E set(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	@Override
	public int indexOf(E element)
	{
		
		Iterator<E> iterator = iterator();
		int x = 0;
		while (x < size())
		{
			
			if (iterator.next().equals(element))
			{
				return x;
			}
			x++;
		}
		return -1;
		
	}
	
	
	@Override
	public boolean isEmpty()
	{
		
		return sentinel.next == sentinel;
		
	}
	
	
	@Override
	public Iterator<E> iterator()
	{
		
		return new InnerIterator();
		
	}
	
	
	@Override
	public ListIterator<E> listIterator()
	{
		
		return new InnerListIterator();
		
	}
	
	
	@Override
	public E remove(int index)
	{
		
		Element element = sentinel.next;
		
		int x = 0;
		while (x <= index && element != sentinel)
		{
			if (x == index)
			{
				return element.remove();
			}
			
			x++;
			element = element.next;
		}
		throw new NoSuchElementException();
		
	}
	
	
	@Override
	public boolean remove(E e)
	{
		
		try
		{
			remove(indexOf(e));
			return true;
		}
		catch (NoSuchElementException ex)
		{
			return false;
		}
		
	}
	
	
	@Override
	public int size()
	{
		
		int x = 0;
		Element element = sentinel.next;
		
		while (element != sentinel)
		{
			x++;
			element = element.next;
		}
		return x;
		
	}
	
	
	@Override
	public boolean equals(Object other)
	{
		
		return toString().contentEquals(other.toString());
		
	}
	
	
	public void add(TwoWayCycledOrderedListWithSentinel<E> other)
	{
		
		if (!equals(other))
		{
			while (other.size() > 0)
			{
				add(other.remove(0));
			}
			
		}
		
	}
	
	
	public void removeAll(E e)
	{
		
		while (remove(e));
		
	}
	
	
	@Override
	public String toString()
	{
		
		String string = "";
		Iterator<E> iterator = iterator();
		int x = 0;
		while (x < size())
		{
			string += "\n" + iterator.next();
			x++;
		}
		return string;
		
	}
	
}

class Link implements Comparable<Link>
{
	
	public String ref;
	public int weight;
	
	public Link(String ref)
	{
		
		this.ref = ref;
		weight = 1;
		
	}
	
	
	public Link(String ref, int weight)
	{
		
		this.ref = ref;
		this.weight = weight;
		
	}
	
	
	public int getWeight()
	{
		
		return weight;
		
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		
		return obj instanceof Link && ref.contentEquals(((Link) obj).ref);
		
	}
	
	
	@Override
	public String toString()
	{
		
		return ref + "(" + weight + ")";
		
	}
	
	
	@Override
	public int compareTo(Link another)
	{
		
		return ref.compareTo(another.ref);
		
	}
	
}

class Document implements IWithName
{
	
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	final static Pattern regex = Pattern.compile("(link=)(?<ref>[a-z][\\w_]*)(\\((?<number>[0-9-]*)\\))?$");
	final static Pattern docName = Pattern.compile("^[a-z].*$");
	
	public Document(String name)
	{
		
		// TODO
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		
	}
	
	
	public Document(String name, Scanner scan)
	{
		
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
		
	}
	
	
	public void load(Scanner scan)
	{
		
		// TODO
		String marker = "link=";
		String endMarker = "eod";
		String line = scan.nextLine().toLowerCase();
		while (!line.equalsIgnoreCase(endMarker))
		{
			String arr[] = line.split(" ");
			for (String word : arr)
			{
				if (word.startsWith(marker))
				{
					String linkStr = word.substring(marker.length());
					Link l;
					if ((l = createLink(linkStr)) != null) link.add(l);
				}
				
			}
			line = scan.nextLine().toLowerCase();
		}
		
	}
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	
	
	public static boolean isCorrectId(String id)
	{
		
		id = id.toLowerCase();
		if (id.length() == 0) return false;
		if (id.charAt(0) < 'a' || id.charAt(0) > 'z') return false;
		for (int i = 1; i < id.length(); i++)
		{
			if (!(id.charAt(i) >= 'a' && id.charAt(i) <= 'z' || id.charAt(i) >= '0' && id.charAt(i) <= '9'
					|| id.charAt(i) == '_'))
				return false;
		}
		return true;
		
	}
	
	
	private static Link createIdAndNumber(String id, int n)
	{
		
		if (!isCorrectId(id)) return null;
		return new Link(id.toLowerCase(), n);
		
	}
	
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the
	// begin)
	static Link createLink(String link)
	{
		
		if (link.length() == 0) return null;
		int openBracket = link.indexOf('(');
		int closeBracket = link.indexOf(')');
		if (openBracket > 0 && closeBracket > openBracket && closeBracket == link.length() - 1)
		{
			String strNumber = link.substring(openBracket + 1, closeBracket);
			try
			{
				int number = Integer.parseInt(strNumber);
				if (number < 1) return null;
				return createIdAndNumber(link.substring(0, openBracket), number);
			}
			catch (NumberFormatException ex)
			{
				return null;
			}
		}
		
		return createIdAndNumber(link, 1);
		
	}
	
	
	@Override
	public String toString()
	{
		
		String retStr = "Document: " + name;
		int counter = 0;
		for (Link linkElem : link)
		{
			if (counter % 10 == 0) retStr += "\n";
			else retStr += " ";
			retStr += linkElem.toString();
			counter++;
		}
		return retStr;
		
	}
	
	
	@Override
	public boolean equals(Object other)
	{
		
		return other instanceof Document && name.contentEquals(((Document) other).name);
		
	}
	
	
	@Override
	public int hashCode()
	{
		
		int[] multValues = {7, 11, 13, 17, 19 };
		char[] name = this.name.toCharArray();
		double key = 0;
		if (name.length >= 1) key = (int) name[0];
		for (int x = 1, n = 0; x < name.length; x++, n++)
		{
			key *= multValues[n % 5];
			key += (int) name[x];
		}
		return (int) key;
		
	}
	
	
	public BigInteger hashCodeBig()
	{
		
		int[] multValues = {7, 11, 13, 17, 19 };
		char[] name = this.name.toCharArray();
		BigInteger key = BigInteger.ZERO;
		if (name.length >= 1) key = BigInteger.valueOf((int) name[0]);
		for (int x = 1, n = 0; x < name.length; x++, n++)
		{
			key = key.multiply(BigInteger.valueOf(multValues[n % 5]));
			key = key.add(BigInteger.valueOf((int) name[x]));
		}
		return key;
		
	}
	
	
	public String getName()
	{
		
		return name;
		
	}
	
	
	public String toStringReverse()
	{
		
		String retStr = "Document: " + name;
		int counter = 0;
		ListIterator<Link> iter = link.listIterator();
		while (iter.hasNext()) iter.next();
		while (iter.hasPrevious())
		{
			if (counter % 10 == 0) retStr += "\n";
			else retStr += " ";
			retStr += iter.previous().toString();
			counter++;
		}
		return retStr;
		
	}
	
	
	public int[] getWeights()
	{
		
		int[] arr = new int[link.size()];
		int i = 0;
		for (Link linkElem : link)
		{
			arr[i++] = linkElem.weight;
		}
		return arr;
		
	}
	
	
	public static void showArray(int[] arr)
	{
		
		if (arr.length > 0)
		{
			System.out.print(arr[0]);
			for (int i = 1; i < arr.length; i++) System.out.print(" " + arr[i]);
		}
		System.out.println();
		
	}
	
	
	void bubbleSort(int[] arr)
	{
		
		showArray(arr);
		for (int begin = 0; begin < arr.length - 1; begin++)
		{
			for (int j = arr.length - 1; j > begin; j--) if (arr[j - 1] > arr[j]) swap(arr, j - 1, j);
			showArray(arr);
		}
		
	}
	
	
	void a(int[] arr)
	{
		
		int begining = 0;
		while (begining < arr.length - 1)
		{
			begining++;
			int num = arr.length;
			while (num > begining)
			{
				num--;
				if (arr[num - 1] > arr[num])
				{
					swap(arr, num - 1, num);
				}
			}
		}
		
	}
	
	
	private void swap(int[] arr, int i, int j)
	{
		
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
	}
	
	
	public void insertSort(int[] arr)
	{
		
		showArray(arr);
		for (int pos = arr.length - 2; pos >= 0; pos--)
		{
			int value = arr[pos];
			int i = pos + 1;
			while (i < arr.length && value > arr[i])
			{
				arr[i - 1] = arr[i];
				i++;
			}
			arr[i - 1] = value;
			showArray(arr);
		}
		
	}
	
	
	public void selectSort(int[] arr)
	{
		
		showArray(arr);
		for (int border = arr.length; border > 1; border--)
		{
			int maxPos = 0;
			for (int pos = 0; pos < border; pos++) if (arr[maxPos] < arr[pos]) maxPos = pos;
			swap(arr, border - 1, maxPos);
			showArray(arr);
		}
		
	}
	
	
	private static int[] mergeSort(int[] arr, int[] arrL, int[] arrR, int leftFirst)
	{
		
		// MergeSorting arrays
		int leftPos = 0;
		int rightPos = 0;
		while (leftPos < arrL.length && rightPos < arrR.length)
		{
			if (arrL[leftPos] < arrR[rightPos]) arr[leftFirst++] = arrL[leftPos++];
			else arr[leftFirst++] = arrR[rightPos++];
		}
		
		while (leftPos < arrL.length)
		{
			arr[leftFirst++] = arrL[leftPos++];
		}
		while (rightPos < arrR.length)
		{
			arr[leftFirst++] = arrR[rightPos++];
		}
		return arr;
		
	}
	
	
	public static void iterativeMergeSort(int[] arr)
	{
		
		showArray(arr);
		
		for (int size = 1; size < arr.length; size *= 2)
		{
			
			for (int LeftBeggining = 0; LeftBeggining < arr.length; LeftBeggining += 2 * size)
			{
				
				int LeftEnding = (LeftBeggining + size < arr.length ? LeftBeggining + size : arr.length) - 1;
				int RigthEnding = (LeftBeggining + 2 * size < arr.length ? LeftBeggining + 2 * size : arr.length) - 1;
				
				int[] arrL = new int[LeftEnding - LeftBeggining + 1];
				int[] arrR = new int[RigthEnding - LeftEnding];
				int x = 0;
				while (x < arrL.length)
				{
					
					arrL[x] = arr[LeftBeggining + x];
					x++;
				}
				int y = 0;
				while (y < arrR.length)
				{
					
					arrR[y] = arr[LeftEnding + y + 1];
					y++;
				}
				arr = mergeSort(arr, arrL, arrR, LeftBeggining);
			}
			showArray(arr);
		}
		
	}
	
	
	public static void radixSort(int[] arr)
	{
		
		showArray(arr);
		
		int[] numArray = new int[arr.length];
		// last
		int i = 0;
		while (i < numArray.length)
		{
			
			numArray[i] = arr[i] % 10;
			i++;
		}
		arr = countSort(arr, numArray);
		showArray(arr);
		// middle
		int x = 0;
		while (x < numArray.length)
		{
			
			numArray[x] = (arr[x] / 10) % 10;
			x++;
		}
		arr = countSort(arr, numArray);
		showArray(arr);
		// first
		int y = 0;
		
		while (y < numArray.length)
		{
			numArray[y] = arr[y] / 100;
			y++;
		}
		arr = countSort(arr, numArray);
		showArray(arr);
		
	}
	
	
	public static int[] countSort(int[] arr, int[] numArray)
	{
		
		int[] count = new int[10];
		for (int x : numArray) count[x]++;
		for (int x = 1; x < 10; x++) count[x] += count[x - 1];
		int[] newArr = new int[numArray.length];
		for (int x = arr.length - 1; x >= 0; x--) newArr[count[numArray[x]]-- - 1] = arr[x];
		return newArr;
		
	}
	
}

public class Main
{
	
	static Scanner scan; // for input stream
	
	public static void main(String[] args)
	{
		
		System.out.println("START");
		scan = new Scanner(System.in);
		HashTable hashTable = new HashTable(8);
		Document currentDoc = null;
		boolean halt = false;
		while (!halt)
		{
			String line = scan.nextLine();
			// empty line and comment line - read next line
			if (line.length() == 0 || line.charAt(0) == '#') continue;
			// copy line to output (it is easier to find a place of a mistake)
			System.out.println("!" + line);
			String word[] = line.split(" ");
			// getdoc name - change document to name
			if (word[0].equalsIgnoreCase("getdoc") && word.length == 2)
			{
				currentDoc = (Document) hashTable.get(new Document(word[1]));
				continue;
			}
			
			// ld documentName
			if (word[0].equalsIgnoreCase("ld") && word.length == 2)
			{
				if (Document.isCorrectId(word[1]))
				{
					currentDoc = new Document(word[1], scan);
					if (!hashTable.add(currentDoc)) System.out.println("error");
				}
				else System.out.println("incorrect ID");
				continue;
			}
			// ha
			if (word[0].equalsIgnoreCase("ha") && word.length == 1)
			{
				halt = true;
				continue;
			}
			// clear
			if (word[0].equalsIgnoreCase("clear") && word.length == 1)
			{
				if (currentDoc != null) currentDoc.link.clear();
				else System.out.println("no current document");
				continue;
			}
			// show
			if (word[0].equalsIgnoreCase("show") && word.length == 1)
			{
				if (currentDoc != null) System.out.println(currentDoc.toString());
				else System.out.println("no current document");
				continue;
			}
			// reverse
			if (word[0].equalsIgnoreCase("reverse") && word.length == 1)
			{
				if (currentDoc != null) System.out.println(currentDoc.toStringReverse());
				else System.out.println("no current document");
				continue;
			}
			// size
			if (word[0].equalsIgnoreCase("size") && word.length == 1)
			{
				if (currentDoc != null) System.out.println(currentDoc.link.size());
				else System.out.println("no current document");
				continue;
			}
			// add str
			if (word[0].equalsIgnoreCase("add") && word.length == 2)
			{
				if (currentDoc != null)
				{
					Link link = Document.createLink(word[1]);
					if (link == null) System.out.println("error");
					else System.out.println(currentDoc.link.add(link));
				}
				else System.out.println("no current document");
				continue;
			}
			// get index
			if (word[0].equalsIgnoreCase("get") && word.length == 2)
			{
				if (currentDoc != null)
				{
					int index = Integer.parseInt(word[1]);
					try
					{
						Link l = currentDoc.link.get(index);
						System.out.println(l.ref);
					}
					catch (NoSuchElementException e)
					{
						System.out.println("error");
					}
				}
				else System.out.println("no current document");
				continue;
			}
			// index str
			if (word[0].equalsIgnoreCase("index") && word.length == 2)
			{
				if (currentDoc != null)
				{
					int index = currentDoc.link.indexOf(new Link(word[1]));
					System.out.println(index);
				}
				else System.out.println("no current document");
				
				continue;
			}
			// remi index
			if (word[0].equalsIgnoreCase("remi") && word.length == 2)
			{
				if (currentDoc != null)
				{
					int index = Integer.parseInt(word[1]);
					try
					{
						Link l = currentDoc.link.remove(index);
						System.out.println(l);
					}
					catch (NoSuchElementException e)
					{
						System.out.println("error");
					}
				}
				else System.out.println("no current document");
				
				continue;
			}
			// rem str
			if (word[0].equalsIgnoreCase("rem") && word.length == 2)
			{
				if (currentDoc != null)
				{
					System.out.println(currentDoc.link.remove(new Link(word[1])));
				}
				else System.out.println("no current document");
				continue;
			}
			// remall str
			if (word[0].equalsIgnoreCase("remall") && word.length == 2)
			{
				if (currentDoc != null)
				{
					currentDoc.link.removeAll(new Link(word[1]));
				}
				else System.out.println("no current document");
				continue;
			}
			// ht - show hashtable
			if (word[0].equalsIgnoreCase("ht") && word.length == 1)
			{
				System.out.print(hashTable.toString());
				continue;
			}
			
			System.out.println("Wrong command");
		}
		System.out.println("END OF EXECUTION");
		scan.close();
		
	}
	
}
