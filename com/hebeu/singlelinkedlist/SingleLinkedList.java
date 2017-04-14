package com.hebeu.singlelinkedlist;
/**
 * 单链表实现
 * @author zhaol
 *
 * @param <T>
 */
public class SingleLinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	
	private int size;
	
	public int size() {
		return size;
	}
	
	private void rangeCheck(int index) {
		if(index < 0 || index >= size) {
			try {
				throw new Exception("索引不能小于0或者不能大于等于size");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Node<T> node(int index) {
		Node<T> temp = null;
		if(first == null) return null;
		temp = first;
		for(int i=0;i < index;i++) {
			temp = temp.getNext();
		}
		return temp;
	}
	
	public void add(T t) {
		Node<T> node = new Node<>();
		if(first == null) {
			node.setNext(null);
			node.setT(t);
			first = node;
			last = node;
		}else{
			node.setT(t);
			node.setNext(null);
			last.setNext(node);
			last = node;
		}
		size++;
	}
	
	public void add(int index, T t) {
		rangeCheck(index);
		Node<T> node = new Node<>();
		node.setT(t);
		
		Node<T> pre = node(index-1);
		Node<T> next = node(index);
		
		node.setNext(next);
		pre.setNext(node);
		size++;
	}
	
	public T get(int index) {
		rangeCheck(index);
		Node<T> node = node(index);
		if(node == null) return null; 
		return node.getT();
	}
	
	public void set(int index, T t) {
		rangeCheck(index);
		Node<T> temp = new Node<>();
		temp.setT(t);
		
		Node<T> pre = node(index - 1);
		Node<T> next = node(index + 1);
		
		temp.setNext(next);
		pre.setNext(temp);
	}
	
	public void remove(int index) {
		Node<T> pre = node(index - 1);
		Node<T> next = node(index + 1);
		pre.setNext(next);
		size--;
	}
	
	public boolean remove(T t) {
		for(int i=0;i<size;i++) {
			Node<T> node = node(i);
			if(node.getT().equals(t)) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(T t) {
		Node<T> node = null;
		for(int i=0;i<size;i++) {
			node = node(i);
			if(node.getT().equals(t)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 不存在t返回-1
	 * 存在返回索引
	 * @param t
	 * @return
	 */
	public int indexOf(T t) {
		Node<T> node = null;
		for(int i=0;i<size;i++) {
			node = node(i);
			if(node.getT().equals(t)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		SingleLinkedList<String> list = new SingleLinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add(2, "123");
		list.set(2, "木头人");
		//list.remove(2);
		//System.out.println(list.remove("123"));
		System.out.println(list.contains("123"));
		System.out.println(list.contains("木头人"));
		System.out.println(list.get(5));
		/*for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		System.out.println("size:"+list.size());*/
	}
}
