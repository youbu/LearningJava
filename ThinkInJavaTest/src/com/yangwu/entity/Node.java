package com.yangwu.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Edge> edges;

	private String nodeName;

	private int inDegree;

	public Node(String name) {
		this.nodeName = name;
		this.inDegree = 0;
		this.setEdges(new ArrayList<Edge>());
	}

	/**
	 * �������й����ڵ��InDegree
	 */
	public void resetIndegree() {
		if (this.getEdges() != null) {
			for (Edge edge : this.getEdges()) {
				edge.getDest().setInDegree(edge.getDest().getInDegree() + 1);

			}
		}
	}

	public int getWeight(Node dest) {
		int weight = Integer.MAX_VALUE;
		if (this.equals(dest)) {
			weight = 0;
		} else {
			for (Edge edge : this.edges) {
				if (edge.getDest().equals(dest)) {
					weight = edge.getWeight();
					break;
				}
			}
		}
		return weight;
	}

	/**
	 * �Ƴ��ýڵ�����б�,ֻ�������Ϊ0���������Ч
	 */
	public void removeEdges() {
		if (this.getEdges() != null && this.inDegree == 0) {
			for (Edge edge : this.getEdges()) {
				edge.getDest().setInDegree(edge.getDest().getInDegree() - 1);
			}
		}
	}

	/**
	 * ���һ����
	 * 
	 * @param node
	 *            �ߵ��յ�
	 */
	public void addNext(Node node) {
		Edge edge = new Edge(this, node);
		this.getEdges().add(edge);
		node.setInDegree(node.getInDegree() + 1);
	}

	public void addNext(Node node, int weight) {
		Edge edge = new Edge(this, node, weight);
		this.getEdges().add(edge);
		node.setInDegree(node.getInDegree() + 1);
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

}
