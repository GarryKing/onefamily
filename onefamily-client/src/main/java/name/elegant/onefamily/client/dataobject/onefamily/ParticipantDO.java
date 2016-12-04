package name.elegant.onefamily.client.dataobject.onefamily;

/**
 * Created by GarryKing on 2016/12/5.
 */
public class ParticipantDO {

    private long contributorId;

    private String contributorBizId;

    private String contributorName;

    private String starLevel;

    private String thisActDuration;

    private String thisStarLevel;

    private String role;

    public long getContributorId() {
        return contributorId;
    }

    public void setContributorId(long contributorId) {
        this.contributorId = contributorId;
    }

    public String getContributorBizId() {
        return contributorBizId;
    }

    public void setContributorBizId(String contributorBizId) {
        this.contributorBizId = contributorBizId;
    }

    public String getContributorName() {
        return contributorName;
    }

    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public String getThisActDuration() {
        return thisActDuration;
    }

    public void setThisActDuration(String thisActDuration) {
        this.thisActDuration = thisActDuration;
    }

    public String getThisStarLevel() {
        return thisStarLevel;
    }

    public void setThisStarLevel(String thisStarLevel) {
        this.thisStarLevel = thisStarLevel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}