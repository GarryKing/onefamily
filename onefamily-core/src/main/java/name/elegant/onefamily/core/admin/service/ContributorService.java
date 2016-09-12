package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.Contributor;

import java.util.List;

/**
 * Created by GarryKing on 2016/8/19.
 * E-mail:flyhzq@sina.com
 */
public interface ContributorService {

    public List<Contributor> queryContributorByPageNo(int pageNo, int size);

}
