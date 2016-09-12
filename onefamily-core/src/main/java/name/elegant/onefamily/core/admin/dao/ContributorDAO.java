package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.onefamily.Contributor;

import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface ContributorDAO {

    public Long insertContributor(Contributor contributor);

    public Contributor queryContributorById(long contributorId);

    public List<Contributor> queryContributorByPageNo(int pageNo, int size);

}
