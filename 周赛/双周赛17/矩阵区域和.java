package github.leetcode.周赛.双周赛17;

/**
 * Created by hujingtian on 2020/1/11.
 */
public class 矩阵区域和 {

    public static void main(String[] args){
        int[][] aa = new int[3][3];
        aa[0] = new int[]{1,2,3};
        aa[1] = new int[]{4,5,6};
        aa[2] = new int[]{7,8,9};
        new 矩阵区域和().matrixBlockSum(aa,1);
    }

    int m = 0;
    int n = 0;

    public int[][] matrixBlockSum(int[][] mat, int K) {
        m = mat.length;
        n = mat[0].length;
        int[][] answer = new int[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                answer[i][j] = calculate(mat,i,j,K);
            }
        }
        return answer;
    }
    private int calculate(int[][] mat,int i,int j,int k){
        int sum = 0;
        int istart = i-k > 0 ? i-k : 0;
        int iend = i+k < m-1 ? i+k : m-1;
        int jstart = j-k > 0 ? j-k : 0;
        int jend = j+k < n-1 ? j+k : n-1;
        for(int x = istart ; x <= iend ; x++){
            for(int y = jstart ; y <= jend ; y++){
                sum = sum + mat[x][y];
            }
        }
        return sum;
    }
}
