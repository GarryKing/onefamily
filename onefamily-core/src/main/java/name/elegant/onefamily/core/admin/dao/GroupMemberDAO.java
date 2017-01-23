package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.onefamily.MemberDO;

import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface GroupMemberDAO {

    public void insertMember(MemberDO memberDO);

    public void updateMember(MemberDO memberDO);

    public void deleteMember(long memberId);

    public MemberDO queryMemberById(long memberId);

    public List<MemberDO> queryMemberByDonateId(long donateId);

}
