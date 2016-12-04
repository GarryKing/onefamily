package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.core.admin.dao.DonateDAO;
import name.elegant.onefamily.core.admin.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
@Service
public class DonateServiceImpl implements DonateService {

    @Autowired
    private DonateDAO donateDAO;

    public void insertDonate(DonateDO donateDO) {
        donateDAO.insertDonate(donateDO);
    }

    public void updateDonate(DonateDO donateDO) {
        donateDAO.updateDonate(donateDO);
    }

    public DonateDO queryDonateById(long donateId) {
        return donateDAO.queryDonateById(donateId);
    }

    public List<DonateDO> queryDonateByPageNo(int pageNo, int size, String keyWord) {
        return donateDAO.queryDonateByPageNo(pageNo, size, keyWord);
    }

}
