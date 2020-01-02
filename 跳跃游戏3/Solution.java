package github.leetcode.跳跃游戏3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hujingtian on 2019/12/29.
 */
public class Solution {

    public static void main(String[] args){
        int[] arr = new int[]{4,2,3,0,3,1,2};
        new Solution().canReach(arr,5);
    }

    public boolean canReach(int[] arr, int start) {
        List<Integer> zeroIndex = new ArrayList<Integer>();
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i] == 0){
                zeroIndex.add(i);
            }
        }
        if(zeroIndex.size() == 0){
            return false;
        }
        for(int i = 0 ; i < zeroIndex.size() ; i++){
            if(zeroIndex.get(i) == start){
                return true;
            }
            if(check(arr,start,zeroIndex.get(i))){
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] arr, int start, Integer index) {
        HashSet hashSet = new HashSet();
        digui(arr,index,hashSet);
        if(hashSet.add(start)){
            return false;
        }
        return true;

    }

    private void digui(int[] arr, Integer index,Set<Integer> set) {
        if(!set.add(index)){
            return;
        }
        for(int i = 0 ; i < arr.length ; i ++){
            if(i + arr[i] == index){
                digui(arr,i,set);
            }
            if( i - arr[i] == index){
                digui(arr,i,set);
            }
        }
    }
}
