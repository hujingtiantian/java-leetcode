package github.leetcode.前K个高频元素;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by hujingtian on 2019/10/28.
 */
public class Solution {

    public static void main(String[] args){
        //[1,1,1,2,2,3]
        int[] nums = new int[]{1,1,1,2,2,3};
        new Solution().topKFrequent(nums,2);
    }


    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
        for (int num : nums){
            hashMap.put(num,(int)hashMap.getOrDefault(num,0)+1);
        }
        PriorityQueue<CompareData> priorityQueue = new PriorityQueue();
        hashMap.forEach((key,value)->{
            priorityQueue.add(new CompareData(key,value));
        });
        List<Integer> retList = new ArrayList<>();
        for(int i = 0 ; i < k ; i++){
            retList.add(priorityQueue.poll().key);
        }
        return retList;
    }


    class CompareData implements Comparable<CompareData>{

        Integer key;

        Integer value;

        public CompareData(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public int compareTo(CompareData o) {
            if(this.getValue() < o.getValue()){
                return 1;
            }else if(this.getValue() > o.getValue()){
                return -1;
            }
            return 0;
        }
    }
}
