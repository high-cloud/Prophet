package ustc.zzy.prophet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static String timeStamp2date(long timeStamp, String format) {
        SimpleDateFormat formatTime = new SimpleDateFormat(format);
        return formatTime.format(new Date(timeStamp));
    }

    public static String timeStamp2date(long timeStamp) {
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatTime.format(new Date(timeStamp));
    }
}
