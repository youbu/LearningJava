package com.yangwu.entity;

public class LinkNode<E> {
	private LinkNode<E> next;

	private E value;

	public LinkNode(E value) {
		this.setValue(value);
		this.setNext(null);
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public LinkNode<E> getNext() {
		return next;
	}

	public void setNext(LinkNode<E> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
}
