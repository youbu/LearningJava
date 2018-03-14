package com.yangwu.util;

import java.util.ArrayList;
import java.util.List;

import com.yangwu.entity.Edge;
import com.yangwu.entity.Node;

public class CreateGraph {

	public static List<Node> createGraph() {
		List<Node> res = new ArrayList<Node>();

		Node blocker = new Node("blocker");
		res.add(blocker);

		Node catch_glove = new Node("catch glove");
		res.add(catch_glove);

		Node leg_pads = new Node("leg pads ");
		res.add(leg_pads);

		Node skates = new Node("skates");
		res.add(skates);

		Node mask = new Node("mask");
		res.add(mask);

		Node pants = new Node("pants");
		res.add(pants);

		Node sweater = new Node("sweater");
		res.add(sweater);

		Node hose = new Node("hose");
		res.add(hose);

		Node cup = new Node("cup");
		res.add(cup);

		Node chest_pad = new Node("chest  pad");
		res.add(chest_pad);

		Node Tshirt = new Node("T-shirt");
		res.add(Tshirt);

		Node socks = new Node("socks");
		res.add(socks);

		Node compression_shorts = new Node("compression shorts");
		res.add(compression_shorts);

		Node undershorts = new Node("undershorts");
		res.add(undershorts);

		undershorts.addNext(compression_shorts);
		compression_shorts.addNext(hose);
		compression_shorts.addNext(cup);
		hose.addNext(pants);
		cup.addNext(pants);
		pants.addNext(skates);
		skates.addNext(leg_pads);
		socks.addNext(hose);
		pants.addNext(sweater);
		leg_pads.addNext(catch_glove);
		Tshirt.addNext(chest_pad);
		chest_pad.addNext(sweater);
		sweater.addNext(mask);
		mask.addNext(catch_glove);
		catch_glove.addNext(blocker);

		return res;
	}

	public static void printGraph(List<Node>list) {
		List<Edge> edges;

		for (Node node : list) {
			edges = node.getEdges();
			for (Edge edge : edges) {
				System.out.println(edge.toString() + "  :  " + edge.getWeight());
			}
		}
	}

	/**
	 * Õÿ∆À≈≈–Ú
	 * @param list
	 */
	public static void sort(List<Node> list) {
		List<Node> sortedList = new ArrayList<Node>();
		List<Node> temp = new ArrayList<Node>();
		while (list.size() > 0) {
			temp.clear();
			for (Node node : list) {
				if (node.getInDegree() == 0) {
					sortedList.add(node);
					node.removeEdges();
					temp.add(node);
				}
			}
			list.removeAll(temp);
		}

		for (int i = 0; i < sortedList.size(); i++) {
			if (i == sortedList.size() - 1) {
				System.out.print(sortedList.get(i).getNodeName());
			} else {
				System.out.print(sortedList.get(i).getNodeName() + " --> ");
			}
		}

	}

	/**
	 * ”–»®Õº
	 * @return
	 */
	public static List<Node> getWeightGraph() {
		List<Node> list = new ArrayList<Node>();

		Node nodeS = new Node("S");
		Node nodeT = new Node("T");
		Node nodeX = new Node("X");
		Node nodeY = new Node("Y");
		Node nodeZ = new Node("Z");

		list.add(nodeS);
		list.add(nodeT);
		list.add(nodeX);
		list.add(nodeY);
		list.add(nodeZ);
		
		nodeS.addNext(nodeT, 6);
		nodeS.addNext(nodeY, 4);

		nodeT.addNext(nodeX, 3);
		nodeT.addNext(nodeY, 2);

		nodeX.addNext(nodeZ, 4);

		nodeZ.addNext(nodeX, 5);
		nodeZ.addNext(nodeS, 7);

		nodeY.addNext(nodeT, 1);
		nodeY.addNext(nodeX, 9);
		nodeY.addNext(nodeZ, 3);

		return list;
	}
}
