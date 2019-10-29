package github.leetcode.周赛.第160场周赛.找出给定方程的正整数解;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujingtian on 2019/10/27.
 */
public class Solution {


    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> retList = new ArrayList<>();
        for(int i = 1 ; i <= 1000 ; i++){
            for(int j = 1 ; j <= 1000 ; j++){
                if(customfunction.f(i,j) == z){
                    List addList = new ArrayList();
                    addList.add(i);
                    addList.add(j);
                    retList.add(addList);
                }
            }
        }
        return retList;
    }


    interface CustomFunction {
        // Returns positive integer f(x, y) for any given positive integer x and y.
         int f(int x, int y);
    };
}
