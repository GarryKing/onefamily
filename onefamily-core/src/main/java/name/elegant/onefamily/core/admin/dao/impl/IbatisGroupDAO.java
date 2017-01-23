package name.elegant.onefamily.core.admin.dao.impl;

import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;
import name.elegant.onefamily.core.admin.dao.GroupDAO;
import name.elegant.onefamily.core.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Garry King on 2017/1/24.
 */
@Repository
public class IbatisGroupDAO extends BaseDao implements GroupDAO {

    public void insertGroup(GroupDO groupDO) {
        this.getSqlMapClientTemplate().insert("GroupDAO.insertGroup", groupDO);
    }

    public void updateGroup(GroupDO groupDO) {
        this.getSqlMapClientTemplate().update("GroupDAO.updateGroup", groupDO);
    }

    public GroupDO queryGroupById(long groupId) {
        return (GroupDO) this.getSqlMapClientTemplate().queryForObject("GroupDAO.queryGroupById", groupId);
    }

    public List<GroupDO> queryGroupByPageNo(int pageNo, int size, String keyWord) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNo", (pageNo - 1) * size);
        param.put("size", size);
        param.put("keyWord", keyWord);
        return (List<GroupDO>) this.getSqlMapClientTemplate().queryForList("GroupDAO.queryGroupByPageNo", param);
    }

}
