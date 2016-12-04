package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.core.admin.dao.ActDAO;
import name.elegant.onefamily.core.admin.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
@Service
public class ActServiceImpl implements ActService {

    @Autowired
    private ActDAO actDAO;

    public void insertAct(ActDO actDO) {
        actDAO.insertAct(actDO);
    }

    public void updateAct(ActDO actDO) {
        actDAO.updateAct(actDO);
    }

    public ActDO queryActById(long actId) {
        return actDAO.queryActById(actId);
    }

    public List<ActDO> queryActByPageNo(int pageNo, int size, String keyWord) {
        return actDAO.queryActByPageNo(pageNo, size, keyWord);
    }

}
