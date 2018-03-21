package com.yangwu.test;

import java.util.List;

import com.yangwu.algorithm.Dijkstra;
import com.yangwu.entity.Node;
import com.yangwu.util.CreateGraph;

public class DijkstraTest {

	public static void main(String[] args) {
		List<Node>list = CreateGraph.getWeightGraph();
		
		try {
			Dijkstra dijkstra = new Dijkstra(list);
			dijkstra.dijkstraSearch();
			dijkstra.printResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
