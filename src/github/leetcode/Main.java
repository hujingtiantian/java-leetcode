package github.leetcode;

/**
 * Created by hujingtian on 2019/9/20.
 */
public class Main {

    public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3l;

        // 包装类的== 不会拆箱
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a+b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a+b));
        System.out.println(g.equals(3));

    }
}
