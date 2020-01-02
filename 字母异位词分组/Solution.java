package github.leetcode.字母异位词分组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

 示例:

 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 输出:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 说明：

 所有输入均为小写字母。
 不考虑答案输出的顺序。
 * Created by hujingtian on 2019/12/17.
 */
public class Solution {

    public static void main(String[] args){
        String[] retStr = new String[]{"eat","tea","tan","ate","nat","bat"};
        new Solution().groupAnagrams(retStr);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> hashMap = new HashMap();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if(hashMap.get(Arrays.toString(chars)) == null){
                hashMap.put(Arrays.toString(chars),new ArrayList<>());
            }
            hashMap.get(Arrays.toString(chars)).add(str);
        }
        List<List<String>> retList = new ArrayList<List<String>>();
        hashMap.keySet().stream().forEach(key->{
            retList.add(hashMap.get(key));
        });
        return retList;
    }
}
