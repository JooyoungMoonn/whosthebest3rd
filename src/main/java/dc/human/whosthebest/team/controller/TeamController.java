package dc.human.whosthebest.team.controller;

import dc.human.whosthebest.vo.TeamInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface TeamController {

    public ModelAndView addTeam(@RequestParam("tLogo") MultipartFile file,
                                @ModelAttribute("info") TeamInfoVO teamInfoVO,
                                HttpServletRequest request, HttpServletResponse response) throws Exception;
    public String index();

}
