package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;

import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface ActDAO {

    public void insertAct(ActDO actDO);

    public void updateAct(ActDO actDO);

    public ActDO queryActById(long actId);

    public List<ActDO> queryActByPageNo(int pageNo, int size, String keyWord);

}
