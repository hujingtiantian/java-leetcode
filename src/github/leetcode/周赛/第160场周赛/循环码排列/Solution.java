package github.leetcode.周赛.第160场周赛.循环码排列;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujingtian on 2019/10/27.
 */
public class Solution {

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> retList = new ArrayList<Integer>();
        int[] gray = getGray(n);
        boolean flag = false;
        for(int i = 0 ; i < gray.length ; i++){
            if(start == gray[i]){
                flag = true;
            }
            if(flag){
                retList.add(gray[i]);
            }
        }
        for(int i = 0 ; i < gray.length ; i++){
            if(start == gray[i]){
                flag = false;
            }
            if(!flag){
               break;
            }
            retList.add(gray[i]);
        }
        return retList;
    }

    public int[] getGray(int n){
        int[] s1 = {0,1};
        if(n < 1) {
            return null;
        }
        for(int i=2;i<=n;i++){
            int p = (int)Math.pow(2, i);
            int[] si = new int[p];
            for(int j=0;j<p;j++){
                if(j<(p/2)){
                    si[j] = s1[j];
                }else{
                    si[j] = ((int)Math.pow(2,i-1)) + s1[p-j-1];
                }
            }
            s1 = si;
        }
        return s1;
    }

}
