package com.hebeu.singlecyclelinkedlist;
/**
 * 单向循环链表
 * @author zhaol
 *
 */
public class SingleCycleLinkedList<T> {
	//头结点对象
	private Node<T> first;
	//当前结点对象
	private Node<T> last;
	private int size;
	
	public int size() {
		return size;
	}
	
	private void rangeCheck(int index) {
		if(index < 0 || index >= size)
			try {
				throw new Exception("索引不能小于0或者大于等于size");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private Node<T> node(int index){
		Node<T> temp = null;
		if(first == null) return null;
		temp = first;
		for(int i=0;i<index;i++) {
			temp = temp.getNext();
		}
		return temp;
	}
	
	public void add(T t) {
		Node<T> node = new Node<>();
		node.setT(t);
		if(first == null) {
			node.setNext(node);
			
			first = node;
			last = node;
		}else {
			node.setNext(first);
			last.setNext(node);
			
			last = node;
		}
		size++;
	}
	
	public void add(int index, T t){
		rangeCheck(index);
		Node<T> node = new Node<>();
		node.setT(t);
		
		Node<T> temp = node(index);
		if(temp == first) {
			node.setNext(first);
			last.setNext(node);
			first = node;
			size++;
		}else{
			Node<T> prev = node(index-1);
			prev.setNext(node);
			node.setNext(temp);
			size++;
		}	
	}
	
	public T get(int index){
		rangeCheck(index);
		Node<T> temp = node(index);
		return temp.getT();
	}
	
	public Node<T> getNode(int index){
		rangeCheck(index);
		Node<T> temp = node(index);
		return temp;
	}
	
	public Node<T> remove(int index){
		rangeCheck(index);
		Node<T> temp = node(index);
		if(temp == first){
			Node<T> next = node(index+1);
			last.setNext(next);
			first = next;
			size--;
		}else{
			Node<T> prev = node(index - 1);
			Node<T> next = temp.getNext();
			prev.setNext(next);
			size--;
		}
		return temp;
	}
	
	public boolean remove(T t){
		Node<T> temp = null;
		for(int i=0;i<size;i++){
			temp = node(i);
			if(temp.getT().equals(t)){
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void set(int index, T t){
		rangeCheck(index);
		Node<T> node = new Node<>();
		node.setT(t);
		
		Node<T> temp = node(index);
		if(temp == first) {
			Node<T> next = first.getNext();
			last.setNext(node);
			node.setNext(next);
			first = node;
		}else{
			Node<T> prev = node(index-1);
			Node<T> next = temp.getNext();
			node.setNext(next);
			prev.setNext(node);
		}
	}
	
	public boolean contains(T t){
		Node<T> temp = null;
		for(int i=0;i<size;i++){
			temp = node(i);
			if(temp.getT().equals(t)) return true;
		}
		return false;
	}
	/**
	 * 不存在t返回-1
	 * 存在返回索引
	 * @param t
	 * @return
	 */
	public int indexOf(T t){
		Node<T> temp = null;
		for(int i=0;i<size;i++){
			temp = node(i);
			if(temp.getT().equals(t)) 
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		SingleCycleLinkedList<String> list = new SingleCycleLinkedList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(0, "111");
		//list.remove("m");
		list.set(0, "222");
		System.out.println(list.indexOf("c"));
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("获取最后一个node从最后一个结点获得头结点："+list.getNode(list.size()-1).getNext().getT());
		
	}
	
}
