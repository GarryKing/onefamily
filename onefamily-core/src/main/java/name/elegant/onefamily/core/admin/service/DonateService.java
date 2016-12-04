package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;

import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
public interface DonateService {

    public void insertDonate(DonateDO donateDO);

    public void updateDonate(DonateDO donateDO);

    public DonateDO queryDonateById(long donateId);

    public List<DonateDO> queryDonateByPageNo(int pageNo, int size, String keyWord);

}
