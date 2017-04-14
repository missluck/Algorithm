package com.hebeu.doublycyclelinkedlist;
/**
 * 双向循环链表
 * @author zhaol
 *
 */
public class DoublyCycleLinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	
	private int size;
	
	public int size() {
		return size;
	}
	
	private void rangeCheck(int index){
		if(index < 0 || index >= size)
			try {
				throw new Exception("index不能小于0或者不能大于等于size");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private Node<T> node(int index) {
		Node<T> temp = null;
		if(first != null) {
			if(index < (size >> 1)) {
				temp = first;
				for(int i=0;i<index;i++) {
					temp = temp.getNext();
				}
			}else{
				temp = last;
				for(int i = size - 1;i > index; i--) {
					temp = temp.getPrev();
				}
			}
		}
		return temp;
	}
	
	public T get(int index){
		rangeCheck(index);
		Node<T> temp = node(index);
		return temp.getT();
	}
	
	public Node<T> getNode(int index) {
		rangeCheck(index);
		Node<T> temp = node(index);
		return temp;
	}
	
	public void add(T t){
		Node<T> node = new Node<>();
		node.setT(t);
		if(first == null){
			node.setNext(node);
			node.setPrev(node);
			
			first = node;
			last = node;
		}else{
			node.setNext(first);
			node.setPrev(last);
			
			first.setPrev(node);
			last.setNext(node);
			
			last = node;
		}
		size++;
	}
	
	public void add(int index, T t) {
		rangeCheck(index);
		Node<T> node = new Node<>();
		node.setT(t);
		
		Node<T> temp = node(index);
		if(temp == first) {
			node.setNext(first);
			node.setPrev(last);
			
			first.setPrev(node);
			last.setNext(node);
			
			first = node;
			size++;
		}else{
			Node<T> prev = temp.getPrev();
			
			node.setPrev(prev);
			node.setNext(temp);
			
			prev.setNext(node);
			temp.setPrev(node);
			size++;
		}
		
	}
	
	public void remove(int index) {
		rangeCheck(index);
		Node<T> temp = node(index);
		if(temp == first) {
			Node<T> prev = temp.getPrev();
			Node<T> next = temp.getNext();
			prev.setNext(next);
			next.setPrev(prev);
			first = next;
			size--;
			return;
		}
		if(temp == last) {
			Node<T> prev = temp.getPrev();
			Node<T> next = temp.getNext();
			prev.setNext(next);
			next.setPrev(prev);
			last = prev;
			size--;
			return;
		}
		if(temp != null) {
			Node<T> prev = temp.getPrev();
			Node<T> next = temp.getNext();
			prev.setNext(next);
			next.setPrev(prev);
			size--;
		}
	}
	
	public boolean remove(T t) {
		Node<T> temp = null;
		for(int i=0;i<size;i++) {
			temp = node(i);
			if(temp.getT().equals(t)){
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(T t) {
		Node<T> temp = null;
		for(int i=0;i<size;i++) {
			temp = node(i);
			if(temp.getT().equals(t)) 
				return true;
		}
		return false;
	}
	/**
	 * 不存在返回值为-1
	 * 存在返回索引
	 * @param t
	 * @return
	 */
	public int indexOf(T t) {
		Node<T> temp = null;
		for(int i=0;i<size;i++) {
			temp = node(i);
			if(temp.getT().equals(t))
				return i;
		}
		return -1;
	}
	
	public void set(int index, T t) {
		rangeCheck(index);
		Node<T> node = new Node<>();
		node.setT(t);
		
		Node<T> temp = node(index);
		if(temp == first) {
			Node<T> prev = temp.getPrev();
			Node<T> next = temp.getNext();
			node.setNext(next);
			node.setPrev(prev);
			
			prev.setNext(node);
			next.setPrev(node);
			first = node;
			return;
		}
		if(temp == last) {
			Node<T> prev = temp.getPrev();
			Node<T> next = temp.getNext();
			node.setNext(next);
			node.setPrev(prev);
			
			prev.setNext(node);
			next.setPrev(node);
			last = node;
			return;
		}
		if(temp != null){
			Node<T> prev = temp.getPrev();
			Node<T> next = temp.getNext();
			node.setNext(next);
			node.setPrev(prev);
			
			prev.setNext(node);
			next.setPrev(node);
			return;
		}
	}
	
	public static void main(String[] args) {
		DoublyCycleLinkedList<String> list = new DoublyCycleLinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.set(1, "111");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("获取双向循环链表第一个结点，取上一个结点值"+list.getNode(0).getPrev().getT());
		System.out.println("获取双向循环链表最后一个结点，取下一结点值："+list.getNode(list.size() - 1).getNext().getT());
	}
	
}
