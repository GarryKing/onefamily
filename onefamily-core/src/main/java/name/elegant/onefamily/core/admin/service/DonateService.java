package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
public interface DonateService {

    public String insertDonate(HttpServletRequest request, DonateDO donateDO);

    public String updateDonate(DonateDO donateDO);

    public DonateDO queryDonateById(long donateId);

    public List<DonateDO> queryDonateByPageNo(int pageNo, int size, String keyWord);

}
