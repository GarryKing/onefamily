package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;

import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface DonateDAO {

    public void insertDonate(DonateDO donateDO);

    public void updateDonate(DonateDO donateDO);

    public DonateDO queryDonateById(long peerId);

    public List<DonateDO> queryDonateByPageNo(int pageNo, int size, String keyWord);

}
