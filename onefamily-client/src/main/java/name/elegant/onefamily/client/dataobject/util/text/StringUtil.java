package name.elegant.onefamily.client.dataobject.util.text;

import java.util.Calendar;

/**
 * Created by GarryKing on 2016/8/18.
 * E-mail:flyhzq@sina.com
 */
public class StringUtil {

    public static boolean isBlank(String str) {
        if (null == str) return true;
        if (str.length() == 0) return true;
        String tmp = str.trim();
        if (tmp.length() == 0) return true;
        return false;
    }

    public static String getBirthdayFromID(String identify) {
        if (isBlank(identify) || identify.length() != 18) return "";
        return identify.substring(6, 10) + "-" +identify.substring(10, 12) +"-" +identify.substring(12, 14);
    }

    public static int getAgeFromId(String identify) {
        if (isBlank(identify) || identify.length() != 18) return 0;
        String year = identify.substring(6, 10);
        int yearInt;
        try {
            yearInt = Integer.parseInt(year);
        } catch (Exception e) {
            return 0;
        }
        Calendar a = Calendar.getInstance();
        int now = a.get(Calendar.YEAR);
        if (yearInt >= now) return 0;
        return now - yearInt;
    }

}
