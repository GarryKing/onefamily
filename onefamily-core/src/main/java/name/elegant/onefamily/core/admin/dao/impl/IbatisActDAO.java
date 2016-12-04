package name.elegant.onefamily.core.admin.dao.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.core.admin.dao.ActDAO;
import name.elegant.onefamily.core.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GarryKing on 2016/12/5.
 */
@Repository
public class IbatisActDAO extends BaseDao implements ActDAO {

    public void insertAct(ActDO actDO) {
        this.getSqlMapClientTemplate().insert("ActDAO.insertAct", actDO);
    }

    public void updateAct(ActDO actDO) {
        this.getSqlMapClientTemplate().update("ActDAO.updateAct", actDO);
    }

    public ActDO queryActById(long actId) {
        return (ActDO) this.getSqlMapClientTemplate().queryForObject("ActDAO.queryActById", actId);
    }

    public List<ActDO> queryActByPageNo(int pageNo, int size, String keyWord) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNo", (pageNo - 1) * size);
        param.put("size", size);
        param.put("keyWord", keyWord);
        return (List<ActDO>) this.getSqlMapClientTemplate().queryForList("ActDAO.queryActByPageNo", param);
    }

}
