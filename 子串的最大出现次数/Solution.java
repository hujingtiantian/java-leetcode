package github.leetcode.子串的最大出现次数;

import java.util.*;

/**
 * Created by hujingtian on 2019/12/23.
 */
public class Solution {

    public static void main(String[] args){
        new Solution().maxFreq("aababcaab",2,3,4);
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        if(s == null || s.length() < minSize){
            return 0;
        }
        int start = 0;
        HashMap<String,Integer> hashMap = new HashMap<>();
        while (start + minSize <= s.length()){
            hashMap.put(s.substring(start,start+minSize),hashMap.getOrDefault(s.substring(start,start+minSize),0)+1);
            start++;
        }
        int sum = 0;
        for (String str : hashMap.keySet()){
            if(checkStr(str,maxLetters)){
                if (hashMap.get(str) > sum) {
                    sum = hashMap.get(str);
                }
            }
        }
        return sum;
    }

    private boolean checkStr(String str, int maxLetters) {
        HashSet<Character> set = new HashSet();
        char[] chars = str.toCharArray();
        for(char c : chars){
            set.add(c);
        }
        return set.size() <= maxLetters;
    }
}
