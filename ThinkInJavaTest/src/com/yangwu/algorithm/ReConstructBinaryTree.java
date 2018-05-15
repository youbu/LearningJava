package com.yangwu.algorithm;

/**
 * ����ĳ��������ǰ���������������Ľ���� ���ؽ����ö������� ���������ǰ���������������Ľ���ж������ظ������֡���
 * ��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}�� ���ؽ������������ء�
 * 
 * @author Administrator
 */
public class ReConstructBinaryTree {

	public static void main(String[] args) {
		int[] pre={1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,8,6};
		ReConstructBinaryTree tree = new ReConstructBinaryTree();
		TreeNode node = tree.reConstructBinaryTree(pre, in);
		System.out.println();
	}
	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null || pre.length != in.length) 
             return null;
         
         return construct(pre, 0, pre.length-1, in, 0, in.length-1);
    }
    
    private TreeNode construct(int[] pre,int ps,int pe,int[] in,int is,int ie){
         if (ps >pe) {
			return null;
		}
    	int value = pre[ps];
        int index=is;
        while(index <= ie && value != in[index]){
            index++;
        }
        if(index > ie) 
             throw new RuntimeException("Invalid Iuput!");
        TreeNode node = new TreeNode(value);
        node.left = construct(pre,ps +1,ps+index-is,in,is,index-1);
        node.right = construct(pre, ps+index-is+1, pe, in, index+1, ie);
        return node;
    }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}