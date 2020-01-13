package github.leetcode.周赛170;

import java.util.Stack;

/**
 * Created by hujingtian on 2020/1/5.
 */
public class 解码字母到整数映射 {

    public static void main(String[] args){
        char char1 = 'a';
        char char2 = '1';
        new 解码字母到整数映射().freqAlphabets("1326#0");
    }

    public String freqAlphabets(String s) {
        StringBuffer retStr = new StringBuffer();
        String[] array = s.split("#");
        for(int i = 0 ; i < array.length ; i++){
            char[] chars = array[i].toCharArray();
            int length = chars.length;
            for (int j = 0 ;j < length; j++){
                if(i != array.length-1){
                    if(j != length-2){
                        retStr.append((char)(chars[j]+48));
                    }else {
                        retStr.append((char) (chars[j]+chars[j+1]));
                        j++;
                    }
                }else {
                    retStr.append((char)(chars[j]+48));
                }
            }
        }
        return retStr.toString();
    }
}
