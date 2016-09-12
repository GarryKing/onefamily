package name.elegant.onefamily.core.util.text;

import name.elegant.onefamily.core.util.console.ConsoleDebuger;
import name.elegant.onefamily.core.util.array.ArrayUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Company:tmall.com
 * User: zeqing.hzq
 * Date: 12-9-3 ÏÂÎç3:16
 */
public class WordSearcher {

    public String[] searchMatchingContent(String regular, String sourceString) {
        if (null == regular || null == sourceString) {
            return null;
        }
        Matcher matcher = Pattern.compile(regular).matcher(sourceString);
        String[] result = new String[3];
        int count = 0;
        while (matcher.find()) {
            if (result.length <= (count + 1)) {
                result = ArrayUtil.expandStringArray(result);
            }
            result[count] = sourceString.substring(matcher.start(), matcher.end());
            ConsoleDebuger.println("count:" + count + ",Array's length:" + result.length);
            count++;
        }
        return result;
    }
}
