package lanqiaobei;

import java.util.ArrayList;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/10
 */
public class baifanghuowu {
    public static void main(String args[]) {

        //常规思路

        /*
        long num = 2021041820210418l;

        int count = 0;
        for ( long i = 1 ; i < num ; i++ ){
            for ( long j = 1 ; j < num ; j++ ){
                for ( long k = 1 ; k < num ; k++ ){
                    if ( i * j *um ){
                        count++;
                    }
                }
            }
        }
        */

        //显而易见，这个算法的时间复杂的非常的大。计算机想要算出结果，可能需要几天的时间

        //所以要另辟蹊径

        //想想，三个数相乘要等于num，那么这三个数是不是都是num的因子，都能被num整除呢？
        //上方的算法，三层for循环都是从1遍历到num，其中很多组合都是无效的
        //简而言之，这些无效组合中不能同时被num整除，而有效的组合，任何一个数都能被num整除
        //所以，如果我们能从num的因子里面去找组合，是不是时间复杂度就要小许多？

        long num = 2021041820210418l;
        //如果直接赋值,计算机默认数字是int类型，会报错。所以要在后面加'l'，转换为long类型

        //定义一个ArrayList数组，存放num的因子
        ArrayList<Long> arr = new ArrayList<>();

        //从1开始遍历，遍历到num的平方根结束。不需要把num遍历一遍，这样算法复杂都也非常大，重点看for循环里面的语句
        for (long i = 1; i <= Math.sqrt(num); i++) {

            if (num % i == 0) {
                arr.add(i);     //如果能被整除，就放到arr数组中

                //！！！重点在这里，当i能被num整除的情况下，求出num关于i的另外一个除数n
                //这样，for循环不需要从1遍历到num。可以通过较小的因子，求出另外一个较大的因子
                long n = num / i;

                //如果num = Math.sqrt(num)*Math.sqrt(num),那么由较小的因子求较大的因子时，会重复，要排除这种情况
                if (n != i) {      //当然，此时num，不会出现这种情况。如果num=4，就会出现这种情况
                    arr.add(n);     //将较大的因子放入arr数组
                }

            }

        }

        //System.out.println(arr.size());   //num一共有128个因子

        //三层for循环依次遍历即可。 128^3 = 2097152 计算机完全可以在短时间内算出结果
        int count = 0;
        for (long i : arr) {
            for (long j : arr) {
                for (long k : arr) {
                    if (i * j * k == num) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);

    }
}
