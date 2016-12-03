package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;

import java.util.List;

/**
 * Created by GarryKing on 2016/8/19.
 * E-mail:flyhzq@sina.com
 */
public interface PeerService {

    public void insertPeer(PeerDO peerDO);

    public void updatePeer(PeerDO peerDO);

    public PeerDO queryPeerById(long peerId);

    public List<PeerDO> queryPeerByPageNo(int pageNo, int size, String keyWord);

}
