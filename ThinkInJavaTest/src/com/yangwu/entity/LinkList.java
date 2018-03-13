package com.yangwu.entity;

public class LinkList<E> {
	private LinkNode<E> first;

	private int size;

	private LinkNode<E> last;

	public LinkList(LinkNode<E> node) {
		this.add(node);
	}

	public void add(LinkNode<E> node) {
		if (this.first == null) {
			this.first = node;
			this.last = node;
		} else {
			this.last.setNext(node);
			this.last = node;
		}
		size++;
	}

	public void removeLast() {
		this.removeAt(size - 1);
	}

	public void removeFirst() {
		this.removeAt(0);
	}

	public void removeAt(int index) {
		if (index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		// TODO
	}

	public void reserve() {
		LinkNode<E> pre = null;
		LinkNode<E> head = this.first;
		LinkNode<E> next = this.first.getNext();

		while (head != null) {
			
			head.setNext(pre);
			pre = head;
			head = next;
			if(next != null) {
				next = next.getNext();
			}
		}
		this.last = this.first;
		this.first = pre;
	}

	public LinkNode<E> getFirst() {
		return first;
	}

	public void setFirst(LinkNode<E> first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		LinkNode<E> node = this.first;
		while (node != null) {
			res.append(node.toString() + "-->");
			node = node.getNext();
		}

		return res.toString().equals("") ? "" : res.substring(0, res.length() - 3);
	}

	public LinkNode<E> getLast() {
		return last;
	}

	public void setLast(LinkNode<E> last) {
		this.last = last;
	}
}
