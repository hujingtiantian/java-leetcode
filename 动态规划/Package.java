package github.leetcode.动态规划;

/**
 * Created by hujingtian on 2019/10/17.
 */
public class Package {

    public static void main(String[] args){
        int[] weight = {3,5,2,6,4}; //物品重量
        int[] val = {4,4,3,5,3}; //物品价值
        int m = 12; //背包容量
        System.out.println(maxWeight(weight.length,m,weight,val));

    }

    /**
     * 返回最大的价值
     * @param num 数量
     * @param capacity 容量
     * @param v 物品的体积
     * @param p 物品的价值
     * @return
     */
    public static int maxWeight(int num,int capacity,int[] v,int[] p){
        int[][] dyArr = new int[num+1][capacity+1];
        for(int i = 1 ; i < num+1 ; i++){
            for(int j = 1 ; j < capacity+1;j++){
                try {
                    if(v[i-1] > j){
                        dyArr[i][j] = dyArr[i-1][j];
                    }else {
                        dyArr[i][j] = Math.max(dyArr[i-1][j-v[i-1]] + p[i-1], dyArr[i-1][j]);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return dyArr[num][capacity];
    }
}
