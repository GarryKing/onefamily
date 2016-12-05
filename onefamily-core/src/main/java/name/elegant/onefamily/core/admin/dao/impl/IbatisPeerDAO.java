package name.elegant.onefamily.core.admin.dao.impl;

import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;
import name.elegant.onefamily.core.admin.dao.PeerDAO;
import name.elegant.onefamily.core.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GarryKing on 2016/12/3.
 */
@Repository
public class IbatisPeerDAO extends BaseDao implements PeerDAO {

    public void insertPeer(PeerDO peerDO) {
        this.getSqlMapClientTemplate().insert("PeerDAO.insertPeer", peerDO);
    }

    public void updatePeer(PeerDO peerDO) {
        this.getSqlMapClientTemplate().update("PeerDAO.updatePeer", peerDO);
    }

    public PeerDO queryPeerById(long peerId) {
        return (PeerDO) this.getSqlMapClientTemplate().queryForObject("PeerDAO.queryPeerById", peerId);
    }

    public PeerDO queryPeerByBizId(String bizId) {
        return (PeerDO) this.getSqlMapClientTemplate().queryForObject("PeerDAO.queryPeerByBizId", bizId);
    }

    public List<PeerDO> queryPeerByPageNo(int pageNo, int size, String keyWord) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNo", (pageNo - 1) * size);
        param.put("size", size);
        param.put("keyWord", keyWord);
        return (List<PeerDO>) this.getSqlMapClientTemplate().queryForList("PeerDAO.queryPeerByPageNo", param);
    }

}
