package com.vmall.mapper.questionmapper;

import com.vmall.pojo.VQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    public List<VQuestion> allQuestion(@Param("pageNo")long pageNo,@Param("pageSize")long pageSize);
    public int count();
}
