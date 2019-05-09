package com.vmall.mapper.score;

import com.vmall.pojo.VScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {
    public List<VScore> scorelist(@Param("userId")long userId,
                                  @Param("pageNo")long pageNo,
                                  @Param("pageSize")long pageSize);
    public int count(@Param("userId")long userId);
    public VScore ifSignIn(@Param("userId")long userId);
    public int addScore(VScore vScore);
}
