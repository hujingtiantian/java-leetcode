package github.leetcode.编辑距离;


/**
 * "pneumonoultramicroscopicsilicovolcanoconiosis"
 "ultramicroscopically"
 * Created by hujingtian on 2019/12/25.
 */
public class Solution {

    public static void main(String[] args){
        new Solution().minDistance("pneumonoultramicroscopicsilicovolcanoconiosis","ultramicroscopically");
    }

    public int minDistance(String word1, String word2) {
        if(word1.length() * word2.length() == 0){
            return word1.length() + word2.length();
        }
        int[][] dyarr = new int[word1.length()+1][word2.length()+1];
        for(int i = 0 ; i < word1.length()+1;i++){
            dyarr[i][0] = i;
        }
        for(int j = 0 ; j < word2.length()+1;j++){
            dyarr[0][j] = j;
        }
        for (int i = 1 ; i < word1.length()+1;i++){
            for(int j = 1 ; j < word2.length()+1;j++){
                int minx = dyarr[i-1][j]+1;
                int miny = dyarr[i][j-1]+1;
                int preMin = Math.min(minx,miny);
                int cur = dyarr[i-1][j-1];
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dyarr[i][j] = Math.min(cur,preMin);
                }else {
                    dyarr[i][j] = Math.min(cur+1,preMin);
                }
            }
        }
        return dyarr[word1.length()][word2.length()];
    }
}
