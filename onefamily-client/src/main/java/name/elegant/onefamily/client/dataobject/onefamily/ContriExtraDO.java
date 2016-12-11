package name.elegant.onefamily.client.dataobject.onefamily;

/**
 * Created by GarryKing on 2016/12/11.
 */
public class ContriExtraDO {

    private String key;

    private String type;

    private String value;

    private boolean isTime;

    private boolean isSchool;

    public ContriExtraDO(String key, String type, String value, boolean isTime) {
        this.key = key;
        this.type = type;
        this.value = value;
        this.isTime = isTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isTime() {
        return isTime;
    }

    public void setTime(boolean time) {
        isTime = time;
    }

    public boolean isSchool() {
        return isSchool;
    }

    public void setSchool(boolean school) {
        isSchool = school;
    }
}
