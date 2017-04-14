package com.hebeu.singlelinkedlist;
/**
 * ������ڵ�
 * @author zhaol
 *
 */
public class Node<T> {
	private T t;
	private Node<T> next;
	
	public Node() {
		
	}

	public Node(T t, Node<T> next) {
		this.t = t;
		this.next = next;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}
