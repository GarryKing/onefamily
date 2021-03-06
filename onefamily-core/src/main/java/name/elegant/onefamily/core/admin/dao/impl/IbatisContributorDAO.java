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

    public ContributorDO queryContributorByBizId(String bizId) {
        return (ContributorDO) this.getSqlMapClientTemplate().queryForObject("ContributorDAO.queryContributorByBizId", bizId);
    }

    @SuppressWarnings("unchecked")
    public List<ContributorDO> queryContributorByPageNo(int pageNo, int size, String keyWord) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNo", (pageNo - 1) * size);
        param.put("size", size);
        param.put("keyWord", keyWord);
        return (List<ContributorDO>) this.getSqlMapClientTemplate().queryForList("ContributorDAO.queryContributorByPageNo", param);
    }
}
