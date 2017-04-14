package com.hebeu.sequencelist;
/**
 * 顺序表
 * @author zhaol
 *
 * @param <T>
 */
public class SequenceList<T> {
	private Object[] element;
	private int size;
	
	public int size() {
		return size;
	}
	
	public SequenceList() {
		this(10);
	}
	
	public SequenceList(int initSize) {
		if(initSize > 0) {
			element = new Object[initSize];
		}else{
			try {
				throw new Exception("initSize不能小于等于0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void rangeCheck(int index) {
		if(index < 0 || index >= size) {
			try {
				throw new Exception("索引不能小于0或大于等于size");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//扩容
	private void expandCapacity(int miniumCapacity) {
		Object[] newCapacity = new Object[miniumCapacity*2+1];
		System.arraycopy(element, 0, newCapacity, 0, element.length);
		element = newCapacity;
	}
	
	public void add(T t) {
		if(size == element.length) expandCapacity(element.length);
		element[size++] = t;
	}
	
	public void add(int index, T t) {
		rangeCheck(index);
		if(size == element.length) expandCapacity(element.length);
		System.arraycopy(element, index, element, index + 1, size - index);
		element[index] = t;
		size++;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		rangeCheck(index);
		return (T) element[index];
	}
	
	@SuppressWarnings("unchecked")
	public T set(int index, T t) {
		rangeCheck(index);
		T tValue = (T) element[index];
		element[index] = t;
		return tValue;
	}
	
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		rangeCheck(index);
		T tValue = (T) element[index];
		System.arraycopy(element, index + 1, element, index, size - index - 1);
		/*for(int i = index;i < size;i++) {
			element[i] = element[i+1];
		}*/
		element[--size] = null;
		return tValue;
	}
	
	public boolean remove(T t) {
		for(int i=0;i<size;i++) {
			if(t.equals(element[i])) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		SequenceList<Integer> list = new SequenceList<Integer>(3);
		
		list.add(1);
		list.add(3);
		list.add(1);
		list.add(2, 2);
		list.set(0, 2);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
	}
	
}
