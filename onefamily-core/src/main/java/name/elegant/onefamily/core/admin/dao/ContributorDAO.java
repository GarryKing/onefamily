package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;

import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface ContributorDAO {

    public Long insertContributor(ContributorDO contributor);

    public void updateContributor(ContributorDO contributor);

    public ContributorDO queryContributorById(long contributorId);

    public ContributorDO queryContributorByBizId(String bizId);

    public List<ContributorDO> queryContributorByPageNo(int pageNo, int size, String keyWord);

}
