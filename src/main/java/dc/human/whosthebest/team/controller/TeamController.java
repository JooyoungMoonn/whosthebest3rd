package dc.human.whosthebest.team.controller;

import dc.human.whosthebest.vo.TeamInfoVO;
import dc.human.whosthebest.vo.TeamMemberVO;
import dc.human.whosthebest.vo.TeamProfileVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

public interface TeamController {

    public String teamMakePage();

    public ModelAndView insertTeamInfo(@SessionAttribute(name = "loginId", required = false) String loginId,
                                       @ModelAttribute("teamInfo") TeamInfoVO teamInfo) throws Exception;

    public ModelAndView insertTeamMember(@SessionAttribute(name = "loginId", required = false) String loginId,
                                         @RequestParam("tID") int tID,
                                         RedirectAttributes redirectAttributes) throws Exception;

    public ModelAndView listTeams(@SessionAttribute(name = "loginId", required = false) String loginId) throws Exception;

    public ModelAndView myTeamPage(@SessionAttribute(name = "loginId", required = false) String loginId);

    public ResponseEntity<Map<String, Object>> getTeamProfile(@RequestParam("tID") int tID);

    //팀멤버 임시
    public ModelAndView teamMembersPage(@SessionAttribute(name = "loginId", required = false) String loginId,
                                        @RequestParam("tID") int tID);

//    public ModelAndView Ranking() throws Exception;
    //허진욱
    public ModelAndView Ranking(@SessionAttribute(name = "loginId", required = false) String loginId,
                                @RequestParam(name="region", required = false) String region,
                                @RequestParam(name="search", required = false) String search
                               ) throws Exception;
    public List<TeamInfoVO> SearchRanking(@SessionAttribute(name = "loginId", required = false) String loginId,
                                    @RequestParam(name="region", required = false) String region,
                                    @RequestParam(name="search", required = false) String search
                                ) throws Exception;



}
