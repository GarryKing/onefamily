package name.elegant.onefamily.client.dataobject.onefamily;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by GarryKing on 2016/8/17.
 * E-mail：flyhzq@sina.com
 */
public class ContributorDO {

    /**
     * =================================================
     * 以下为：表 大字段 key 值定义
     * =================================================
     */

    /**
     * 联系方式大字段key值：电话、地址、微信、qq、邮箱
     */
    public static final String CONTACT_PHONE = "电话";
    public static final String CONTACT_ADDRESS = "家庭地址";
    public static final String CONTACT_WECHAT = "微信号";
    public static final String CONTACT_QQ = "QQ号";
    public static final String CONTACT_EMAIL = "邮箱";

    public static final List<String> CONTACT_KEY_MAP = Arrays.asList(CONTACT_PHONE, CONTACT_WECHAT, CONTACT_QQ, CONTACT_EMAIL, CONTACT_ADDRESS);

    /**
     * 业务扩展大字段key值：
     * <p>
     * 基础-00：是否在校、学校名称、在校年级、行业、单位、职务、特长
     * <p>
     * 10、20用户群体：加入约上群时间、参加益家活动项目
     * <p>
     * 30用户群体：加入益家志愿者时间、是否有公益经历、公益经历时间、公益经历组织、公益经历内容
     * 志愿岗位、是否服从分配岗位、实际安排志愿岗位、是否是注册志愿者、注册志愿者时间
     * <p>
     * 40用户群体：
     */
    public static final List<String> EXTRA_00 = Arrays.asList("学校名称", "在校年级", "行业", "单位", "职务", "特长");
    public static final List<String> EXTRA_10_20 = Arrays.asList("加入约上群时间", "参加益家活动项目");
    public static final List<String> EXTRA_30 = Arrays.asList("加入益家志愿者时间", "是否有公益经历", "公益经历时间", "公益经历组织",
            "公益经历内容", "志愿岗位", "是否服从分配岗位", "实际安排志愿岗位", "是否是注册志愿者", "注册志愿者时间");
    public static final List<String> EXTRA_40 = Arrays.asList("参加活动时间", "参加活动内容", "活动评定星级");

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
     * 以下为：表 of_contributor 字段
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
     * 联系方式大字段：电话、地址、微信、qq、邮箱
     */
    private String contactMap;

    private String extraMap;

    private Date createTime;

    private Date modifyTime;
    /**
     * =================================================
     * 以下为：外部关联字段
     * =================================================
     */
    //累计捐款金额，单位：分
    private long totalMoney;

    //累计参加活动时间，单位：毫秒
    private long totalActTime;

    /**
     * =================================================
     * 以下为：容器方法 getter setter
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
