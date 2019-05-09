package com.vmall.mapper.aftersale;

import com.vmall.pojo.VAftersale;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AfterSaleMapper {
    public List<VAftersale> afterSaleList(@Param("userId")long userId,
                                          @Param("pageNo")long pageNo,
                                          @Param("pageSize")long pageSize);
    public VAftersale getAfterSale(@Param("userId")long userId,
                                   @Param("aftersaleId")long aftersaleId);
    public int count(@Param("userId")long userId);
}
