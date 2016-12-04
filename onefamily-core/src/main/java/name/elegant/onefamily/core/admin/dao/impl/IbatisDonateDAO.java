package name.elegant.onefamily.core.admin.dao.impl;

import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.core.admin.dao.DonateDAO;
import name.elegant.onefamily.core.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GarryKing on 2016/12/5.
 */
@Repository
public class IbatisDonateDAO extends BaseDao implements DonateDAO {

    public void insertDonate(DonateDO donateDO) {
        this.getSqlMapClientTemplate().insert("DonateDAO.insertDonate", donateDO);
    }

    public void updateDonate(DonateDO donateDO) {
        this.getSqlMapClientTemplate().update("DonateDAO.updateDonate", donateDO);
    }

    public DonateDO queryDonateById(long peerId) {
        return (DonateDO) this.getSqlMapClientTemplate().queryForObject("DonateDAO.queryDonateById", peerId);
    }

    public List<DonateDO> queryDonateByPageNo(int pageNo, int size, String keyWord) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNo", (pageNo - 1) * size);
        param.put("size", size);
        param.put("keyWord", keyWord);
        return (List<DonateDO>) this.getSqlMapClientTemplate().queryForList("DonateDAO.queryDonateByPageNo", param);
    }

}
