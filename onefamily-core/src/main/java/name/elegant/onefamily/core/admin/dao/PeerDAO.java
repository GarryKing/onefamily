package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;

import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface PeerDAO {

    public void insertPeer(PeerDO peerDO);

    public void updatePeer(PeerDO peerDO);

    public PeerDO queryPeerById(long peerId);

    public PeerDO queryPeerByBizId(String bizId);

    public List<PeerDO> queryPeerByPageNo(int pageNo, int size, String keyWord);

}
