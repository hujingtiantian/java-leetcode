package github.leetcode.april.lengthOfLongestSubstring;

import java.util.*;

/**
 * Created by hujingtian on 2019/4/15.
 *
 * @Description : 取出一个字符串中最长的不重复字符串
 *
 * 1. 看到这道题的第一眼 想法是暴力法  遍历所有的字符串获取最长的长度
 * 2. 暴力法是遍历了所有的字符串 但是考虑一个问题 abcdefagh  当abcdef遇到a时  暴力法是重新从b开始遍历到bcdefa  实际上我们只需要移除a 初始化字符串是bcdef
 * 3.
 *
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring1(String s) {
        int flag = 1;
        if(s == null || "".equals(s)){
            return 0;
        }
        char[] chars = s.toCharArray();
        List<HashSet> setList = new ArrayList();
        List<Integer> numberList = new ArrayList();
        for(int i = 0 ; i < chars.length ; i ++){
            HashSet set = new HashSet();
            setList.add(set);

            for(Iterator<HashSet> iterator = setList.iterator(); iterator.hasNext();){
                HashSet hashSet = iterator.next();
                if(!hashSet.add(chars[i])){
                    numberList.add(hashSet.size());
                    iterator.remove();
                }
                if(i == chars.length - 1){
                    numberList.add(hashSet.size());
                }
            }

        }
        for(int i = 0 ; i < numberList.size()-1 ; i++){
            int x = numberList.get(i);
            int y = numberList.get(i+1);
            if( x >= y && x > flag){
                flag = x;
            }else if(y >= x && y > flag ){
                flag = y;
            }
        }
        return flag;
    }


    public int lengthOfLongestSubstring2(String s) {
        int flag = 0;
        int length = s.length();
        int i = 0;
        int j = 0;
        Set<Character> set = new HashSet<>();
        while (i < length && j<length){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                flag = Math.max(flag,j -i );
            }else {
                set.remove(s.charAt(i++));
            }
        }
        return flag;
    }


    public int lengthOfLongestSubstring3(String s) {
        int flag = 0;
        return flag;
    }


    public static void main(String[] args){
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring2("pwwkew"));
    }
}
