package github.leetcode.统计位数为偶数的数字;

/**
 * Created by hujingtian on 2019/12/23.
 */
public class Solution {

    public int findNumbers(int[] nums) {
        int count = 0;
        for(int i = 0 ; i < nums.length ; i++){
            Integer num = nums[i];
            if(String.valueOf(num).length() % 2 == 0){
                count++;
            }
        }
        return count;
    }
}
