package github.leetcode.两棵二叉搜索树中的所有元素;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hujingtian on 2019/12/29.
 */
public class Solution {

    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(5);
        treeNode2.left = new TreeNode(3);
        treeNode2.right = new TreeNode(6);
        new Solution().getAllElements(treeNode,treeNode2);
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<Integer>();
        digui(root1,list1);
        List<Integer> list2 = new ArrayList<Integer>();
        digui(root2,list2);
        list1.addAll(list2);
        if(list1.size() != 0){
            Collections.sort(list1);
        }
        return list1;
    }

    private void digui(TreeNode root1,List list) {
        if(root1 != null){
            list.add(root1.val);
           if(root1.left != null){
                digui(root1.left,list);
           }
           if(root1.right != null){
                digui(root1.right,list);
           }
        }
    }
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}