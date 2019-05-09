package com.vmall.mapper.user;

import com.vmall.pojo.VCommons;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComMapper {
    public List<VCommons> commonsList(@Param("userId")long userId,
                                      @Param("pageNo")long pageNo,
                                      @Param("pageSize")long pageSize);
    public int count(@Param("userId")long userId);
}
