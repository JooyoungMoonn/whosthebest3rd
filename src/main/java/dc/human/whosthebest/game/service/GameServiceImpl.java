/*
PROJECT        : whosthebest
PROGRAM ID    :  GameService.java
PROGRAM NAME    : game Service
DESCRIPTION    : game 관련 DB 데이터 전송
AUTHOR        : 허진욱
CREATED DATE    : 2024.06.21.
======================================================
*/
package dc.human.whosthebest.game.service;

import dc.human.whosthebest.game.dao.GameDAO;
import dc.human.whosthebest.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("gameService")
@Transactional(propagation = Propagation.REQUIRED)
public class GameServiceImpl implements  GameService {
    @Autowired
    private GameDAO gameDAO;

    /**
     * 사용자 ID에 해당하는 팀 정보를 로드합니다.
     *
     * @param uID 사용자 ID를 나타내는 문자열.
     * @return 사용자 ID에 해당하는 팀 정보 리스트.
     * @throws Exception 데이터 로드 중 발생할 수 있는 예외.
     */
    @Override
    public List<TeamInfoVO> loadMyTeam(String uID) throws Exception {
        List<TeamInfoVO> teamNameList = null;
        teamNameList = gameDAO.loadMyTeam(uID);
        // 리스트의 첫 번째 값의 tID 출력
        if (!teamNameList.isEmpty()) {
            int firstTeamTID = teamNameList.get(0).gettID();
            System.out.println("service 첫 번째 팀의 tID: " + firstTeamTID);
        } else {
            System.out.println("팀 정보 리스트가 비어 있습니다.");
        }
        return teamNameList;
    }
    /**
     * 지역과 검색어를 기반으로 경기장 목록을 검색합니다.
     * 이 메서드는 데이터 접근 객체(DAO)를 사용하여 주어진 지역과 검색어에 맞는 경기장 목록을 조회합니다.
     *
     * @param sRegion 선택한 지역
     * @param search  검색어
     * @return 경기장 목록을 포함하는 List<StadiumVO>
     * @throws Exception 오류가 발생할 경우 예외를 던집니다
     */
    @Override
    public List<StadiumVO> selectStadium(String sRegion, String search) throws  Exception {
        List<StadiumVO> stadiumList = null;
        System.out.println("service sRegion : " + sRegion);
        System.out.println("service search : " + search);
        stadiumList = gameDAO.selectStadium(sRegion, search);
        return stadiumList;
    }
    /**
     * 주어진 경기장 ID에 해당하는 경기장 세부 정보를 조회합니다.
     *
     * @param sID 경기장 ID를 나타내는 정수
     * @return 주어진 경기장 ID에 해당하는 {@link StadiumVO} 객체
     * @throws Exception 예외가 발생한 경우
     */
    @Override
    public StadiumVO stadiumDetail(int sID) throws  Exception {
        StadiumVO stadiumDetil = null;
        System.out.println("service sID : " + sID);
        stadiumDetil = gameDAO.stadiumDetail(sID);
        return stadiumDetil;
    }
    /**
     * 주어진 GameVO 객체를 사용하여 경기를 생성하는 메서드.
     * gResDate 필드를 초까지 포함하도록 포맷팅함.
     *
     * @param gameVO 생성할 경기의 세부 정보를 담은 GameVO 객체.
     * @return 경기 생성 작업의 결과를 나타내는 정수 (성공 시 1).
     * @throws Exception 경기 생성 과정에서 오류가 발생한 경우.
     */
    @Override
    public int createGame(GameVO gameVO) throws  Exception {
        //gResDate 최종 formatting
        gameVO.setgResDate(gameVO.getgResDate() + ":00");
        int createGameResult = gameDAO.createGame(gameVO);

        int createdGID = gameVO.getgID();
        if(createdGID != 0) {
            System.out.println("createdGID : " + createdGID);
        } else {
            System.out.println("createdGID : null 입니다.");
        }

        GameVO selectGameMaker = gameDAO.selectGameMaker(createdGID);

//        selectGameMaker.setgID(createdGID);
        SquadVO squadVO = new SquadVO();
        squadVO.setgID(createdGID);
        squadVO.settID(selectGameMaker.getgTeamID());
        squadVO.setuID(selectGameMaker.gettUserID());
        squadVO.setCreatedID(selectGameMaker.gettUserID());

        int insertSquadResult = gameDAO.insertSquad(squadVO);

        System.out.println("service insertSquadResult : " + insertSquadResult);

        return createGameResult;
    }
    /**
     * 주어진 조건을 기반으로 게임 목록을 조회합니다.
     *
     * @param pageNum        조회할 페이지 번호
     * @param rowNum         페이지당 표시할 행 수
     * @param sRegion        조회할 지역
     * @param search         검색할 키워드
     * @param IMakeGameuID   사용자가 만든 게임의 사용자 ID
     * @param IPartiGameuID  사용자가 참여한 게임의 사용자 ID
     * @return               조회된 게임 목록이 담긴 GameListVO 객체의 리스트
     * @throws Exception     조회 중 오류가 발생한 경우
     */
    @Override
    public List<GameListVO> selectGameList(int pageNum,
                                           int rowNum,
                                           String sRegion,
                                           String search,
                                           String IMakeGameuID,
                                           String IPartiGameuID
                                            ) throws Exception {
        System.out.println("Service parameter pageNum : " + pageNum);
        List<GameListVO> gameList = null;
        gameList = gameDAO.selectGameList(pageNum, rowNum, sRegion, search, IMakeGameuID, IPartiGameuID);
        if(!gameList.isEmpty()) {
            System.out.println("Service gameList 0 번째 gID : " + gameList.get(0).getgID());
            System.out.println("Service gameList의 길이 : " + gameList.size());
        } else {
            System.out.println("Service gameList가 비어있습니다. ");
            System.out.println("Service gameList의 길이 : " + gameList.size());
        }

        return gameList;
    }
    /**
     * 주어진 게임 ID와 사용자 ID를 기반으로 게임 정보를 조회합니다.
     *
     * @param gID  조회할 게임의 ID
     * @param uID  조회할 사용자의 ID
     * @return     조회된 게임 정보가 담긴 GameInfoVO 객체
     * @throws Exception 조회 중 오류가 발생한 경우
     */
    @Override
    public GameInfoVO selectGameInfo(int gID, String uID) throws Exception {
        GameInfoVO gameInfoVO = gameDAO.selectGameInfo(gID);
        List<TeamInfoVO> myTeamList = new ArrayList<>();
        if(gameInfoVO != null) {
            System.out.println("service gameInfo.gTitle : " + gameInfoVO.getgTitle());
            System.out.println("service gameInfo.getuName: " + gameInfoVO.getuName());
            SquadVO squadVO = new SquadVO();
            squadVO.setgID(gameInfoVO.getgID());
            squadVO.settID(gameInfoVO.getgTeamID());
            List<GameMemberListVO> gameHomeMemberList = gameDAO.selectGameTMemmber(squadVO);
            List<GCommentVO> gCommentsList = gameDAO.selectComments(gameInfoVO.getgID());
            if(gameInfoVO.gettAwayID() == 0) {
                myTeamList = gameDAO.loadMyTeam(uID);
                gameInfoVO.setMyTeamList(myTeamList);
                System.out.println("myTeamList.size()" + myTeamList.size());
            }
            if(!gameHomeMemberList.isEmpty()) {
                gameInfoVO.setGameMemberList(gameHomeMemberList);
                System.out.println("Service gameMemberList 첫 번째 uName : " + gameHomeMemberList.get(0).getuName());
            }
            if(!gCommentsList.isEmpty()) {
                gameInfoVO.setgCommentsList(gCommentsList);
                System.out.println("Service setgCommentsList 첫 번째 uName : " + gCommentsList.get(0).getuName());
            }
            gameInfoVO.setNowPartiMemberNum(gameDAO.nowPartiMemberNum(gID));
        }
        return gameInfoVO;
    }
    /**
     * 주어진 게임 ID와 팀 ID를 기반으로 원정 팀 정보를 조회합니다.
     *
     * @param gID  조회할 게임의 ID
     * @param tID  조회할 팀의 ID
     * @return     조회된 원정 팀 정보가 담긴 GameAwayTeamInfoVO 객체
     * @throws Exception 조회 중 오류가 발생한 경우
     */
    @Override
    public GameAwayTeamInfoVO selectAwayTeam(int gID, int tID) throws  Exception {
        GameAwayTeamInfoVO gameAwayTeamInfoVO = new GameAwayTeamInfoVO();
        List<GameMemberListVO> awayTeamMember = new ArrayList<>();
        SquadVO squadVO = new SquadVO();
        squadVO.setgID(gID);
        squadVO.settID(tID);
        String awayTeamName = gameDAO.selectAwayTeamName(tID);
        System.out.println("service awayTeamName : " + awayTeamName);
        awayTeamMember = gameDAO.selectGameTMemmber(squadVO);
        System.out.println("service awayTeamMember.get(0).getuName() : " + awayTeamMember.get(0).getuName());

        if(awayTeamName != null) {
            gameAwayTeamInfoVO.setAwayTeamName(awayTeamName);
        }
        if(!awayTeamMember.isEmpty()) {
            gameAwayTeamInfoVO.setAwayTeamMemberList(awayTeamMember);
        }
        int nowPartiMemberNum = gameDAO.nowPartiMemberNum(squadVO.getgID());
        gameAwayTeamInfoVO.setNowPartiMemberNum(nowPartiMemberNum);
        return gameAwayTeamInfoVO;
    }
    /**
     * 주어진 스쿼드 정보를 기반으로 원정 팀을 게임에 추가합니다.
     *
     * @param squadVO  추가할 원정 팀의 스쿼드 정보
     * @return         업데이트된 원정 팀 정보가 담긴 GameAwayTeamInfoVO 객체
     * @throws Exception 업데이트 중 오류가 발생한 경우
     */
    @Override
    public GameAwayTeamInfoVO awayTeamIntoGame(SquadVO squadVO) throws  Exception {
        GameAwayTeamInfoVO gameAwayTeamInfoVO = new GameAwayTeamInfoVO();
        List<GameMemberListVO> awayTeamMember = new ArrayList<>();
        String awayTeamName = null;
        int updateAwayTeamIDResult = 0;
        int checkDuplicateSquadResult = 0;
        int insertSquad = 0;
        System.out.println("service updateAndInsertAwayTeam : " + squadVO.gettID());
        GameVO gameVO = new GameVO();
        gameVO.setgID(squadVO.getgID());
        gameVO.settAwayID(squadVO.gettID());
        System.out.println(squadVO.gettID());

        int checkAwayTeamExistResult = gameDAO.checkAwayTeamExist(gameVO);
        System.out.println("before checkAwayTeamExistResult : " + checkAwayTeamExistResult);
        if(checkAwayTeamExistResult < 1) {
            updateAwayTeamIDResult = gameDAO.updateAwayTeamID(squadVO);
            System.out.println("updateAwayTeamIDResult : " + updateAwayTeamIDResult);
            checkAwayTeamExistResult = gameDAO.checkAwayTeamExist(gameVO);
            System.out.println("after checkAwayTeamExistResult : " + checkAwayTeamExistResult);
        }

        if(checkAwayTeamExistResult >= 1) {
            checkDuplicateSquadResult = gameDAO.checkDuplicateSquad(squadVO);
            gameAwayTeamInfoVO.setIsAwayTeamExist(true);
            if(checkDuplicateSquadResult < 1) {
                insertSquad = gameDAO.insertSquad(squadVO);
                System.out.println("insertSquad : " + insertSquad);
            }
            awayTeamName = gameDAO.selectAwayTeamName(squadVO.gettID());
            System.out.println("service awayTeamName : " + awayTeamName);
            awayTeamMember = gameDAO.selectGameTMemmber(squadVO);
            System.out.println("service awayTeamMember.get(0).getuName() : " + awayTeamMember.get(0).getuName());
            if(awayTeamName != null) {
                gameAwayTeamInfoVO.setAwayTeamName(awayTeamName);
            }
            if(!awayTeamMember.isEmpty()) {
                gameAwayTeamInfoVO.setAwayTeamMemberList(awayTeamMember);
            }
        }
        int nowPartiMemberNum = gameDAO.nowPartiMemberNum(squadVO.getgID());
        gameAwayTeamInfoVO.setNowPartiMemberNum(nowPartiMemberNum);
        return gameAwayTeamInfoVO;
    }
    /**
     * 주어진 스쿼드 정보를 기반으로 원정 팀 멤버를 게임에 추가합니다.
     *
     * @param squadVO  추가할 원정 팀의 스쿼드 정보
     * @return         업데이트된 원정 팀 정보가 담긴 GameAwayTeamInfoVO 객체
     * @throws Exception 추가 중 오류가 발생한 경우
     */
    @Override
    public GameAwayTeamInfoVO insertawayTeamMembr(SquadVO squadVO) throws Exception {
        GameAwayTeamInfoVO gameAwayTeamInfoVO = new GameAwayTeamInfoVO();
        List<GameMemberListVO> awayTeamMember = new ArrayList<>();
        String awayTeamName = null;
        int updateAwayTeamIDResult = 0;
        int checkDuplicateSquadResult = 0;
        int insertSquad = 0;
        System.out.println("service updateAndInsertAwayTeam : " + squadVO.gettID());
        GameVO gameVO = new GameVO();
        gameVO.setgID(squadVO.getgID());
        gameVO.settAwayID(squadVO.gettID());
        System.out.println(squadVO.gettID());

        int checkAwayTeamExistResult = gameDAO.checkAwayTeamExist(gameVO);
        System.out.println("checkAwayTeamExistResult : " + checkAwayTeamExistResult);
        if(checkAwayTeamExistResult >= 1) {
            gameAwayTeamInfoVO.setIsAwayTeamExist(true);
            awayTeamName = gameDAO.selectAwayTeamName(squadVO.gettID());
            System.out.println("service awayTeamName : " + awayTeamName);
            awayTeamMember = gameDAO.selectGameTMemmber(squadVO);
            System.out.println("service awayTeamMember.get(0).getuName() : " + awayTeamMember.get(0).getuName());
            checkDuplicateSquadResult = gameDAO.checkDuplicateSquad(squadVO);
            if(checkDuplicateSquadResult < 1) {
                insertSquad = gameDAO.insertSquad(squadVO);
                System.out.println("insertSquad : " + insertSquad);
                gameAwayTeamInfoVO.setCheckDuplicateSquadResult(true);
            } else {
                gameAwayTeamInfoVO.setCheckDuplicateSquadResult(false);
            }
        } else {
            gameAwayTeamInfoVO.setIsAwayTeamExist(false);
        }


        if(awayTeamName != null) {
            gameAwayTeamInfoVO.setAwayTeamName(awayTeamName);
        }
        if(!awayTeamMember.isEmpty()) {
            gameAwayTeamInfoVO.setAwayTeamMemberList(awayTeamMember);
        }
        int nowPartiMemberNum = gameDAO.nowPartiMemberNum(squadVO.getgID());
        gameAwayTeamInfoVO.setNowPartiMemberNum(nowPartiMemberNum);
        return gameAwayTeamInfoVO;
    }
    /**
     * 새로운 게임 댓글을 삽입하고, 업데이트된 댓글 목록을 조회합니다.
     *
     * @param gCommentVO  삽입할 게임 댓글의 세부 정보
     * @return            업데이트된 게임 댓글 목록이 담긴 GCommentVO 객체의 리스트
     * @throws Exception  삽입 및 조회 중 오류가 발생한 경우
     */
    @Override
    public List<GCommentVO> insertComments(GCommentVO gCommentVO) throws Exception {
        int insertCommentsResult =  gameDAO.insertComments(gCommentVO);
        List<GCommentVO> gCommentsList = gameDAO.selectComments(gCommentVO.getgID());
        System.out.println("gCommentsList.get(0).getuName() : " + gCommentsList.get(0).getuName());
        return gCommentsList;
    }
    @Override
    public List<GameMemberListVO> insertAndSelectHomeTeam(SquadVO squadVO) throws  Exception {
        List<GameMemberListVO> gameMemberList = new ArrayList<>();
        int checkDuplicateSquadResult = gameDAO.checkDuplicateSquad(squadVO);
        if(checkDuplicateSquadResult >= 1) {
            return gameMemberList;
        } else {
            int insertSquadResult = gameDAO.insertSquad(squadVO);
            if(!(insertSquadResult < 1)) {
                gameMemberList = gameDAO.selectGameTMemmber(squadVO);
                System.out.println("Serviece gameMemberList의 첫 번째 uNmae : " + gameMemberList.get(0).getuName());
            }
            return gameMemberList;
        }
    }

