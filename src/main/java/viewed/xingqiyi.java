package viewed;

import java.util.Calendar;
import java.util.Scanner;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/8
 */
public class xingqiyi {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //在此输入您的代码...
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,1901);
        calendar.set(Calendar.MONTH,Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        int ans=0;
        while(calendar.get(Calendar.YEAR)!=2000||calendar.get(Calendar.MONTH)!=11||calendar.get(Calendar.DAY_OF_MONTH)!=31){
            if(calendar.get(Calendar.DAY_OF_WEEK)==2)ans++;
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }
        System.out.println(ans);
        scan.close();
    }
}
