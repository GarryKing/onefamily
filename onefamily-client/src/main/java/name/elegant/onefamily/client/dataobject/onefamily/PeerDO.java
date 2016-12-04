package name.elegant.onefamily.client.dataobject.onefamily;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by GarryKing on 2016/12/3.
 */
public class PeerDO {

    public static final List<String> CONTACT_KEY_MAP = Arrays.asList(
            "学校所属地市", "学校名称", "年级", "班主任姓名", "班主任电话", "学生家庭住址", "家庭情况",
            "监护人姓名", "监护人手机", "监护人座机", "监护人QQ", "监护人微信", "监护人E-mail"
    );

    private long peerId;

    private String bizId;

    private String aidedName;

    private String identify;

    private String pic;

    private Date birthday;

    private String sex;

    private int age;

    private String nationality;

    private String aidedType;

    private String status;

    private String remark;

    private String bank;

    private String account;

    private String payee;

    private String contactMap;

    private String extraMap;

    private Date peerTime;

    private String payPeriod;

    private long contributorId;

    private Date createTime;

    private Date modifyTime;

    private String contributorBizId;

    private String contributorName;

    private String contributorCard;

    private long totalAidedAmount;

    public long getPeerId() {
        return peerId;
    }

    public void setPeerId(long peerId) {
        this.peerId = peerId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getAidedName() {
        return aidedName;
    }

    public void setAidedName(String aidedName) {
        this.aidedName = aidedName;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getAidedType() {
        return aidedType;
    }

    public void setAidedType(String aidedType) {
        this.aidedType = aidedType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
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

    public void setExtraMap(String extraMap) {
        this.extraMap = extraMap;
    }

    public Date getPeerTime() {
        return peerTime;
    }

    public void setPeerTime(Date peerTime) {
        this.peerTime = peerTime;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public long getContributorId() {
        return contributorId;
    }

    public void setContributorId(long contributorId) {
        this.contributorId = contributorId;
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

    public String getContributorBizId() {
        return contributorBizId;
    }

    public void setContributorBizId(String contributorBizId) {
        this.contributorBizId = contributorBizId;
    }

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getContributorCard() {
        return contributorCard;
    }

    public void setContributorCard(String contributorCard) {
        this.contributorCard = contributorCard;
    }

    public long getTotalAidedAmount() {
        return totalAidedAmount;
    }

    public void setTotalAidedAmount(long totalAidedAmount) {
        this.totalAidedAmount = totalAidedAmount;
    }

}
