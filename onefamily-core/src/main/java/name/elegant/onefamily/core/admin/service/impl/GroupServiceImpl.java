package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;
import name.elegant.onefamily.client.dataobject.onefamily.MemberDO;
import name.elegant.onefamily.client.dataobject.util.text.MoneyUtil;
import name.elegant.onefamily.client.dataobject.util.text.StringUtil;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.dao.GroupDAO;
import name.elegant.onefamily.core.admin.dao.GroupMemberDAO;
import name.elegant.onefamily.core.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    @Autowired
    private ContributorDAO contributorDAO;

    public List<GroupDO> queryGroupByPageNo(int pageNo, int pageSize, String keyWord) {
        List<GroupDO> groupDOList = groupDAO.queryGroupByPageNo(pageNo, pageSize, keyWord);
        fillGroupMembers(groupDOList);
        return groupDOList;
    }

    public void insertGroup(GroupDO groupDO) {
        long id = groupDAO.insertGroup(groupDO);
        groupDO = groupDAO.queryGroupById(id);
        updateGroup(groupDO, generateSerialId(id));
    }

    public void updateGroup(GroupDO groupDO, String insertSerialId) {
        GroupDO old = groupDAO.queryGroupById(groupDO.getDonateId());
        if (StringUtil.isBlank(insertSerialId))
            groupDO.setSerialId(old.getSerialId());
        else
            groupDO.setSerialId(insertSerialId);
        groupDAO.updateGroup(groupDO);
    }

    public void addMember(MemberDO memberDO) {
        fillContributorInfo(memberDO);
        List<MemberDO> list = groupMemberDAO.queryMemberByDonateAndBizId(memberDO.getDonateId(), memberDO.getContributorId());
        if (list == null || list.size() == 0)
            groupMemberDAO.insertMember(memberDO);
        else
            groupMemberDAO.updateMember(memberDO);
    }

    private void fillContributorInfo(MemberDO memberDO) {
        ContributorDO contributorDO = contributorDAO.queryContributorById(memberDO.getContributorId());
        if (contributorDO == null)
            contributorDO = contributorDAO.queryContributorByBizId(memberDO.getContributorBizId());
        if (contributorDO != null) {
            memberDO.setContributorId(contributorDO.getContributorId());
            memberDO.setContributorBizId(contributorDO.getBizId());
            memberDO.setContributorName(contributorDO.getContributorName());
        }
    }

    public void updateMember(MemberDO memberDO) {
        MemberDO old = groupMemberDAO.queryMemberById(memberDO.getMemberId());
        memberDO.setDonateId(old.getDonateId());
        fillContributorInfo(memberDO);
        groupMemberDAO.updateMember(memberDO);
    }

    public void deleteMember(long memberId) {
        groupMemberDAO.deleteMember(memberId);
    }

    public MemberDO queryMemberByMemberId(long memberId) {
        return groupMemberDAO.queryMemberById(memberId);
    }

    private void fillGroupMembers(List<GroupDO> groupDOList) {
        if (groupDOList == null) return;
        for (GroupDO groupDO : groupDOList) {
            try {
                if (groupDO.getDonateId() <= 0) continue;
                groupDO.setMemberList(groupMemberDAO.queryMemberByDonateId(groupDO.getDonateId()));
                if (groupDO.getMemberList() != null) {
                    for (MemberDO memberDO : groupDO.getMemberList()) {
                        fillContributorInfo(memberDO);
                        double old = groupDO.getTotal();
                        old += MoneyUtil.stringToMoney(memberDO.getPayAmount()).doubleValue();
                        groupDO.setTotal(old);
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    private String generateSerialId(long id) {
        String result = "JG";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        result += sdf.format(new Date()) + "-";
        result += ((1000000 + id) + "").substring(1);
        return result;
    }

}
