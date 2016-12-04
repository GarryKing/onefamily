package name.elegant.onefamily.client.dataobject.onefamily;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by GarryKing on 2016/8/17.
 * E-mail��flyhzq@sina.com
 */
public class ContributorDO {

    /**
     * =================================================
     * ����Ϊ���� ���ֶ� key ֵ����
     * =================================================
     */

    /**
     * ��ϵ��ʽ���ֶ�keyֵ���绰����ַ��΢�š�qq������
     */
    public static final String CONTACT_PHONE = "�绰";
    public static final String CONTACT_ADDRESS = "��ͥ��ַ";
    public static final String CONTACT_WECHAT = "΢�ź�";
    public static final String CONTACT_QQ = "QQ��";
    public static final String CONTACT_EMAIL = "����";

    public static final List<String> CONTACT_KEY_MAP = Arrays.asList(CONTACT_PHONE, CONTACT_WECHAT, CONTACT_QQ, CONTACT_EMAIL, CONTACT_ADDRESS);

    /**
     * ҵ����չ���ֶ�keyֵ��
     * <p>
     * ����-00���Ƿ���У��ѧУ���ơ���У�꼶����ҵ����λ��ְ���س�
     * <p>
     * 10��20�û�Ⱥ�壺����Լ��Ⱥʱ�䡢�μ���һ��Ŀ
     * <p>
     * 30�û�Ⱥ�壺�������־Ը��ʱ�䡢�Ƿ��й��澭�������澭��ʱ�䡢���澭����֯�����澭������
     * ־Ը��λ���Ƿ���ӷ����λ��ʵ�ʰ���־Ը��λ���Ƿ���ע��־Ը�ߡ�ע��־Ը��ʱ��
     * <p>
     * 40�û�Ⱥ�壺
     */
    public static final List<String> EXTRA_00 = Arrays.asList("ѧУ����", "��У�꼶", "��ҵ", "��λ", "ְ��", "�س�");
    public static final List<String> EXTRA_10_20 = Arrays.asList("����Լ��Ⱥʱ��", "�μ���һ��Ŀ");
    public static final List<String> EXTRA_30 = Arrays.asList("�������־Ը��ʱ��", "�Ƿ��й��澭��", "���澭��ʱ��", "���澭����֯",
            "���澭������", "־Ը��λ", "�Ƿ���ӷ����λ", "ʵ�ʰ���־Ը��λ", "�Ƿ���ע��־Ը��", "ע��־Ը��ʱ��");
    public static final List<String> EXTRA_40 = Arrays.asList("�μӻʱ��", "�μӻ����", "������Ǽ�");

    public static final List<String> ALL_EXTRA_KEY;

    static {
        ALL_EXTRA_KEY = new ArrayList<String>();
        ALL_EXTRA_KEY.addAll(EXTRA_00);
        ALL_EXTRA_KEY.addAll(EXTRA_10_20);
        ALL_EXTRA_KEY.addAll(EXTRA_30);
        ALL_EXTRA_KEY.addAll(EXTRA_40);
    }


    /**
     * =================================================
     * ����Ϊ���� of_contributor �ֶ�
     * =================================================
     */
    private long contributorId;

    private String bizId;

    private String originalBizId;

    private String contributorName;

    private String type;

    private String identityCard;

    private String sex;

    private int age;

    private String nationality;

    private String status;

    private String level;

    private String remark;

    /**
     * ��ϵ��ʽ���ֶΣ��绰����ַ��΢�š�qq������
     */
    private String contactMap;

    private String extraMap;

    private Date createTime;

    private Date modifyTime;
    /**
     * =================================================
     * ����Ϊ���ⲿ�����ֶ�
     * =================================================
     */
    //�ۼƾ�����λ����
    private long totalMoney;

    //�ۼƲμӻʱ�䣬��λ������
    private long totalActTime;

    /**
     * =================================================
     * ����Ϊ���������� getter setter
     * =================================================
     */
    public long getContributorId() {
        return contributorId;
    }

    public void setContributorId(long contributorId) {
        this.contributorId = contributorId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getOriginalBizId() {
        return originalBizId;
    }

    public void setOriginalBizId(String originalBizId) {
        this.originalBizId = originalBizId;
    }

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContactMap() {
        return contactMap;
    }

    public Map<String, String> getContactMapObj() {
        JSONObject contactJSON = JSON.parseObject(contactMap);
        Map<String, String> result = new LinkedHashMap<String, String>();
        for (String contactKey : CONTACT_KEY_MAP) {
            String value = (contactJSON == null || contactJSON.get(contactKey) == null) ? "" : contactJSON.get(contactKey) + "";
            result.put(contactKey, value);
        }
        return result;
    }

    public void setContactMap(String contactMap) {
        this.contactMap = contactMap;
    }

    public String getExtraMap() {
        return extraMap;
    }

    public Map<String, String> getExtraMapObj() {
        JSONObject contactJSON = JSON.parseObject(extraMap);
        Map<String, String> result = new LinkedHashMap<String, String>();
        for (String contactKey : ALL_EXTRA_KEY) {
            String value = (contactJSON == null || contactJSON.get(contactKey) == null) ? "" : contactJSON.get(contactKey) + "";
            result.put(contactKey, value);
        }
        return result;
    }

    public void setExtraMap(String extraMap) {
        this.extraMap = extraMap;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public long getTotalActTime() {
        return totalActTime;
    }

    public void setTotalActTime(long totalActTime) {
        this.totalActTime = totalActTime;
    }

    public static List<String> getContactKeyMap() {
        return CONTACT_KEY_MAP;
    }

    public static List<String> getExtra40() {
        return EXTRA_40;
    }

    public static List<String> getExtra1020() {
        return EXTRA_10_20;
    }

    public static List<String> getExtra00() {
        return EXTRA_00;
    }

    public static List<String> getExtra30() {
        return EXTRA_30;
    }
}
