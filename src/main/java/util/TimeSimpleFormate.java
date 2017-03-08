package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 17854 on 2016/11/26.
 */
public class TimeSimpleFormate
{
    public static String dateFormat(Date date)
    {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormater.format(date);
    }
    public static String simpleData(Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
