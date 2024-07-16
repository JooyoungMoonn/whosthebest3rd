package dc.human.whosthebest.team.service;

import dc.human.whosthebest.vo.*;

import java.util.List;

public interface TeamService {
    public List listTeams(String userID) throws Exception;
    public int insertTeamInfo(TeamInfoVO teamInfoVO) throws Exception;
    public int insertTeamMember(TeamMemberVO teamMemberVO) throws Exception;
    public int createTeamAndAddMember(TeamInfoVO teamInfo, String userID) throws Exception;
    public List<TeamInfoVO> getTeamsByUserId(String userID) throws Exception;
    public TeamProfileVO getTeamProfile(int tID) throws Exception;
    public List<GameListVO> selectGameSchedule(int tID) throws Exception;
    public GameRecordVO selectGameRecordInfo(int tID, String resultType) throws Exception;
//    public List ranking() throws Exception;
    //허진욱
    public List<TeamInfoVO> ranking(String region, String search) throws Exception;

    public List<TeamMemberVO> getTeamMembers(int tID) throws Exception;
}
