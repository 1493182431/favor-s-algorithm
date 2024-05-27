package lanqiaobei;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/10
 */
public class LCMlujing {
    public static void main(String[] args) {
        int[]arr=new int[2022];
        for (int i = 0; i < 2022; i++) {
            arr[i]=Integer.MAX_VALUE;
        }
        arr[1]=0;
        for (int i = 1; i <= 2021; i++) {
            for (int j = 1; j <= 21; j++) {
                if(i+j<=2021){
                    arr[i+j]=Math.min(arr[i+j],lcm(i,i+j)+arr[i]);
                }
            }
        }
        System.out.println(arr[2021]);
    }

    // 计算最大公约数
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 计算最小公倍数
    public static int lcm(int a, int b) {
        int ta = a, tb = b;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return (ta * tb) / a;
    }
}
