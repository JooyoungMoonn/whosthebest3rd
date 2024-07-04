package dc.human.whosthebest.myPage.controller;

import dc.human.whosthebest.myPage.serivce.MyPageService;

import dc.human.whosthebest.vo.MyPageInfoVO;
import dc.human.whosthebest.vo.RecentGameVO;
import dc.human.whosthebest.vo.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("myPageController")
public class MyPageControllerImpl implements  MyPageController {

    @Autowired
    private MyPageService myPageService;

    //마이페이지 메인
    @Override
    @RequestMapping(value = "/myPage", method = RequestMethod.GET)
    public ModelAndView myPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

//        HttpSession session = request.getSession();
//        String uID = (String) session.getAttribute("loginId");

        List<MyPageInfoVO> myPageInfo = myPageService.getUserinfo();
        List<RecentGameVO> recentGame = myPageService.getRecentGame();
        ModelAndView mav = new ModelAndView("/myPage/myPage");
        mav.addObject("myPageInfo", myPageInfo);
        mav.addObject("recentGame", recentGame);
        return mav;
    }

    //마이페이지 - 내가 참여한 경기결과
    @Override
    @RequestMapping(value = "/myPage/myGameRecord")
    public ModelAndView myGameRecord(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception{
        List<RecentGameVO> myGameRecord = myPageService.getRecentGame();
        ModelAndView mav = new ModelAndView("/myPage/myGameRecord");
        mav.addObject("recentGame", myGameRecord);
        return  mav;
    }


  /*  마이페이지 - 회원정보수정 전 비밀번호 확인 */
 
    
    /*마이페이지  - 회원정보수정*/
    @Override
    @RequestMapping(value = "/myPage/updateMyInfo" ,method=RequestMethod.GET)
    public  ModelAndView loadInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<UserInfoVO> allMyInfo = myPageService.loadUserInfo();
        ModelAndView mav = new ModelAndView("/myPage/updateMyInfo");
        mav.addObject("allMyInfo", allMyInfo);
        return  mav;
    }

    @RequestMapping(value = "/myPage/updateMyInfo" ,method=RequestMethod.POST)
    public String updateMyInfo(@RequestParam("uID") String uID,
                               @RequestParam("uName") String uName,
                               @RequestParam("uBday") String uBday,
                               @RequestParam("uAddr1") String uAddr1,
                               @RequestParam("uAddr2") String uAddr2,
                               @RequestParam("uEmail") String uEmail,
                               @RequestParam("uPhone") String uPhone) throws Exception {

    myPageService.updateInfo(uID,uName,uBday,uAddr1,uAddr2, uEmail, uPhone);
    return "redirect:/myPage";

    }

}


