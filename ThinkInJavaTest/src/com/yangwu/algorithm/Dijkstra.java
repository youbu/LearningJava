package com.yangwu.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yangwu.entity.Edge;
import com.yangwu.entity.Node;

public class Dijkstra {

	private Map<String, Integer> shortest = new HashMap<String, Integer>();

	private Map<String, Node> pre = new HashMap<String, Node>();

	private List<Node> graph;

	/**
	 * 初始化对象，graph的第一个节点为源点
	 * 
	 * @param graph
	 * @throws Exception
	 */
	public Dijkstra(List<Node> graph) throws Exception {
		if (graph == null || graph.size() == 0) {
			throw new Exception("The Graph is empty!");
		}
		this.graph = graph;

		String key = "";
		for (int i = 0; i < graph.size(); i++) {
			key = graph.get(i).getNodeName();
			pre.put(key, null);
			if (i == 0) {
				shortest.put(key, 0);
			} else {
				shortest.put(key, Integer.MAX_VALUE);
			}
		}
	}

	public void dijkstraSearch() {
		List<Node> list = new ArrayList<Node>();
		list.addAll(graph);
		Node nodeu = null;
		int weight;
		Node nodev = null;
		while (list.size() > 0) {
			Collections.sort(list, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return shortest.get(o1.getNodeName()) - shortest.get(o2.getNodeName());
				}
			});

			nodeu = list.get(0);
			list.remove(nodeu);

			for (Edge edge : nodeu.getEdges()) {
				weight = edge.getWeight();
				nodev = edge.getDest();

				if (shortest.get(nodev.getNodeName()) > shortest.get(nodeu.getNodeName()) + weight) {
					shortest.put(nodev.getNodeName(), shortest.get(nodeu.getNodeName()) + weight);
					pre.put(nodev.getNodeName(), nodeu);
				}
			}
		}
	}

	// private void relax(Node u, Node v) {
	// int weight = u.getWeight(v);
	// if (shortest.get(v.getNodeName()) > shortest.get(u.getNodeName()) + weight) {
	// shortest.put(v.getNodeName(), shortest.get(u.getNodeName()) + weight);
	// pre.put(v.getNodeName(), u);
	// }
	// }

	public void printResult() {
		Set<String> keys = shortest.keySet();
		for (String key : keys) {
			System.out.println("节点名 : " + key + " 距S最短距离 : " + shortest.get(key));

			if (pre.get(key) == null) {
				System.out.println("pre : " + "NULL");
			} else {
				System.out.println("pre : " + pre.get(key).getNodeName());
			}

		}

	}

	public Map<String, Integer> getShortest() {
		return shortest;
	}

	public Map<String, Node> getPre() {
		return pre;
	}

	public List<Node> getGraph() {
		return graph;
	}

	public void setGraph(List<Node> graph) {
		this.graph = graph;
	}
}
