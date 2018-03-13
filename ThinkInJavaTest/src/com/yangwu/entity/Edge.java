package com.yangwu.entity;

public class Edge {
	private Node start;

	private Node dest;

	private int weight;

	public Edge(Node start, Node dest, int weight) {
		this.start = start;
		this.dest = dest;
		this.weight = weight;
	}

	public Edge(Node node, Node node2) {
		this(node, node2, 0);
	}

	public Node getStart() {
		return start;
	}

	public void setStart(Node start) {
		this.start = start;
	}

	public Node getDest() {
		return dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String toString() {
		return start.getNodeName() + "(" + start.getInDegree() + ") --> " + dest.getNodeName() + "("
				+ dest.getInDegree() + ")"; 
				//+ "(" + weight + ")";
	}
}
