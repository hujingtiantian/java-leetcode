package github.leetcode.字符串解码;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hujingtian on 2019/7/15.
 * 给定一个经过编码的字符串，返回它解码后的字符串。

 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/decode-string
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {


    public static void main(String[] args){
        Solution solution = new Solution();

    }

    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        StringBuffer curStr = new StringBuffer();
//        for(Character c : chars){
//            if(isNumber(c)){
//                curStr.append(c);
//            }else if(){
//
//            }
//        }
        return null;
    }

    public boolean isNumber(Character c){
       try {
           Integer.valueOf(c);
           return true;
       }catch (NumberFormatException e){
           return false;
       }
    }
}
