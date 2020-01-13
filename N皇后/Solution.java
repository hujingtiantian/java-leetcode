package github.leetcode.N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hujingtian on 2020/1/2.
 */
public class Solution {

    List<List<String>> list = new ArrayList<List<String>>();

    public static void main(String[] args){
        new Solution().solveNQueens(4);
    }

    public List<List<String>> solveNQueens(int n) {
        // 0 可用 1 不可用 2 皇后
        int[][] dyArr = new int[n][n];
        backtrack(dyArr,0,0);
        return list;
    }

    /**
     * 所以步骤一，判断是否满足终结条件，满足则存储并回退到上一行。
     * 不满足则进行步骤二，判断当前位置是否可用，
     * 可用则将皇后攻击范围内的棋盘位置置为不可用，并对行进行加一执行步骤一。
     * 不可用则进行步骤三，判断当前列的值是否等于n-1，不等于则对列进行加1，再执行步骤一，等于则对行减一，再执行步骤一。
     * 经典回溯
     * @param dyArr
     * @param x
     * @param y
     */
    private void backtrack(int[][] dyArr, int x,int y) {
        //如果超过最后一行了
        if(x == dyArr.length){
            trimBackTrack(dyArr,x,y);
            backtrack(dyArr,x-1,y+1);
        }
        //如果超过最后一列了
        if(y == dyArr.length){
            trimBackTrack(dyArr,x,y);
            backtrack(dyArr,x+1,0);
        }
        if(x == dyArr.length - 1 && dyArr[x][y] == 0){
            dyArr[x][y] = 2;
            addResult(dyArr);
            trimBackTrack(dyArr,x,y);
            backtrack(dyArr,x-1,y+1);
        }
    }

    private void addResult(int[][] dyArr) {

    }

    private void trimBackTrack(int[][] dyArr, int x, int y) {
        for(int i = 0 ; i < dyArr.length ; i++){
            for(int j = 0 ; j < dyArr[i].length ; j++){
                if(i == x || j == y || i + j == x + y || i - j == x - y){
                    dyArr[i][j] = 0;
                }
            }
        }
    }

}
