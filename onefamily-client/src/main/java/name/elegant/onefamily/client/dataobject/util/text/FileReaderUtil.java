package name.elegant.onefamily.client.dataobject.util.text;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Author: Garry King
 * Date: 12-12-5 ����11:13
 * E-mail:flyhzq@sina.com
 */
public class FileReaderUtil {

    public static String readTextFile(String path) {
        String result = "";
        BufferedReader inputFile = null;
        try {
            String tempLine = "";
            inputFile = new BufferedReader(new FileReader(path));
            while ((tempLine = inputFile.readLine()) != null) {
                if (tempLine.equals(""))
                    continue;
                result += tempLine + "\n";
            }
            inputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
