package github.leetcode.最长重复子数组;

/**
 * Created by hujingtian on 2019/10/25.
 */
public class Solution {

    public static void main(String[] args){
        new Solution().findLength(new int[]{1,2,3,2,1},new int[]{3,2,1,4,7});
    }

    public int findLength(int[] A, int[] B) {
        int[][] dyArr = new int[A.length][B.length];
        int maxNum = 0;
        for(int i = 0 ; i < A.length ; i++){
            for(int j = 0 ; j < B.length ; j++){
                if(A[i] == B[j]){
                    if(i == 0 || j==0){
                        dyArr[i][j] = 1;
                        maxNum = Math.max(maxNum,dyArr[i][j]);
                    }else {
                        dyArr[i][j] = dyArr[i-1][j-1]+1;
                        maxNum = Math.max(maxNum,dyArr[i][j]);
                    }
                }
            }
        }
        return maxNum;
    }
}
