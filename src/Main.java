public class Main {

    public static void main(String[] args) {
        int[][] a= {{1,2,3},{4,5,6},{7,8,9}};
        rotate(a);
        System.out.println(a);
    }

    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        if(length == 1){
            return;
        }
        for(int i = 0;i< length/2;i++){
            for(int j=i;j<length-1-2*i;j++){
                int a = matrix[i][j];
                int b = matrix[j][length-i-1];
                int c = matrix[length-i-1][length-j-1];
                int d = matrix[length-j-1][i];
                matrix[i][j] = d;
                matrix[j][length-i-1] = a;
                matrix[length-i-1][length-j-1] = b;
                matrix[length-j-1][i] = c;
            }
        }
    }

}
