package dc.human.whosthebest.aboutteam.service;

import dc.human.whosthebest.vo.GameListVO;

import java.util.List;

public interface TeamService {
    public List<GameListVO> selectGameSchedule(int tID) throws Exception;
}
