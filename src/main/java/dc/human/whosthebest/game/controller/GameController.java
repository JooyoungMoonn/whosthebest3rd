/*
PROJECT        : whosthebest
PROGRAM ID    :  GameController.java
PROGRAM NAME    : game Controller Interface
DESCRIPTION    : game 관련 views 데이터 전송 인터페이스
AUTHOR        : 허진욱
CREATED DATE    : 2024.06.21.
======================================================
*/
package dc.human.whosthebest.game.controller;

import dc.human.whosthebest.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public interface GameController {
    public ModelAndView gameMake(@ModelAttribute("gameVO") GameVO gameVO,
                                 @SessionAttribute(name = "loggedID", required = false) String loggedID
                                 ) throws Exception;

    public ModelAndView selectStadium(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public List<StadiumVO> searchStadiumName(@RequestParam(value = "sRegion", required = false) String sRegion,
                                             @RequestParam(value = "search", required = false) String search) throws Exception;

    public StadiumVO stadiumDetail(@RequestParam(value = "sID", required = false) String sID) throws Exception;

    public ModelAndView createGame(@RequestParam("gTeamID") String gTeamID,
                                   @RequestParam("gTitle") String gTitle,
                                   @RequestParam("gTag") String gTag,
                                   @RequestParam("gMinMember") int gMinMember,
                                   @RequestParam("gInfo") String gInfo,
                                   @RequestParam("sID") int sID,
                                   @RequestParam("sNum") int sNum,
                                   @RequestParam("gTime") int gTime,
                                   @RequestParam("gResDate") String gResDate) throws Exception;
    public ModelAndView selectGameList(HttpServletRequest request, HttpServletResponse response) throws Exception;
    public List<GameListVO> selectGameList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam(value = "rowNum", required = false, defaultValue = "0") int rowNum,
                                           @RequestParam(value = "sRegion", required = false) String sRegion,
                                           @RequestParam(value = "search", required = false) String search,
                                           @RequestParam(value = "IMakeGameuID", required = false) String IMakeGameuID,
                                           @RequestParam(value = "IPartiGameuID", required = false) String IPartiGameuID
                                          ) throws Exception;
    public ModelAndView selectGameInfo(@RequestParam("gID") int gID) throws Exception;
    public List<GCommentVO> insertComments(@ModelAttribute("gCommentVO") GCommentVO gCommentVO)  throws Exception;
    public  List<GameMemberListVO> partiHomeTeam(@RequestParam("gID") int gID,
                                                 @RequestParam("gTeamID") int gTeamID
                                                 ) throws Exception;
    public GameAwayTeamInfoVO insertAndSelectAwayTeam(@RequestParam("gID") int gID,
                                                       @RequestParam("tAwayID") int tAwayID
                                                      ) throws Exception;
    public GameAwayTeamInfoVO insertawayTeamMembr(@RequestParam("gID") int gID,
                                                   @RequestParam("tAwayID") int tAwayID
                                                    ) throws Exception;

    public String intoGameResult(@RequestParam("gID") int gID,
                                       @RequestParam("sRegion") String sRegion,
                                       @RequestParam("gTitle") String gTitle,
                                       @RequestParam("tID") int tID,
                                       @RequestParam("tAwayID") int tAwayID,
                                      RedirectAttributes redirectAttributes) throws Exception;
    public ModelAndView intoGameResultHadler(@RequestParam(value="gID", required = false, defaultValue = "0") int gID,
                                             @RequestParam(value="sRegion", required = false) String sRegion,
                                             @RequestParam(value="gTitle", required = false)String gTitle,
                                             @RequestParam(value="tID", required = false) int tID,
                                             @RequestParam(value="tAwayID", required = false) int tAwayID
                                             ) throws Exception;
    public ModelAndView insertGameResult(@RequestParam(value="gID", required = false, defaultValue = "0") int gID,
                                         @RequestParam(value="gTeamID", required = false, defaultValue = "0") int gTeamID,
                                         @RequestParam(value="tAwayID", required = false, defaultValue = "0") int tAwayID,
                                         @RequestParam(value="homeTeamScoreHidden", required = false, defaultValue = "0") int homeTeamScoreHidden,
                                         @RequestParam(value="awayTeamScoreHidden", required = false, defaultValue = "0") int awayTeamScoreHidden,
                                         @RequestParam(value="uID", required = false) String uID
                                         ) throws Exception;
    public ModelAndView selectStadiumInfo() throws Exception;
    public List<StadiumVO> searchStadiumInfo(@RequestParam(value="pageNum", required = false, defaultValue = "1") int pageNum,
                                             @RequestParam(value="rowNum", required = false, defaultValue = "0") int rowNum,
                                             @RequestParam(value="sRegion", required = false) String sRegion,
                                             @RequestParam(value="search", required = false) String search
                                             ) throws Exception;
    public ModelAndView deleteGame(@RequestParam(value="gID") int gID) throws Exception;
    public String goToModPage(@RequestParam(value="gID") int gID, RedirectAttributes redirectAttributes) throws Exception;
    public String modGameResult(@RequestParam("gID") int gID,
                                @RequestParam("gTeamID") String gTeamID,
                                @RequestParam("gTitle") String gTitle,
                                @RequestParam("gTag") String gTag,
                                @RequestParam("gMinMember") int gMinMember,
                                @RequestParam("gInfo") String gInfo,
                                @RequestParam("sID") int sID,
                                @RequestParam("sNum") int sNum,
                                @RequestParam("gTime") int gTime,
                                @RequestParam("gResDate") String gResDate,
                                RedirectAttributes redirectAttributes
                                ) throws Exception;
}