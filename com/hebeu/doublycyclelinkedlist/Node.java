package com.hebeu.doublycyclelinkedlist;

public class Node<T> {
	private Node<T> prev;
	private T t;
	private Node<T> next;
	public Node() {
		
	}
	public Node(Node<T> prev, T t, Node<T> next) {
		this.prev = prev;
		this.t = t;
		this.next = next;
	}
	public Node<T> getPrev() {
		return prev;
	}
	public void setPrev(Node<T> prev) {
		this.prev = prev;
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
