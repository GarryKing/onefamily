package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;

import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface GroupDAO {

    public void insertGroup(GroupDO groupDO);

    public void updateGroup(GroupDO groupDO);

    public GroupDO queryGroupById(long groupId);

    public List<GroupDO> queryGroupByPageNo(int pageNo, int size, String keyWord);

}
