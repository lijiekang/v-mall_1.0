package com.vmall.mapper.questionmapper;

import org.apache.ibatis.annotations.Param;

public interface QuesUserMapper {
    public int insert(@Param("userId")long userId,@Param("quesId")int quesId,@Param("answer")String answer);
}
