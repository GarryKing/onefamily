package name.elegant.onefamily.client.dataobject.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by GarryKing on 2016/8/18.
 * E-mail£ºflyhzq@sina.com
 */
public class SystemUser {

    private long userId;

    private String userName;

    private String password;

    private String authExtra;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject getAuthExtra() {
        return JSON.parseObject(authExtra);
    }

    public void setAuthExtra(String authExtra) {
        this.authExtra = authExtra;
    }

}
