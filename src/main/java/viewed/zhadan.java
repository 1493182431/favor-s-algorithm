package viewed;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @description:
 * @author：Favor
 * @date: 2024/4/8
 */
public class zhadan {
    public static void main(String[] args) {
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        cal.set(cal.YEAR,2014);
        cal.set(cal.MONTH, 10);
        cal.set(cal.DATE, 9);
        cal.add(cal.DATE, 1000);
        String sc=sdf.format(cal.getTime());
        System.out.println(sc);
    }
}
