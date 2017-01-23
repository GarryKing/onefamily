package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;
import name.elegant.onefamily.client.dataobject.onefamily.MemberDO;
import name.elegant.onefamily.core.admin.dao.GroupDAO;
import name.elegant.onefamily.core.admin.dao.GroupMemberDAO;
import name.elegant.onefamily.core.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Garry King on 2017/1/23.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private GroupMemberDAO groupMemberDAO;

    public List<GroupDO> queryGroupByPageNo(int pageNo, int pageSize, String keyWord) {
        List<GroupDO> groupDOList = groupDAO.queryGroupByPageNo(pageNo, pageSize, keyWord);
        fillGroupMembers(groupDOList);
        return groupDOList;
    }

    public void insertGroup(GroupDO groupDO) {
        groupDAO.insertGroup(groupDO);
    }

    public void updateGroup(GroupDO groupDO) {
        groupDAO.updateGroup(groupDO);
    }

    public void addMember(MemberDO memberDO) {
        groupMemberDAO.insertMember(memberDO);
    }

    public void updateMember(MemberDO memberDO) {
        groupMemberDAO.updateMember(memberDO);
    }

    public void deleteMember(long memberId) {
        groupMemberDAO.deleteMember(memberId);
    }

    private void fillGroupMembers(List<GroupDO> groupDOList) {
        if (groupDOList == null) return;
        for (GroupDO groupDO : groupDOList) {
            try {
                if (groupDO.getDonateId() <= 0) continue;
                groupDO.setMemberList(groupMemberDAO.queryMemberByDonateId(groupDO.getDonateId()));
            } catch (Exception e) {

            }
        }
    }

}
