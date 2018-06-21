package com.yangwu.algorithm;

import java.util.ArrayList;
import java.util.List;

public class DfsAndBfs {

	public static void main(String[] args) {
		DfsAndBfs main = new DfsAndBfs();

		TreeNode root = main.getTree();

		main.dfs(root);
		
		System.out.println();
		List<TreeNode>list = main.bfs(root);
		
		for(TreeNode node : list) {
			System.out.print(node.val + " , ");
		}
	}

	public TreeNode getTree() {
		TreeNode root = new TreeNode(1);

		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node11 = new TreeNode(11);
		TreeNode node55 = new TreeNode(55);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node44 = new TreeNode(44);
		TreeNode node22 = new TreeNode(22);
		// 0001
		// / \
		// 0002 0003
		// / \ / \
		// 0011 0055 0004 0005
		// / \
		// 0044 0022

		root.left = node2;
		root.right = node3;

		node2.left = node11;
		node2.right = node55;

		node11.left = node44;
		node11.right = node22;

		node3.left = node4;
		node3.right = node5;

		return root;
	}

	public List<TreeNode> bfs(TreeNode root) {
		List<TreeNode> list = new ArrayList<>();
		List<TreeNode >queue = new ArrayList<>();
		if (root == null) {
			return list;
		}

		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode temp = queue.remove(0);
			
			if (temp.left != null) {
				queue.add(temp.left);
			}
			
			if (temp.right != null) {
				queue.add(temp.right);
			}
			
			list.add(temp);
		}

		return list;
	}

	public void dfs(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " , ");

		dfs(root.left);
		dfs(root.right);
	}
}
