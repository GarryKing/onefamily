package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.ParticipantDO;
import name.elegant.onefamily.core.admin.dao.ActDAO;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        actDO.setParticipantList(new ArrayList<ParticipantDO>());
        actDAO.insertAct(actDO);
    }

    public void updateAct(ActDO actDO) {
        ActDO oldAct = actDAO.queryActById(actDO.getActId());
        actDO.setParticipantList(oldAct.getParticipantList());
        actDAO.updateAct(actDO);
    }

    public void updateParticipant(ParticipantDO newDO) {
        ActDO oldAct = actDAO.queryActById(newDO.getActId());
        List<ParticipantDO> oldList = oldAct.getParticipantList();
        Map<Long, ParticipantDO> oldMap = new LinkedHashMap<Long, ParticipantDO>();
        if (oldList == null) oldList = new ArrayList<ParticipantDO>();
        for (ParticipantDO old : oldList) {
            oldMap.put(old.getContributorId(), old);
        }
        ContributorDO contri = contributorDAO.queryContributorByBizId(newDO.getContributorBizId());
        if (contri != null) {
            oldMap.put(contri.getContributorId(), newDO);
            newDO.setContributorId(contri.getContributorId());
        } else {
            oldMap.put(0L, newDO);
            newDO.setContributorId(0L);
        }
        List<ParticipantDO> newList = new ArrayList<ParticipantDO>();
        for (Long key : oldMap.keySet()) {
            newList.add(oldMap.get(key));
        }
        oldAct.setParticipantList(newList);
        actDAO.updateAct(oldAct);
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
                if (contributorDO == null) continue;
                participantDO.setContributorName(contributorDO.getContributorName());
                participantDO.setContributorBizId(contributorDO.getBizId());
                participantDO.setStarLevel(contributorDO.getLevel());
            }
        }
        List<ParticipantDO> newList = new ArrayList<ParticipantDO>();
        newList.add(new ParticipantDO());
        newList.addAll(list);
        actDO.setParticipantList(newList);
        return actDO;
    }
}
