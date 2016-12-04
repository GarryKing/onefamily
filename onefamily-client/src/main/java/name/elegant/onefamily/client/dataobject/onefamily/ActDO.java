package name.elegant.onefamily.client.dataobject.onefamily;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
public class ActDO {

    private long actId;

    private String actBizId;

    private String actName;

    private String participantListStr;

    private List<ParticipantDO> participantList;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date modifyTime;

    public long getActId() {
        return actId;
    }

    public void setActId(long actId) {
        this.actId = actId;
    }

    public String getActBizId() {
        return actBizId;
    }

    public void setActBizId(String actBizId) {
        this.actBizId = actBizId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getParticipantListStr() {
        return participantListStr;
    }

    public void setParticipantListStr(String participantListStr) {
        this.participantListStr = participantListStr;
    }

    public List<ParticipantDO> getParticipantList() {
        return JSON.parseArray(getParticipantListStr(), ParticipantDO.class);
    }

    public void setParticipantList(List<ParticipantDO> participantList) {
        this.participantList = participantList;
        this.setParticipantListStr(JSON.toJSONString(participantList));
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
