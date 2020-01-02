package github.leetcode.无重复字符的最长子串;

import java.util.*;

/**
 * Created by hujingtian on 2019/10/25.
 */
public class Solution {

    public static void main(String[] args){
        Solution lengthOfLongestSubstring = new Solution();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int maxNum = 0;
        int tempNum = 0;
        int right = 0 ;
        for (int i = 0 ; i < chars.length; i++){
            for(int j = right ; j < chars.length ; j++){
                if(set.add(chars[j])){
                    right++;
                    tempNum++;
                }else {
                    set.remove(chars[i]);
                    maxNum = Math.max(maxNum,tempNum);
                    tempNum--;
                    break;
                }
            }
        }
        maxNum = Math.max(maxNum,tempNum);
        return maxNum;
    }
}
