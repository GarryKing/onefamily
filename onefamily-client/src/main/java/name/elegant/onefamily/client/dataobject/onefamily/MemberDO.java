package name.elegant.onefamily.client.dataobject.onefamily;

import java.util.Date;

/**
 * ������������ĳ�Ա
 * <p>
 * Created by Garry King on 2017/1/23.
 */
public class MemberDO {

    private long memberId;

    private long donateId;

    private long contributorId;

    private String contributorBizId;

    private String contributorName;

    private String payAmount;

    private Date createTime;

    private Date modifyTime;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getDonateId() {
        return donateId;
    }

    public void setDonateId(long donateId) {
        this.donateId = donateId;
    }

    public long getContributorId() {
        return contributorId;
    }

    public void setContributorId(long contributorId) {
        this.contributorId = contributorId;
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

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
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
}
