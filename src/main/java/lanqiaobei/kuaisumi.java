package lanqiaobei;

import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/11
 */
public class kuaisumi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        long T=scan.nextLong();
        for(long i=0;i<T;i++){
            long N=scan.nextLong();
            long M=scan.nextLong();
            long P=scan.nextLong();
            long k=qucikMod(N,M,P);
            System.out.println(k);
        }
        scan.close();
    }

    public static long qucikMod(long x, long n, long p) {//快速幂运算,注意参数和返回值都用long
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) ans = ans * x % p;//指数为奇数时，底数=(底数*指数^1)%p
            x = x * x % p;//底数平方,同时模运算
            n >>= 1;//指数减半（向下取整）
        }
        return ans;
    }

//    public static long qucikPower(long x, long n) {//快速幂运算,注意参数和返回值都用long
//        long ans = 1;
//        while (n > 0) {
//            if ((n & 1) == 1) ans *= x;//指数为奇数时，底数=底数*指数^1
//            x *= x;//底数平方
//            n >>= 1;//指数减半（向下取整）
//        }
//        return ans;
//    }
}
