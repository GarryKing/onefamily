package name.elegant.onefamily.client.dataobject.util.text;

import java.math.BigDecimal;

/**
 * Created by Garry King on 2016/12/5.
 */
public class MoneyUtil {

    public static boolean isNumeric(String money) {
        if (StringUtil.isBlank(money)) return false;
        try {
            BigDecimal bigDecimal = new BigDecimal(money);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static BigDecimal stringToMoney(String money) {
        return new BigDecimal(money);
    }

}