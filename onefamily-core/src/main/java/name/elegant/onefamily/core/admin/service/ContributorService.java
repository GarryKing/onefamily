package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;

import java.util.List;

/**
 * Created by GarryKing on 2016/8/19.
 * E-mail:flyhzq@sina.com
 */
public interface ContributorService {

    public void updateContributor(ContributorDO contributorDO);

    public List<ContributorDO> queryContributorByPageNo(int pageNo, int size);

}
