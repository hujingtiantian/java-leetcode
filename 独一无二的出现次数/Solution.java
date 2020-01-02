package github.leetcode.独一无二的出现次数;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hujingtian on 2019/10/23.
 */
public class Solution {

    public static void main(String[] args){
        new Solution().uniqueOccurrences(new int[]{1,2});
    }
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer,Integer> hashMap = new HashMap();
        Set numSet = new HashSet();
        for(int num : arr){
            hashMap.put(num,hashMap.getOrDefault(num,0)+1);
        }
        for(Integer num : hashMap.keySet()){
            if(!numSet.add(hashMap.get(num))){
                return false;
            }
        }
        return true;
    }
}
