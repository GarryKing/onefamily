package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.ParticipantDO;
import name.elegant.onefamily.core.admin.dao.ActDAO;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
@Service
public class ActServiceImpl implements ActService {

    @Autowired
    private ActDAO actDAO;

    @Autowired
    private ContributorDAO contributorDAO;

    public void insertAct(ActDO actDO) {
        actDAO.insertAct(actDO);
    }

    public void updateAct(ActDO actDO) {
        actDAO.updateAct(actDO);
    }

    public ActDO queryActById(long actId) {
        return fillActDO(actDAO.queryActById(actId));
    }

    public List<ActDO> queryActByPageNo(int pageNo, int size, String keyWord) {
        List<ActDO> list = actDAO.queryActByPageNo(pageNo, size, keyWord);
        if (list != null) {
            for (ActDO actDO : list) {
                fillActDO(actDO);
            }
        }
        return list;
    }

    private ActDO fillActDO(ActDO actDO) {
        List<ParticipantDO> list = actDO.getParticipantList();
        if (list != null) {
            for (ParticipantDO participantDO : list) {
                ContributorDO contributorDO = contributorDAO.queryContributorById(participantDO.getContributorId());
                participantDO.setContributorName(contributorDO.getContributorName());
                participantDO.setContributorBizId(contributorDO.getBizId());
                participantDO.setStarLevel(contributorDO.getExtraMapObj().get(ContributorDO.EXTRA_40.toArray()[2]));
            }
        }
        return actDO;
    }
}
