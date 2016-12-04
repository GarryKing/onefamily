package name.elegant.onefamily.client.dataobject.onefamily;

import java.util.Date;

/**
 * Created by GarryKing on 2016/12/4.
 */
public class DonateDO {

    private long donateId;

    private String serialId;

    private long contributorId;

    private String type;

    private String payAmount;

    private Date payTime;

    private String accountStatus;

    private String feedbackContent;

    private Date feedbackTime;

    private String feedbacker;

    private String remark;

    private Date createTime;

    private Date modifyTime;

    private long peerId;

    private String contributorBizId;

    private String contributorName;

    private String contributorCard;

    private String aidedBizId;

    private String aidedName;


    public long getDonateId() {
        return donateId;
    }

    public void setDonateId(long donateId) {
        this.donateId = donateId;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public long getContributorId() {
        return contributorId;
    }

    public void setContributorId(long contributorId) {
        this.contributorId = contributorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public long getPeerId() {
        return peerId;
    }

    public void setPeerId(long peerId) {
        this.peerId = peerId;
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

    public String getAidedBizId() {
        return aidedBizId;
    }

    public void setAidedBizId(String aidedBizId) {
        this.aidedBizId = aidedBizId;
    }

    public String getAidedName() {
        return aidedName;
    }

    public void setAidedName(String aidedName) {
        this.aidedName = aidedName;
    }
}
