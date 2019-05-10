package com.vmall.vauth.service.dev;

import com.vmall.mapper.score.ScoreMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VScore;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.tool.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScoreService {
    @Resource
    ScoreMapper scoreMapper;
    @Resource
    TokenService tokenService;
    public List<VScore> scoreList(String token, Page page){
        VUser vUesr= (VUser) tokenService.get(token);
        return scoreMapper.scorelist(vUesr.getvUserId(),(page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public VScore ifSignIn(String token){
        VUser vUesr= (VUser) tokenService.get(token);
        return scoreMapper.ifSignIn(vUesr.getvUserId());
    }
    public boolean addScore(VScore vScore,String token){
        VUser vUesr= (VUser) tokenService.get(token);
        if(scoreMapper.ifSignIn(vUesr.getvUserId())!=null){
            return false;
        }
        vScore.setV_userId(vUesr.getvUserId());
        return scoreMapper.addScore(vScore)>0;
    }
    public int count(String token){
        VUser vUser=(VUser)tokenService.get(token);
        return scoreMapper.count(vUser.getvUserId());
    }
}
