package lanqiaobei;

import java.math.BigInteger;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/8
 */
public class fushumi {
    public static void main(String[] args) {
        BigInteger a=new BigInteger("2");
        BigInteger b=new BigInteger("3");
        int c=123456;
        method(a, b, c);
        BigInteger temp=new BigInteger("0");
        System.out.print(shi);
        if(xu.compareTo(temp)==1) {//虚部是正数
            System.out.print("+"+xu+"i");
        }else {//虚部是负数
            System.out.print(xu+"i");
        }

    }

    static BigInteger shi=new BigInteger("1");
    static BigInteger xu=new BigInteger("1");
    public static void method(BigInteger a,BigInteger b,int c) {

        if(c<1) {
            return;
        }
        if(c==1) {
            shi=a;
            xu=b;
            return;
        }
        if(c%2==0) {//偶数
            BigInteger temp=new BigInteger("2");
            shi=a.multiply(a).subtract(b.multiply(b));
            xu=a.multiply(b).multiply(temp);
            method(shi, xu, c/2);
        }else {
            method(a, b, c-1);
            BigInteger temp=shi;
            shi=shi.multiply(a).subtract(xu.multiply(b));
            xu=xu.multiply(a).add(b.multiply(temp));

        }
    }
}
