package different;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

class HashTable
{
	
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize = 8;
	private final static double defaultMaxLoadFactor = 0.7;
	private int size;
	private final double maxLoadFactor;
	private int numElements;
	
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
		
		this.size = initCapacity;
		this.maxLoadFactor = maxLF;
		arr = new LinkedList[size];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = new LinkedList<>();
		}
		numElements = 1;
		
	}
	
	
	private int hashValue(int hc, int sz)
	{
		
		if (hc < 0) return hc % sz + sz;
		else return hc % sz;
		// return (hc & 0x7fffffff) % sz;
		
	}
	
	
	public boolean add(Object elem)
	{
		
		// TODO
		int hc = elem.hashCode();
		int hv = hashValue(hc, size);
		if (arr[hv].contains(elem))
		{
			return false;
		}
		if (((double) numElements) / size < maxLoadFactor)
		{
			arr[hv].add(elem);
		}
		else
		{
			doubleArray();
			hv = hashValue(hc, size);
			arr[hv].add(elem);
		}
		numElements++;
		return true;
		
	}
	
	
	private void doubleArray()
	{
		
		LinkedList arr2[] = new LinkedList[size * 2];
		for (int i = 0; i < arr2.length; i++)
		{
			arr2[i] = new LinkedList<>();
		}
		int i, j, idx;
		Object elem;
		for (i = 0; i < size; i++)
		{
			for (j = 0; j < arr[i].size(); j++)
			{
				elem = arr[i].get(j);
				idx = hashValue(elem.hashCode(), 2 * size);
				arr2[idx].add(elem);
			}
		}
		arr = null;
		size = size * 2;
		arr = new LinkedList[size];
		for (i = 0; i < arr.length; i++)
		{
			arr[i] = new LinkedList<>();
		}
		for (i = 0; i < size; i++)
		{
			for (j = 0; j < arr2[i].size(); j++)
			{
				elem = arr2[i].get(j);
				arr[i].add(elem);
			}
		}
		arr2 = null;
		
	}
	
	
	@Override
	public String toString()
	{
		
		// TODO
		// use IWithName x=(IWithName)elem;
		String res = "";
		int i, j;
		for (i = 0; i < arr.length; i++)
		{
			res += (i + ":");
			if (arr[i].size() == 0)
			{
				res += "\n";
			}
			else
			{
				res += " ";
				res += ((IWithName) arr[i].get(0)).getName();
				for (j = 1; j < arr[i].size(); j++)
				{
					res += ", ";
					res += ((IWithName) arr[i].get(j)).getName();
				}
				res += "\n";
			}
		}
		return res;
		
	}
	
	
	public Object get(Object toFind)
	{
		
		// TODO
		int hc = hashValue(toFind.hashCode(), size);
		if (!(arr[hc].contains(toFind)))
		{
			return null;
		}
		for (int i = 0; i < arr[hc].size(); i++)
		{
			if (arr[hc].get(i).equals(toFind))
			{
				return arr[hc].get(i);
			}
		}
		return null;
		
	}
	
}

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>
{
	
	private class Element
	{
		
		public Element(E e)
		{
			
			this.object = e;
			this.next = this;
			this.prev = this;
			
		}
		
		
		public Element(E e, Element next, Element prev)
		{
			
			this.object = e;
			this.next = next;
			this.prev = prev;
			
		}
		
		
		// add element e after this
		public void addAfter(Element elem)
		{
			
			elem.next = this.next;
			elem.prev = this;
			this.next.prev = elem;
			this.next = elem;
			
		}
		
		
		// assert it is NOT a last element in a list
		public void remove()
		{
			
			this.next.prev = this.prev;
			this.prev.next = this.next;
			
		}
		
		E object;
		Element next = null;
		Element prev = null;
		
	}
	
	Element sentinel;
	int size;
	
	private class InnerIterator implements Iterator<E>
	{
		
		Element p;
		int pos = 0;
		
		public InnerIterator()
		{
			
			pos = 0;
			p = sentinel.next;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return pos < size;
			
		}
		
		
		@Override
		public E next()
		{
			
			E elem = p.object;
			p = p.next;
			pos++;
			return elem;
			
		}
		
	}
	
	private class InnerListIterator implements ListIterator<E>
	{
		
		Element p;
		int pos = 0;
		
		public InnerListIterator()
		{
			
			pos = 0;
			p = sentinel.next;
			
		}
		
		
		@Override
		public boolean hasNext()
		{
			
			return pos < size;
			
		}
		
		
		@Override
		public E next()
		{
			
			E elem = p.object;
			p = p.next;
			pos++;
			return elem;
			
		}
		
		
		@Override
		public void add(E arg0)
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public boolean hasPrevious()
		{
			
			return pos > 0;
			
		}
		
		
		@Override
		public int nextIndex()
		{
			
			throw new UnsupportedOperationException();
			
		}
		
		
		@Override
		public E previous()
		{
			
			pos--;
			p = p.prev;
			return p.object;
			
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
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e)
	{
		
		Element p = sentinel.next;
		while (p != sentinel && ((Comparable<E>) p.object).compareTo(e) <= 0) p = p.next;
		p.prev.addAfter(new Element(e));
		size++;
		return true;
		
	}
	
	
	private Element getElement(int index)
	{
		
		checkIndex(index);
		Element p = sentinel.next;
		while (index > 0)
		{
			index--;
			p = p.next;
		}
		return p;
		
	}
	
	
	private Element getElement(E obj)
	{
		
		Element p = sentinel.next;
		while (p != sentinel)
		{
			if (p.object.equals(obj)) return p;
			p = p.next;
		}
		return null;
		
	}
	
	
	@Override
	public void add(int index, E element)
	{
		
		throw new UnsupportedOperationException();
		
	}
	
	
	private void checkIndex(int index)
	{
		
		if (index < 0 || index >= size) throw new NoSuchElementException();
		
	}
	
	
	@Override
	public void clear()
	{
		
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
		
	}
	
	
	@Override
	public boolean contains(E element)
	{
		
		return indexOf(element) != -1;
		
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
		
		Element p = sentinel.next;
		int counter = 0;
		counter++;
		while (p != sentinel)
		{
			if (p.object.equals(element)) return counter;
			else
			{
				counter++;
				p = p.next;
			}
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
		
		Element p = getElement(index);
		E retValue = p.object;
		p.remove();
		size--;
		return retValue;
		
	}
	
	
	@Override
	public boolean remove(E e)
	{
		
		Element p = getElement(e);
		if (p == null) return false;
		p.remove();
		size--;
		return true;
		
	}
	
	
	@Override
	public int size()
	{
		
		return size;
		
	}
	
	
	public String toStringReverse()
	{
		
		// TODO
		ListIterator<E> iter = new InnerListIterator();
		while (iter.hasNext()) iter.next();
		String retStr = "";
		while (iter.hasPrevious()) retStr += "\n" + iter.previous().toString();
		return retStr;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other)
	{
		
		if (this == other) return;
		if (other.isEmpty()) return;
		// this empty, but other not
		if (isEmpty())
		{
			sentinel.next = other.sentinel.next;
			sentinel.prev = other.sentinel.prev;
			size = other.size;
			other.clear();
		}
		// both not empty
		else
		{
			Element p1 = sentinel.next;
			Element p2 = other.sentinel.next;
			while (p1 != sentinel && p2 != other.sentinel)
			{
				if (((Comparable<E>) p1.object).compareTo(p2.object) <= 0)
				{
					p1 = p1.next;
				}
				else
				{
					p2 = p2.next;
					p1.prev.addAfter(p2.prev);
				}
			}
			while (p2 != other.sentinel)
			{
				p2 = p2.next;
				p1.prev.addAfter(p2.prev);
			}
			size += other.size;
			other.clear();
		}
		
	}
	
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public void removeAll(E e)
	{
		
		Element p = getElement(e);
		if (p == null) return;
		while (p != sentinel && ((Comparable) p.object).compareTo(e) == 0)
		{
			p.remove();
			p = p.next;
			size--;
		}
		
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
	
	
	@Override
	public boolean equals(Object obj)
	{
		
		return ((Link) obj).ref.equalsIgnoreCase(ref);
		
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
	
	public Document(String name)
	{
		
		this.name = name;
		
	}
	
	
	public Document(String name, Scanner scan)
	{
		
		this.name = name.toLowerCase();
		link = new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
		
	}
	
	
	public void load(Scanner scan)
	{
		
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
	public static Link createLink(String link)
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
	
	
	public String getName()
	{
		
		return name;
		
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
