package com.vmall.vauth.service.dev;

import com.vmall.mapper.questionmapper.QuesUserMapper;
import com.vmall.mapper.questionmapper.QuestionMapper;
import com.vmall.mapper.user.UserMapper;
import com.vmall.pojo.VQuestion;
import com.vmall.vauth.service.tool.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DevUserService {
    @Resource
    TokenService tokenService;
    @Resource
    UserMapper userMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    QuesUserMapper quesUserMapper;
    public VUesr getVUesrById(String token){
        VUesr vUesr=(VUesr)tokenService.get(token);
        return userMapper.getVUesrById(vUesr.getvUserId());
    }
    public List<VQuestion> allQuestion(){
        return questionMapper.allQuestion();
    }
    public int addQuestion(int quesId,String token,String answer){
        VUesr vUesr=(VUesr)tokenService.get(token);
        return quesUserMapper.insert(vUesr.getvUserId(),quesId,answer);
    }
}
