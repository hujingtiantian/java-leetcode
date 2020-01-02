package github.leetcode.最长上升子序列;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by hujingtian on 2019/10/19.
 */
public class LIS {

    public static void main(String[] args){
        int[] a = new int[]{2,2};
        lisSearch(a);
    }


    public static int lengthOfLIS(int[] nums) {
        int[] dyArr = new int[nums.length];
        int maxVal = 0;
        for(int i = 0 ; i < nums.length; i++){
            int tempVal = 0;
            for(int j = 0 ; j < i;j++){
                if(nums[i] > nums[j]){
                    tempVal = Math.max(dyArr[j],tempVal);
                }
            }
            dyArr[i] = tempVal +1;
            if(dyArr[i] > maxVal){
                maxVal = dyArr[i];
            }
        }
        return maxVal;
    }

    public static int lisSearch(int[] nums){
        int length = 1;
        if(nums.length == 0){
            return 1;
        }
        int[] dyArr = new int[nums.length];
        dyArr[0] = nums[0];
        for (int i = 1 ; i < nums.length ; i++){
            int num = Arrays.binarySearch(dyArr, 0, length, nums[i]);
            if(num == length){
                length = num+1;
            }
            dyArr[num] = nums[i];
        }
        return length;
    }

    private static int search(int[] dyArr, int length,int value) {
        for(int i = 0 ; i < length; i++){
            if(value <= dyArr[i]){
                return i;
            }
        }
        return length;
    }


    public static class Solution {
        public static int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int len = 0;
            for (int num : nums) {
                int i = Arrays.binarySearch(dp, 0, len, num);
                if (i < 0) {
                    i = -(i + 1);
                }
                dp[i] = num;
                if (i == len) {
                    len++;
                }
            }
            return len;
        }
    }

}
