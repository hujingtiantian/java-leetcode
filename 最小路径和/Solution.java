package github.leetcode.最小路径和;

/**
 * Created by hujingtian on 2019/10/23.
 */
public class Solution {

    public static void main(String[] args){
        new Solution().minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}});
    }

    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int[][] dyArr = new int[grid.length][grid[0].length];
        for(int i = 0 ; i < grid.length ; i++){
            for (int j = 0 ; j < grid[i].length ; j++){
                if(i == 0 && j==0){
                    dyArr[i][j] = grid[i][j];
                }else if( i == 0){
                    dyArr[i][j] = dyArr[i][j-1] + grid[i][j];
                }else if(j == 0){
                    dyArr[i][j] = dyArr[i-1][j] + grid[i][j];
                }else {
                    dyArr[i][j] = Math.min(dyArr[i-1][j],dyArr[i][j-1]) + grid[i][j];
                }
            }
        }
        return dyArr[grid.length-1][grid[0].length-1];
    }

}
