package github.leetcode.杨辉三角;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujingtian on 2019/12/9.
 */
public class Solution {

    public static void main(String[] args){

    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> retList = new ArrayList<>();
        for(int i = 0 ; i < numRows;i++){
            retList.add(new ArrayList<>());
            for (int j = 0; j < i+1;j++){
                if(j == 0 || j == i){
                    retList.get(i).add(1);
                }else {
                    retList.get(i).add(retList.get(i-1).get(j-1) + retList.get(i-1).get(j));
                }

            }
        }
        return retList;
    }
}
