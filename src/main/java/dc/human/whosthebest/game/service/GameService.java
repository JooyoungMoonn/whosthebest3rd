/*
PROJECT        : whosthebest
PROGRAM ID    :  GameService.java
PROGRAM NAME    : game Service Interface
DESCRIPTION    : game 관련 DB 데이터 전송 Interface
AUTHOR        : 허진욱
CREATED DATE    : 2024.06.21.
======================================================
*/
package dc.human.whosthebest.game.service;

import dc.human.whosthebest.vo.*;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface GameService {
    public List<TeamInfoVO> loadMyTeam(String uID) throws Exception;
    public List<StadiumVO> selectStadium(String sRegion, String search) throws  Exception;
    public StadiumVO stadiumDetail(int sID) throws  Exception;
    public int createGame(GameVO gameVO) throws  Exception;
    public List<GameListVO> selectGameList(int pageNum) throws Exception;
}
