package dc.human.whosthebest.team.controller;

import dc.human.whosthebest.team.dao.TeamDAO;
import dc.human.whosthebest.team.service.TeamService;
import dc.human.whosthebest.team.service.TeamServiceImpl;
import dc.human.whosthebest.vo.GameListVO;
import dc.human.whosthebest.vo.TeamInfoVO;
import dc.human.whosthebest.vo.TeamMemberVO;
import dc.human.whosthebest.vo.TeamProfileVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("teamController")
public class TeamControllerImpl implements TeamController{

    @Autowired
    private TeamService teamService;


    //팀 만들기 페이지 단순 매핑
    @Override
    @GetMapping("/teamMake")
    public String teamMakePage() {
        return "/team/teamMake";
    }


    //팀 만들기 insert
    //팀원 목록에 추가하는 것과 동시에 시행
        //프로필 사진 넣기 필요
        //해당지역 이미 생성되있는 팀이름 검사 필요
        //가입한 팀 3개 검사 필요
        //팀 만들때 팀장도 team_member 테이블에 저장 필요
    @Override
    @RequestMapping(value="/insertTeamInfo" ,method = RequestMethod.POST)
    public ModelAndView insertTeamInfo(@SessionAttribute(name = "loginId", required = false) String loginId,
                                       @ModelAttribute("teamInfo") TeamInfoVO teamInfo) throws Exception {
        ModelAndView mav = new ModelAndView();
        String userID = loginId;
        //실제 userID 가져오는 로직으로 대체
//        String userID = request.getSession().getAttribute("userID").toString();
        try {
            //팀생성과 동시에 팀원 목록에도 팀장 추가
            int result = teamService.createTeamAndAddMember(teamInfo, userID);
            String viewName = "";

            if (result < 1) {
                mav.addObject("errorMsg", "정상적으로 입력이 되지 않았습니다");
                viewName = "team/teamList";
            } else {
                viewName = "redirect:/teamList";
            }
            mav.setViewName(viewName);
        } catch (Exception ex) {
            mav.addObject("errorMsg", "정상적으로 입력이 되지 않았습니다");
            mav.setViewName("team/teamList");
            System.out.println(ex.getMessage());
        }
        return mav;
        //최소나이 최대나이 처리 필요
        //중복 기능 따로 만들기
    }

    //팀 가입하기(유저)
        //이미 가입한 팀 검사
        //3개이상 가입 금지
        //팀 가입시 알림
    @Override
    @RequestMapping(value="/insertTeamMember" ,method = RequestMethod.POST)
    public ModelAndView insertTeamMember(@SessionAttribute(name = "loginId", required = false) String loginId,
                                         @RequestParam("tID") int tID) throws Exception{
        ModelAndView mav = new ModelAndView();
        String viewName = "";
        try {

            String userID = loginId;
            TeamMemberVO teamMember = new TeamMemberVO();
            teamMember.setCreatedID(userID);
            teamMember.setuID(userID);
            teamMember.settID(tID);
            int insertTeamMemberResult = teamService.insertTeamMember(teamMember);


            if(insertTeamMemberResult<1) {
                mav.addObject("errorMsg", "팀 가입 실패");
                viewName = "team/teamList";
            } else {
                mav.addObject("errorMsg", tID+"팀 가입 성공");
                viewName = "redirect:/teamList";
                //redirect 수정 핋요
            }
            mav.setViewName(viewName);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return mav;
    }

    //팀목록 페이지 매핑
        //페이징 처리 필요
        //검색 기능 지역 필터링 메소드 추가 필요
    @Override
    @GetMapping("/teamList")
    public ModelAndView listTeams() throws Exception{
        List teamsList = teamService.listTeams();
        ModelAndView mav = new ModelAndView("/team/teamList");
        mav.addObject("teamsList", teamsList);
        return mav;
    }

    //나의팀 페이지
        //더보기 또는 사이드바 메뉴 매핑 필요
        //프로필 사진 불러오기 필요
    @Override
    @GetMapping("/myTeam")
    public ModelAndView myTeamPage(@SessionAttribute(name = "loginId", required = false) String loginId) {
        ModelAndView mav = new ModelAndView("/team/myTeam");
        String userID = loginId; // 실제 사용자 ID 가져오는 로직으로 대체

        try {
            //팀 목록 사이드바에 가져오기
            List<TeamInfoVO> myTeams = teamService.getTeamsByUserId(userID);
            mav.addObject("myTeams", myTeams);

            if(myTeams != null && !myTeams.isEmpty()) {
                int tID=myTeams.get(0).gettID();
                TeamProfileVO teamProfile = teamService.getTeamProfile(tID);
                List<GameListVO> gameListVO = teamService.selectGameSchedule(tID);
                mav.addObject("teamProfile", teamProfile);
                mav.addObject("gameList", gameListVO);
            } else {
                mav.addObject("teamProfile",null);
            }
        } catch (Exception e) {
            mav.addObject("errorMsg", "팀 목록을 가져오는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return mav;
    }

    //특정 팀의 상세 정보 반환
    @GetMapping("/api/teamInfo")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getTeamProfile(@RequestParam("tID") int tID) {
        Map<String, Object> response = new HashMap<>();
        try {
            TeamProfileVO teamProfile = teamService.getTeamProfile(tID);
            List<GameListVO> gameListVO = teamService.selectGameSchedule(tID);
            response.put("teamProfile", teamProfile);
            response.put("gameList", gameListVO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    };
    //임시
    //팀 멤버 페이지 매핑
    @Override
    @GetMapping("/teamMembers")
    public ModelAndView teamMembersPage(@SessionAttribute(name = "loginId", required = false) String loginId,
                                        @RequestParam("tID") int tID) {
        ModelAndView mav = new ModelAndView("/team/teamMembers"); // teamMembers.jsp 파일 위치
        String userID = loginId;

        try {
            //팀 목록 사이드바에 가져오기
            List<TeamInfoVO> myTeams = teamService.getTeamsByUserId(userID);
            mav.addObject("myTeams", myTeams);

            List<TeamMemberVO> teamMembers = teamService.getTeamMembers(tID); // 팀원 목록 조회 로직
            mav.addObject("teamMembers", teamMembers);  // 팀원 목록을 ModelAndView에 추가
            mav.addObject("tID",tID);
        } catch (Exception e) {
            mav.addObject("errorMsg", "팀원 목록을 가져오는 도중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        return mav;
    }


    //랭킹 페이지 매핑
        //페이징 처리 필요
        //검색 기능 지역 필터링 메소드 추가 필요
    @Override
    @GetMapping("/ranking")
    public ModelAndView Ranking() throws Exception{
        List ranking = teamService.ranking();
        ModelAndView mav = new ModelAndView("/team/ranking");
        mav.addObject("ranking", ranking);
        return mav;
    }
//커밋 테스트


}
