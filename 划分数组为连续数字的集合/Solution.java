package github.leetcode.划分数组为连续数字的集合;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hujingtian on 2019/12/23.
 */
public class Solution {

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,3,4,4,5,6};
        new Solution().isPossibleDivide(nums,4);
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return false;
        }
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(list);
        while (list.size() > 0){
            int begin = list.get(0);
            int start = 0;
            while (start<k){
               if(list.remove(new Integer(begin)) == false){
                   return false;
               }
               begin++;
               start++;
           }
        }
        return true;
    }
}
