package name.elegant.onefamily.core.admin.dao.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GarryKing on 2016/8/19.
 * E-mail:flyhzq@sina.com
 */
@Repository
public class IbatisContributorDAO extends BaseDao implements ContributorDAO {

    public Long insertContributor(ContributorDO contributor) {
        return (Long) this.getSqlMapClientTemplate().insert("ContributorDAO.insertContributor", contributor);
    }

    public void updateContributor(ContributorDO contributor) {
        this.getSqlMapClientTemplate().update("ContributorDAO.updateContributor", contributor);
    }

    public ContributorDO queryContributorById(long contributorId) {
        return (ContributorDO) this.getSqlMapClientTemplate().queryForObject("ContributorDAO.queryContributorById", contributorId);
    }

    @SuppressWarnings("unchecked")
    public List<ContributorDO> queryContributorByPageNo(int pageNo, int size) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNo", pageNo - 1);
        param.put("size", size);
        return (List<ContributorDO>) this.getSqlMapClientTemplate().queryForList("ContributorDAO.queryContributorByPageNo", param);
    }
}
