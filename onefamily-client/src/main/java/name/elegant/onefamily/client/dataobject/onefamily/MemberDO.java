package name.elegant.onefamily.client.dataobject.onefamily;

import java.util.Date;

/**
 * 参与机构捐助的成员
 * <p>
 * Created by Garry King on 2017/1/23.
 */
public class MemberDO {

    private long memberId;

    private long donateId;

    private long contribuorId;

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

    public long getContribuorId() {
        return contribuorId;
    }

    public void setContribuorId(long contribuorId) {
        this.contribuorId = contribuorId;
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
