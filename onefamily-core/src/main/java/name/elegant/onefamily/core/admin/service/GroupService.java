package name.elegant.onefamily.core.admin.service;

import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;
import name.elegant.onefamily.client.dataobject.onefamily.MemberDO;

import java.util.Date;
import java.util.List;

/**
 * Created by Garry King on 2017/1/23.
 */
public interface GroupService {

    List<GroupDO> queryGroupByPageNo(int pageNo, int pageSize, String keyWord);

    void insertGroup(GroupDO groupDO);

    void updateGroup(GroupDO groupDO, String insertSerialId);

    void addMember(MemberDO memberDO);

    void updateMember(MemberDO memberDO);

    void deleteMember(long memberId);

    MemberDO queryMemberByMemberId(long memberId);

}
