package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.client.dataobject.onefamily.ParticipantDO;
import name.elegant.onefamily.core.admin.dao.ActDAO;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.dao.DonateDAO;
import name.elegant.onefamily.core.admin.service.ContributorService;
import name.elegant.onefamily.core.util.text.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GarryKing on 2016/8/19.
 * E-mail:flyhzq@sina.com
 */
@Service
public class ContributorServiceImpl implements ContributorService {

    @Autowired
    private ContributorDAO contributorDAO;

    @Autowired
    private DonateDAO donateDAO;

    @Autowired
    private ActDAO actDAO;

    public void insertContributor(ContributorDO contributorDO) {
        long currId = contributorDAO.insertContributor(contributorDO);
        ContributorDO currDO = contributorDAO.queryContributorById(currId);
        currDO.setBizId(buildContributorBizId(currDO.getType(), currId));
        contributorDAO.updateContributor(currDO);
    }

    public void updateContributor(ContributorDO contributorDO) {
        contributorDO.setBizId(buildContributorBizId(contributorDO.getType(), contributorDO.getContributorId()));
        contributorDAO.updateContributor(contributorDO);
    }

    public List<ContributorDO> queryContributorByPageNo(int pageNo, int size, String keyWord) {
        List<ContributorDO> list = contributorDAO.queryContributorByPageNo(pageNo, size, keyWord);
        return fillList(list);
    }

    private List<ContributorDO> fillList(List<ContributorDO> list) {
        if (list != null) {
            for (ContributorDO contributorDO : list) {
                List<DonateDO> donateDOList = donateDAO.queryDonateByContriId(contributorDO.getContributorId());
                double amount = 0.00D;
                if (donateDOList != null) {
                    for (DonateDO donateDO : donateDOList) {
                        try {
                            amount += MoneyUtil.stringToMoney(donateDO.getPayAmount()).doubleValue();
                        } catch (Exception e) {
                        }
                    }
                }
                contributorDO.setTotalMoney(amount);

                List<ActDO> actDOList = actDAO.queryActByKeyWord("" + contributorDO.getContributorId());
                long hours = 0;
                if (actDOList != null) {
                    for (ActDO actDO : actDOList) {
                        for (ParticipantDO participantDO : actDO.getParticipantList()) {
                            if (participantDO.getContributorId() == contributorDO.getContributorId()) {
                                hours += Long.parseLong(participantDO.getThisActDuration());
                            }
                        }
                    }
                }
                contributorDO.setTotalActTime(hours);
            }
        }
        return list;
    }

    private String buildContributorBizId(int type, long id) {
        String result = "P";
        result += ((1000 + type) + "").substring(1);
        result += "-";
        result += ((1000000 + id) + "").substring(1);
        return result;
    }
}
