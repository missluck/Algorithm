package com.hebeu.doublylinkedlist;

public class DoublyLinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;
	
	public int size() {
		return size;
	}
	
	private void rangeCheck(int index) {
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
			}else {
				temp = last;
				for(int i = size-1;i > index;i--) {
					temp = temp.getPrev();
				}
			}
		}
		return temp;
	}
	
	public void add(T t) {
		Node<T> node = new Node<T>();
		if(first == null) {
			node.setT(t);
			node.setPrev(null);
			node.setNext(null);
			
			first = node;
			last = node;
		}else{
			node.setT(t);
			node.setPrev(last);
			node.setNext(null);
			
			last.setNext(node);
			last = node;
		}
		size++;
	}
	
	public void add(int index, T t){
		rangeCheck(index);
		Node<T> temp = node(index);
		
		Node<T> node = new Node<>();
		node.setT(t);
		if(temp == first) {
			node.setPrev(null);
			node.setNext(first);
			node.setT(t);
			
			first.setPrev(node);
			first = node;
			size++;
			return;
		}
		if(temp != null) {
			Node<T> prev = temp.getPrev();
			
			node.setPrev(prev);
			node.setNext(temp);
			
			prev.setNext(node);
			temp.setPrev(node);
			size++;
		}
		
	}
	
	public T get(int index){
		rangeCheck(index);
		Node<T> temp = node(index);
		if(temp == null) return null;
		return temp.getT();
	}
	
	public void remove(int index) {
		rangeCheck(index);
		Node<T> temp = node(index);
		if(temp == first) {
			Node<T> next = temp.getNext();
			next.setPrev(null);
			first = next;
			size--;
			return;
		}
		if(temp == last) {
			Node<T> prev = temp.getPrev();
			prev.setNext(null);
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
	
	public void remove(T t){
		for(int i=0;i<size;i++) {
			Node<T> temp = node(i);
			if(temp.getT().equals(t)) {
				Node<T> prev = temp.getPrev();
				Node<T> next = temp.getNext();
				if(prev == null) {
					next.setPrev(null);
					first = next;
					size--;
					return;
				}
				if(next == null) {
					prev.setNext(null);
					last = prev;
					size--;
					return;
				}
				prev.setNext(next);
				next.setPrev(prev);
				size--;
			}
		}
	}
	
	public boolean set(int index, T t){
		rangeCheck(index);
		Node<T> temp = node(index);
		Node<T> prev = temp.getPrev();
		Node<T> next = temp.getNext();
		
		Node<T> node = new Node<>();
		node.setT(t);
		
		if(prev == null) {
			//修改头
			node.setPrev(null);
			node.setNext(next);
			
			next.setPrev(node);
			first = node;
			return true;
		}else if(next == null) {
			//修改尾
			node.setPrev(prev);
			node.setNext(null);
			
			prev.setNext(node);
			last = node;
			return true;
		}else{
			node.setPrev(prev);
			node.setNext(next);
			
			prev.setNext(node);
			next.setPrev(node);
			return true;
		}
	}
	
	public boolean contains(T t) {
		for(int i=0;i<size;i++) {
			Node<T> temp = node(i);
			if(temp.getT().equals(t)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 不存在t返回-1
	 * @param t
	 * @return
	 */
	public int indexOf(T t){
		for(int i=0;i<size;i++) {
			Node<T> temp = node(i);
			if(temp.getT().equals(t)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(2,"111");
		list.set(3, "222");
		System.out.println("index:"+list.indexOf("222"));
		for(int i=0;i<list.size();i++) {			
			System.out.println(list.get(i));
		}
	}
}
