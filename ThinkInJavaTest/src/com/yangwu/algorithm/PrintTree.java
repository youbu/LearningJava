package com.yangwu.algorithm;

import java.util.ArrayList;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * 
 * @author Administrator
 *
 */
public class PrintTree {
	public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<TreeNode> queue = new ArrayList<>();
		TreeNode temp = null;
		// 根节点
		queue.add(root);

		while (!queue.isEmpty()) {
			 temp = queue.remove(0);
			 if (temp.left != null) {
				queue.add(temp.left);
			}
			 if (temp.right != null) {
				queue.add(temp.right);
			}
			 list.add(temp.val);
		}

		return list;
	}

}
