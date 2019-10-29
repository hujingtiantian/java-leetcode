package github.leetcode.april.twosum;

import java.util.HashMap;

/**
 * Created by hujingtian on 2019/2/28.
 *
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 *
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap(nums.length);
        for(int i = 0 ; i < nums.length ; i++){
            int result = target - nums[i];
            if(hashMap.containsKey(result)){
                return new int[]{i,hashMap.get(result)};
            }
            hashMap.put(nums[i],i);
        }
        throw new IllegalArgumentException();
    }
}
