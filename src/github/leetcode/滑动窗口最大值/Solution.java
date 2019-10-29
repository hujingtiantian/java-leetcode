package github.leetcode.滑动窗口最大值;

import java.util.*;

/**
 * Created by hujingtian on 2019/10/28.
 * 双端队列
 * 这道题做的有点问题  双端队列存储的应该是下标而不是值 切记
 */
public class Solution {

    public static void main(String[] args){
        //nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
        //[1,3,1,2,0,5] 3
        int[] arr = new int[]{1,-1};
        new Solution().maxSlidingWindow(arr,1);
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return nums;
        }
        int[] retArr = new int[nums.length - k + 1];
        Deque<List<Integer>> queue = new LinkedList<>();
        for(int i = 0 ; i < k && i < nums.length ; i ++){
            if(queue.size() == 0){
                List addList = new ArrayList<Integer>();
                addList.add(i);
                addList.add(nums[i]);
                queue.add(addList);
            }else {
                while (queue.size() > 0 && queue.getLast().get(1) < nums[i]){
                    queue.pollLast();
                }
                List addList = new ArrayList<Integer>();
                addList.add(i);
                addList.add(nums[i]);
                queue.add(addList);
            }
        }
        retArr[0] = queue.getFirst().get(1);
        if(queue.getFirst().get(0) == 0){
            queue.pollFirst();
        }
        for(int i = k ; i < nums.length ; i++){
            if(queue.size() != 0 && queue.getFirst().get(0) == i-k){
                queue.pollFirst();
            }
            while (queue.size() > 0 && queue.getLast().get(1) < nums[i]){
                queue.pollLast();
            }
            List addList = new ArrayList<Integer>();
            addList.add(i);
            addList.add(nums[i]);
            queue.add(addList);
            if(queue.getFirst().get(0) >= i+1-k){
                retArr[i-k+1] = queue.getFirst().get(1);
            }
        }
        return retArr;
    }
}
