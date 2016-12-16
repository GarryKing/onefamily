package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;

import java.io.IOException;
import java.util.List;

/**
 * Created by GarryKing on 2016/8/19.
 * E-mail:flyhzq@sina.com
 */
public interface PeerService {

    public void insertPeer(PeerDO peerDO) throws IOException;

    public void updatePeer(PeerDO peerDO) throws IOException;

    public PeerDO queryPeerById(long peerId);

    public List<PeerDO> queryPeerByPageNo(int pageNo, int size, String keyWord);

}
