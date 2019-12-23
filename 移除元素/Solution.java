package github.leetcode.移除元素;

/**
 * Created by hujingtian on 2019/12/17.
 */
public class Solution {


    public static void main(String[] args){
        int[] nums = new int[]{1};
        new Solution().removeElement(nums,1);
    }

    public int removeElement(int[] nums, int val) {
        int leftIndex = 0;
        int rightIndex = nums.length-1;
        if(leftIndex == rightIndex){
            if(nums[leftIndex] == val){
                return 0;
            }else {
                return 1;
            }
        }
        while (leftIndex <= rightIndex){
            if(nums[leftIndex] == val){
                int temp = nums[leftIndex];
                nums[leftIndex] = nums[rightIndex];
                nums[rightIndex] = temp;
                rightIndex--;
            }else {
                leftIndex++;
            }
        }
        return leftIndex;
    }

}