    @Override
    public String selectAwayTeamName(int tID) throws Exception {
        String tName = null;
        tName = gameDAO.selectAwayTeamName(tID);
        return tName;
    }

    @Override
    public int insertGameResult(GResultVO gResultVO) throws Exception {
        int insertGameResultResult = 0;
        insertGameResultResult = gameDAO.insertGameResult(gResultVO);
        return insertGameResultResult;
    }

    @Override
    public List<StadiumVO> selectStadiumInfo(int pageNum, int rowNum, String sRegion, String search) throws Exception {
        System.out.println("service rowNum : " + rowNum + " pageNum" + pageNum);
        List<StadiumVO> stadiumVO = gameDAO.selectStadiumInfo(pageNum, rowNum, sRegion, search);
        return stadiumVO;
    }
    @Override
    public GameVO selectForModGame(int gID) throws Exception {
        GameVO selectForModGameResult = gameDAO.selectForModGame(gID);
        return selectForModGameResult;
    }
    @Override
    public int deleteGame(int gID) throws Exception {
        int deleteGameResult =  gameDAO.deleteGame(gID);
        return deleteGameResult;
    }

    @Override
    public int modGame(GameVO gameVO) throws  Exception {
        int modGameResult = gameDAO.modGame(gameVO);
        return modGameResult;
    }

    @Override
    public int updateGameState(int gID, int gStatus) throws Exception {
        int updateGameStateResult = gameDAO.updateGameState(gID, gStatus);
        return updateGameStateResult;
    }
}
