package github.leetcode.组合总和;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Created by hujingtian on 2019/12/30.
 */
public class Solution {

    public static void main(String[] args){
        new Solution().combinationSum(new int[]{2,3,6,7},7);
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> targetList = new ArrayList<List<Integer>>();
        if(candidates.length == 0){
            return targetList;
        }
        Arrays.sort(candidates);
        recursive(targetList,candidates,0,target,new Stack<Integer>());
        return targetList;
    }

    /**
     * @param targetList 返回list
     * @param candidates 原数组
     * @param index 下标
     * @param target 结果值
     * @param stack 存储当前结果
     */
    private void recursive(List<List<Integer>> targetList, int[] candidates, int index, int target, Stack stack) {
        if(target == 0){
            targetList.add(new ArrayList<>(stack));
            return;
        }
        for(int i = index ; i < candidates.length ; i++){
            if(target - candidates[i] >= 0){
                stack.push(candidates[i]);
                recursive(targetList,candidates,i,target-candidates[i],stack);
                // 回溯的核心思想点在于  下一次循环的开始之前，将这一次循环的数据删掉。
                stack.pop();
            }else {
                break;
            }
        }
    }
}
