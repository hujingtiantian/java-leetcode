package github.leetcode.解码方法;

/**
 * Created by hujingtian on 2019/12/24.
 */
public class Solution {

    public static void main(String[] args){
        new Solution().numDecodings("31");
    }

    public int numDecodings(String s) {
       s = s.replaceAll("10","a");
       s = s.replaceAll("20","a");
       if(s.contains("0")){
           return 0;
       }
       return  recursive(s,s.length()-1);
    }

    private int recursive(String s,int index) {
        // 如何退出递归 待思考
        if(index <= 0){
            return 1;
        }
        int count = 0;
        char cur = s.charAt(index);
        char pre = s.charAt(index-1);
        if((cur > '6' && pre > '1') ||(pre > '2')|| (cur == 'a' || pre == 'a')){
            count = recursive(s,index-1);
        }else {
            count = recursive(s,index-2) + recursive(s,index-1);
        }
        return count;
    }
}
