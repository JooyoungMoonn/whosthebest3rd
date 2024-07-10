package dc.human.whosthebest.team.service;

import dc.human.whosthebest.aboutteam.dao.AboutTeamDAO;
import dc.human.whosthebest.team.dao.TeamDAO;
import dc.human.whosthebest.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("teamService")
@Transactional(propagation = Propagation.REQUIRED)
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private AboutTeamDAO aboutTeamDAO;

    //날짜 format을 위한 필드
    private static final SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public List listTeams() throws Exception {
        List teamsList = null;
        teamsList = teamDAO.selectTeamList();
        return teamsList;
    }

    //팀 생성 메소드
    @Override
    public int insertTeamInfo(TeamInfoVO team) throws Exception {
        return teamDAO.insertTeamInfo(team);
    }

    //팀 가입하기 메소드
    @Override
    public int insertTeamMember(TeamMemberVO teamMemberVO) throws Exception {
        try {
            return teamDAO.insertTeamMember(teamMemberVO);
        } catch (DataAccessException e) {
            // DataAccessException 처리
            System.out.println("DataAccessException 발생: " + e.getMessage());
            // 필요한 경우 로그를 남기거나 다른 예외로 변환
            return 0; // 또는 적절한 값을 반환하거나 예외를 다시 던질 수 있습니다.
        } catch (Exception e) {
            // 기타 예외 처리
            System.out.println("Exception 발생: " + e.getMessage());
            // 필요한 경우 로그를 남기거나 다른 예외로 변환
            return 0; // 또는 적절한 값을 반환하거나 예외를 다시 던질 수 있습니다.
        }
    };

    //팀 생성과 동시에 팀장 정보 team_member 테이블에 저장을 위한 메소드
    //insertTeamInfo 안에 insertTeamMember 넣어 구현
    //Service 단에서 처리하기 위해서는 이러한 작업이 필요함
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int createTeamAndAddMember(TeamInfoVO teamInfo, String userID) throws Exception {
        teamInfo.setCreatedID(userID);
        int createTeamAndAddMemberResult = teamDAO.insertTeamInfo(teamInfo);

        if(createTeamAndAddMemberResult >0) {
            TeamMemberVO teamMember = new TeamMemberVO();
            teamMember.setCreatedID(userID);
            teamMember.setuID(userID);
            teamMember.settID(teamInfo.gettID());
            teamDAO.insertTeamMember(teamMember);
        } else {
            throw new Exception("Team 생성 실패");
        }
        return createTeamAndAddMemberResult;
    };

    @Override
    public List<TeamInfoVO> getTeamsByUserId(String userID) throws Exception{
        return teamDAO.selectTeamsByUserId(userID);
    };

    @Override
    public TeamProfileVO getTeamProfile(int tID) throws Exception {
        String resultType=null;
        TeamProfileVO teamProfileVO = new TeamProfileVO();
        GameRecordVO gameRecordVO = new GameRecordVO();
        List<GameRecordInfoListVO> gameResultInfoList = new ArrayList<>();

        teamProfileVO = teamDAO.selectTeamProfile(tID);
        gameRecordVO = aboutTeamDAO.getMatchCount(tID);
        gameResultInfoList = teamDAO.selectGResultInfo(tID, resultType);
        gameRecordVO.setGameRecordInfoListVO(gameResultInfoList);
        teamProfileVO.setGameRecordVO(gameRecordVO);

        return teamDAO.selectTeamProfile(tID);
    }

    //AboutTeamDAO 이식
    @Override
    public List<GameListVO> selectGameSchedule(int tID) throws Exception {
        List<GameListVO> gameList = teamDAO.selectGameSchedule(tID);
        //날짜 변환 후 반환
        return gameList.stream().map(this::convertGResDate).collect(Collectors.toList());
    }

    // gresdate 날짜 변환 메서드
    private GameListVO convertGResDate(GameListVO game) {
        game.setgResDate(formatDate(game.getgResDate()));
        return game;
    }

    // 날짜 형식 변환 메서드
    private String formatDate(String dateStr) {
        try {
            return displayFormat.format(originalFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return dateStr; // 변환 실패 시 원본 문자열 반환
        }
    }


    @Override
    public GameRecordVO selectGameRecordInfo(int tID, String resultType) throws Exception {
        GameRecordVO gameRecordVO = new GameRecordVO();
        List<GameRecordInfoListVO> gameResultInfoList = new ArrayList<>();
        gameRecordVO = aboutTeamDAO.getMatchCount(tID);
        gameResultInfoList = teamDAO.selectGResultInfo(tID, resultType);
        gameRecordVO.setGameRecordInfoListVO(gameResultInfoList);
        return gameRecordVO;
    }
    @Override
    public List<TeamMemberVO> getTeamMembers(int tID) throws Exception {
        List<TeamMemberVO> teamMemberList = teamDAO.selectTeamMembers(tID);
        return teamMemberList;
    }

    @Override
    public List ranking() throws Exception {
        List ranking = null;
        ranking = teamDAO.selectRanking();
        return ranking;
    };



}
