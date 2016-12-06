package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.client.dataobject.onefamily.ParticipantDO;

import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
public interface ActService {

    public void insertAct(ActDO actDO);

    public void updateAct(ActDO actDO);

    public void updateParticipant(ParticipantDO participantDO);

    public ActDO queryActById(long actId);

    public List<ActDO> queryActByPageNo(int pageNo, int size, String keyWord);

}