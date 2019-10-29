package github.leetcode.有效的括号;

import java.util.Stack;

/**
 * Created by hujingtian on 2019/10/22.
 */
public class Solution {
    public static void main(String[] args){
        new Solution().isValid("()");
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for(char right : s.toCharArray()){
            if(stack.size() == 0){
                stack.push(right);
                continue;
            }
            char left = stack.pop();
            if(!checkLeftAndRight(left,right)){
               stack.push(left);
               stack.push(right);
            }
        }
       return stack.size() == 0;
    }

    private boolean checkLeftAndRight(char left, char right) {
        char trueChar = 0;
        switch (left){
            case '(' :  trueChar = ')';break;
            case '{' :  trueChar = '}';break;
            case '[' :  trueChar = ']';break;
        }
        return right == trueChar;
    }
}
