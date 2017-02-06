package name.elegant.onefamily.client.dataobject.onefamily;

import java.util.Date;
import java.util.List;

/**
 * Created by Garry King on 2017/1/23.
 */
public class GroupDO {

    private long donateId;

    private String serialId;

    private String groupName;

    private Date donateTime;

    private String remark;

    private double total;

    private Date createTime;

    private Date modifyTime;

    private List<MemberDO> memberList;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(Date donateTime) {
        this.donateTime = donateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public List<MemberDO> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberDO> memberList) {
        this.memberList = memberList;
    }
}
