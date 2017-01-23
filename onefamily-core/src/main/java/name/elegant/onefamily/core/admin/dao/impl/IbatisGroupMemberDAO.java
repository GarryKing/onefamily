package name.elegant.onefamily.core.admin.dao.impl;

import name.elegant.onefamily.client.dataobject.onefamily.MemberDO;
import name.elegant.onefamily.core.admin.dao.GroupMemberDAO;
import name.elegant.onefamily.core.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Garry King on 2017/1/24.
 */
@Repository
public class IbatisGroupMemberDAO extends BaseDao implements GroupMemberDAO {

    public void insertMember(MemberDO memberDO) {
        this.getSqlMapClientTemplate().insert("GroupMemberDAO.insertMember", memberDO);
    }

    public void updateMember(MemberDO memberDO) {
        this.getSqlMapClientTemplate().update("GroupMemberDAO.updateMember", memberDO);
    }

    public void deleteMember(long memberId) {
        this.getSqlMapClientTemplate().delete("GroupMemberDAO.deleteMember", memberId);
    }

    public MemberDO queryMemberById(long memberId) {
        return (MemberDO) this.getSqlMapClientTemplate().queryForObject("GroupMemberDAO.queryMemberById", memberId);

    }

    public List<MemberDO> queryMemberByDonateId(long donateId) {
        return (List<MemberDO>) this.getSqlMapClientTemplate().queryForObject("GroupMemberDAO.queryMemberByDonateId", donateId);
    }

}
