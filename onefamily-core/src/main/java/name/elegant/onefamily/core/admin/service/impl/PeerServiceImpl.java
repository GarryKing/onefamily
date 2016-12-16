package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;
import name.elegant.onefamily.client.dataobject.util.text.MoneyUtil;
import name.elegant.onefamily.client.dataobject.util.text.StringUtil;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.dao.DonateDAO;
import name.elegant.onefamily.core.admin.dao.PeerDAO;
import name.elegant.onefamily.core.admin.service.PeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by GarryKing on 2016/12/3.
 */
@Service
public class PeerServiceImpl implements PeerService {

    @Autowired
    private PeerDAO peerDAO;

    @Autowired
    private DonateDAO donateDAO;

    @Autowired
    private ContributorDAO contributorDAO;

    public static final String FILE_PATH = "E:/ ‹÷˙»ÀÕ∑œÒ/";

    public void insertPeer(PeerDO peerDO) throws IOException {
        ContributorDO contributorDO = contributorDAO.queryContributorByBizId(peerDO.getContributorBizId());
        if (contributorDO != null) peerDO.setContributorId(contributorDO.getContributorId());
        long id = peerDAO.insertPeer(peerDO);
        peerDO.setBizId(buildBizId(id));
        peerDO.setPic(uploadFile(peerDO.getFile(), peerDO));
        peerDAO.updatePeer(peerDO);
    }

    public void updatePeer(PeerDO peerDO) throws IOException {
        ContributorDO contributorDO = contributorDAO.queryContributorByBizId(peerDO.getContributorBizId());
        if (contributorDO != null) peerDO.setContributorId(contributorDO.getContributorId());
        PeerDO oldPeerDO = peerDAO.queryPeerById(peerDO.getPeerId());
        peerDO.setBizId(oldPeerDO.getBizId());
        if (peerDO.getFile() != null && !StringUtil.isBlank(peerDO.getFile().getOriginalFilename())) {
            peerDO.setPic(uploadFile(peerDO.getFile(), peerDO));
        } else {
            PeerDO old = peerDAO.queryPeerById(peerDO.getPeerId());
            peerDO.setPic(old.getPic());
        }
        peerDAO.updatePeer(peerDO);
    }

    public PeerDO queryPeerById(long peerId) {
        return null;
    }

    public List<PeerDO> queryPeerByPageNo(int pageNo, int size, String keyWord) {
        List<PeerDO> list = peerDAO.queryPeerByPageNo(pageNo, size, keyWord);
        fillList(list);
        return list;
    }

    private String buildBizId(long id) {
        String result = "S";
        result += ((1000000 + id) + "").substring(1);
        return result;
    }

    private void fillList(List<PeerDO> list) {
        if (list != null) {
            for (PeerDO peerDO : list) {
                List<DonateDO> result = donateDAO.queryDonateByPeerId(peerDO.getPeerId());
                double payAmount = 0.00D;
                double lastPayAmount = 0.00D;
                Date lastPayTime = null;
                if (result != null) {
                    for (DonateDO donateDO : result) {
                        try {
                            payAmount += MoneyUtil.stringToMoney(donateDO.getPayAmount()).doubleValue();
                            if (lastPayTime == null || (lastPayTime.getTime() < donateDO.getPayTime().getTime())) {
                                lastPayAmount = MoneyUtil.stringToMoney(donateDO.getPayAmount()).doubleValue();
                                lastPayTime = donateDO.getPayTime();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                peerDO.setLastPayAmount(lastPayAmount);
                peerDO.setLastPayTime(lastPayTime);
                peerDO.setTotalAidedAmount(payAmount);
            }
        }
    }

    public String uploadFile(MultipartFile file, PeerDO peerDO) throws IOException {
        String fileName = file.getOriginalFilename();
        File tempFile = new File(FILE_PATH, peerDO.getBizId() + "-" + peerDO.getAidedName() + "." + fileName.substring(fileName.lastIndexOf(".") + 1));
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdir();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        } else {
            tempFile.delete();
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        return FILE_PATH + tempFile.getName();
    }

    public File getFile(String fileName) {
        return new File(FILE_PATH, fileName);
    }

}
