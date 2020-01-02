package github.leetcode.最大子序和;

/**
 * Created by hujingtian on 2019/10/28.
 */
public class Solution {

    public static void main(String[] args){

    }

    public int maxSubArray(int[] nums) {
        int maxNum = nums[0];
        int tempNum = 0;
        for (int num : nums){
            if(tempNum > 0){
                tempNum = tempNum + num;
            }else {
                tempNum = num;
            }
            maxNum = Math.max(tempNum,maxNum);
        }
        return maxNum;
    }
}
