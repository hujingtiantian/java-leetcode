package github.leetcode.每日温度;

import java.util.Stack;

/**
 * Created by hujingtian on 2019/10/25.
 */
public class Solution {


    /**
     * 用for循环
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] retArr = new int[T.length];
        for(int i = 0 ; i < T.length ; i++){
            for (int j = i+1 ; j < T.length;j++){
                if(T[j]>T[i]){
                    retArr[i] = j-i;
                    break;
                }
            }
        }
        return retArr;
    }

    /**
     * 用栈
     * @param T
     * @return
     */
    public int[] dailyTemperatures1(int[] T) {
        int[] retArr = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = 0 ; i < T.length ; i++){
            boolean flag = true;
            while (stack.size() != 0 && flag) {
                int index = stack.pop();
                if(T[i]>T[index]){
                    retArr[index] = i-index;
                }else {
                    stack.push(index);
                    flag = false;
                }
            }
            stack.push(i);
        }
        return retArr;
    }
}
