package com.yangwu.test;

import java.util.List;

import com.yangwu.entity.Node;
import com.yangwu.util.CreateGraph;

public class GraphTest {

	public static void main(String[] args) {
		List<Node>graph = CreateGraph.createGraph();
		CreateGraph.sort(graph);
	}

}
