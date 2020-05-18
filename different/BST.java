package different;

import java.util.LinkedList;

public class BST<T extends Comparable<T>>
{
	
	private class Node
	{
		
		T value;
		Node left, right, parent;
		
		public Node(T v)
		{
			
			value = v;
			
		}
		
		
		public Node(T value, Node left, Node right, Node parent)
		{
			
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
			
		}
		
	}
	
	public Node root = null; // ch pr-pub
	public int size;
	private LinkedList<T> output;
	// private final Comparator<T> comparator;
	
	/*
	 * public BST(Comparator<T> comp){ comparator=comp; root=null; }
	 */
	public Node getNode(T toFind)
	{
		
		if (size == 0)
		{ // null?
			return null;
		}
		Node curNode = root;
		while (curNode != null)
		{
			if (toFind.compareTo(curNode.value) == 0)
			{
				return curNode;
			}
			else if (toFind.compareTo(curNode.value) < 0)
			{
				curNode = curNode.left;
			}
			else
			{ // >=
				curNode = curNode.right;
			}
		}
		return null;
		
	}
	
	
	public T getElement(T toFind)
	{
		
		return (getNode(toFind) == null) ? null : getNode(toFind).value;
		
	}
	
	
	public Node successorNode(T elem)
	{
		
		if (size == 0 || size == 1)
		{
			return null;
		}
		Node curNode = getNode(elem);
		if (curNode == null)
		{
			return null;
		}
		if (curNode.right != null)
		{
			curNode = curNode.right;
			while (curNode.left != null)
			{
				curNode = curNode.left;
			}
			return curNode;
		}
		Node parent = curNode.parent;
		while (parent != null && curNode == parent.right)
		{
			curNode = parent;
			parent = parent.parent;
		}
		return parent;
		
		// return (parent==null)?null:parent;
	}
	
	
	public T successor(T elem)
	{
		
		return (successorNode(elem) == null) ? null : successorNode(elem).value;
		
	}
	
	
	public String toStringInOrder()
	{
		
		String retStr = "";
		output = new LinkedList<T>();
		inOrder(root);
		if (output.size() == 0)
		{
			return retStr;
		}
		for (T node : output)
		{
			
			retStr += node.toString();
			retStr += ", ";
		}
		retStr = retStr.substring(0, retStr.length() - 2);
		return retStr;
		
	}
	
	
	public void inOrder(Node node)
	{
		
		if (node == null)
		{
			return;
		}
		inOrder(node.left);
		output.add(node.value);
		inOrder(node.right);
		
	}
	
	
	public String toStringPreOrder()
	{
		
		String retStr = "";
		output = new LinkedList<T>();
		preOrder(root);
		if (output.size() == 0)
		{
			return retStr;
		}
		for (T node : output)
		{
			retStr += node.toString();
			retStr += ", ";
		}
		retStr = retStr.substring(0, retStr.length() - 2);
		return retStr;
		
	}
	
	
	public void preOrder(Node node)
	{
		
		if (node == null)
		{
			return;
		}
		output.add(node.value);
		preOrder(node.left);
		preOrder(node.right);
		
	}
	
	
	public String toStringPostOrder()
	{
		
		String retStr = "";
		output = new LinkedList<T>();
		postOrder(root);
		if (output.size() == 0)
		{
			return retStr;
		}
		for (T node : output)
		{
			retStr += node.toString();
			retStr += ", ";
		}
		retStr = retStr.substring(0, retStr.length() - 2);
		return retStr;
		
	}
	
	
	public void postOrder(Node node)
	{
		
		if (node == null)
		{
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		output.add(node.value);
		
	}
	
	
	public boolean add(T elem)
	{ // exception?
		
		Node newNode = new Node(elem);
		if (size == 0)
		{
			root = newNode;
			size++;
			return true;
		}
		Node temp = null;
		Node curNode = root;
		while (curNode != null)
		{
			temp = curNode;
			if (newNode.value.compareTo(curNode.value) < 0)
			{
				curNode = curNode.left;
			}
			else
			{ // >=
				curNode = curNode.right;
			}
		}
		if (newNode.value.equals(temp.value))
		{
			// temp.value=newNode.value;
			return false; // without ch size or excep
		}
		newNode.parent = temp;
		
		if (newNode.value.compareTo(temp.value) < 0)
		{
			temp.left = newNode;
		}
		else if (newNode.value.compareTo(temp.value) > 0)
		{
			temp.right = newNode;
		}
		size++;
		return true;
		
	}
	
	
	public T remove(T value)
	{
		
		if (size == 1)
		{
			clear();
			return value;
		}
		Node toRemove = getNode(value);
		
		if (toRemove == null || value == null)
		{
			return null;
		}
		T retValue = toRemove.value;
		root = remove(root, value);
		size--;
		return retValue;
		
	}
	
	
	public Node remove(Node node, T value)
	{
		
		if (value.compareTo(node.value) < 0)
		{ // node to delete is in left subtree
			node.left = remove(node.left, value);
		}
		else if (value.compareTo(node.value) > 0)
		{ // node to delete is in right subtree
			node.right = remove(node.right, value);
		}
		else
		{ // node to delete is a root
			if (node.left == null && node.right == null)
			{ // node to delete is a leaf
				// removeLeaf(node);
				node = null;
				return node;
			}
			
			if (node.left == null && node.right != null)
			{ // root without left subtree
				return node.right;
			}
			else if (node.right == null && node.left != null)
			{ // root without right subtree
				return node.left;
			}
			else
			{
				T inOrderSuccessor = successor(node.value); // root with both subtree
				node.value = inOrderSuccessor;
				node.right = remove(node.right, node.value);
			}
		}
		return node;
		
	}
	
	
	/*
	 * public T remove(Node node,T value) { if(size==1) { clear(); return value; }
	 * Node toRemove=getNode(value); if(toRemove==null || value==null) { return
	 * null; }
	 * 
	 * T retValue=toRemove.value; if(toRemove.left==null && toRemove.right==null) {
	 * //0 removeLeaf(toRemove); size--; return retValue; } if(toRemove.left==null
	 * && toRemove.right!=null) { //1 r if (toRemove.parent==null){
	 * root=toRemove.right; } else { toRemove.right.parent=toRemove.parent;
	 * if(toRemove.value.compareTo(toRemove.parent.value)<0) {
	 * toRemove.parent.left=toRemove.right;
	 * 
	 * } else { toRemove.parent.right=toRemove.right; } } } else
	 * if(toRemove.right==null && toRemove.left!=null) { //1 l if
	 * (toRemove.parent==null){ root=toRemove.left; } else {
	 * toRemove.left.parent=toRemove.parent;
	 * if(toRemove.value.compareTo(toRemove.parent.value)>0) {
	 * toRemove.parent.right=toRemove.left; } else {
	 * toRemove.parent.left=toRemove.left; } } } else { Node
	 * succ=successorNode(value); //2 toRemove.value=succ.value; remove(succ.value);
	 * // removeLeaf(succ);
	 * 
	 * } size--; return retValue; } public void removeLeaf(Node leaf) {
	 * if(leaf.value.compareTo(leaf.parent.value)<0) { leaf.parent.left=null; } else
	 * { leaf.parent.right=null;
	 * 
	 * } leaf.parent=null; }
	 * 
	 */
	public void clear()
	{
		
		root = null;
		size = 0;
		
	}
	
	
	public int size()
	{
		
		return size;
		
	}
	
}
