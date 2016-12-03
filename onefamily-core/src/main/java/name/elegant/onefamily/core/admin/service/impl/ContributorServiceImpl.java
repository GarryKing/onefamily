package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.service.ContributorService;
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

    public void insertContributor(ContributorDO contributorDO) {
        contributorDAO.insertContributor(contributorDO);
    }

    public void updateContributor(ContributorDO contributorDO) {
        contributorDAO.updateContributor(contributorDO);
    }

    public List<ContributorDO> queryContributorByPageNo(int pageNo, int size, String keyWord) {
        return contributorDAO.queryContributorByPageNo(pageNo, size, keyWord);
    }
}
