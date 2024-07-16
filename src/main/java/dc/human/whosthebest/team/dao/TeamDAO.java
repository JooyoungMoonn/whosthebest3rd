package dc.human.whosthebest.team.dao;


import dc.human.whosthebest.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("teamDAO")
public interface TeamDAO {
    public List selectTeamList(@Param("userID") String userID) throws DataAccessException;
    public int insertTeamInfo(TeamInfoVO teamInfoVO) throws DataAccessException;
    public int insertTeamMember(TeamMemberVO teamMemberVO) throws DataAccessException;
    public List<TeamInfoVO> selectTeamsByUserId(String userID) throws DataAccessException;
    public TeamProfileVO selectTeamProfile(int tID) throws DataAccessException;
//    public List selectRanking() throws DataAccessException;
    //허진욱
    public List<TeamInfoVO> selectRanking(@Param("region") String region,
                                          @Param("search") String search
                                         ) throws DataAccessException;
    public List<TeamMemberVO> selectTeamMembers(int tID) throws DataAccessException;

    public List<GameRecordInfoListVO> selectGResultInfo(@Param("tID") int tID,
                                                        @Param("resultType") String resultType
    ) throws DataAccessException;

    public List<GameListVO> selectGameSchedule(int tID) throws DataAccessException;
}
