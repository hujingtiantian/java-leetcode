package github.leetcode.周赛.第160场周赛.串联字符串的最大长度;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hujingtian on 2019/10/27.
 */
//TODO:
public class Solution {

    public static void main(String[] args){
        List<String > arrList = new ArrayList<>();
        arrList.add("un");
        arrList.add("iq");
        arrList.add("ue");
        new Solution().maxLength(arrList);
    }

    public int maxLength(List<String> arr) {
        return dfs(0, arr, "").length();
    }

    private String dfs(int index, List<String> arr, String str) {
        if (index >= arr.size()) {
            return str;
        }
        String currStr = arr.get(index);
        // 两个字符串是否完全不同
        if (isTotallyDifferent(currStr, str)) {
            String result1 = dfs(index + 1, arr, str);
            String result2 = dfs(index + 1, arr, str + currStr);
            if (result1.length() > result2.length()) {
                return result1;
            } else {
                return result2;
            }
        } else {
            return dfs(index + 1, arr, str);
        }
    }

    private boolean isTotallyDifferent(String str1, String str2) {
        HashSet<Character> charSet = new HashSet<Character>(str1.length());
        for (int i = 0; i < str1.length(); i++) {
            if (charSet.contains(str1.charAt(i))) {
                return false;
            }
            charSet.add(str1.charAt(i));
        }
        for (int i = 0; i < str2.length(); i++) {
            if (charSet.contains(str2.charAt(i))) {
                return false;
            }
        }
        return true;
    }






}
