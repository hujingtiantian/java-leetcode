package github.leetcode.周赛.双周赛17;

/**
 * Created by hujingtian on 2020/1/11.
 */
public class 祖父节点值为偶数的节点和 {

    int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        checkRoot(root);
        return sum;
    }

    private void checkRoot(TreeNode root) {
        if(root == null){
            return;
        }
        if(root.val % 2 == 0){
            getSon(root.left);
            getSon(root.right);
        }
        checkRoot(root.left);
        checkRoot(root.right);
    }

    private void getSon(TreeNode root) {
        if(root != null){
            TreeNode left = root.left;
            TreeNode right = root.right;
            if(left != null){
                sum += left.val;
            }
            if(right != null){
                sum += right.val;
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