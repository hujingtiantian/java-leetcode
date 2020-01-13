package github.leetcode.周赛.双周赛17;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujingtian on 2020/1/11.
 */
public class Soluction {

    public static void main(String[] args){
        int[] a = new int[]{1,2,3,4};
        new Soluction().decompressRLElist(a);
    }

    public int[] decompressRLElist(int[] nums) {
        int num = nums.length / 2;
        List<Integer> retList = new ArrayList<Integer>();
        for(int i = 0 ; i < num ; i++){
            for(int j = 0 ; j < nums[2*i] ; j++){
                retList.add(nums[2*i + 1]);
            }
        }
        int[] retArray = new int[retList.size()];
        for(int i = 0 ; i < retList.size(); i++){
            retArray[i] = retList.get(i);
        }
        return retArray;
    }
}
