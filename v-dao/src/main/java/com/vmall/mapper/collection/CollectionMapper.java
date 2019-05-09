package com.vmall.mapper.collection;

import com.vmall.pojo.VCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    public List<VCollection> collectionList(@Param("userId")long userId,
                                            @Param("pageNo")long pageNo,
                                            @Param("pageSize")long pageSize);
    public int count(@Param("userId")long userId);
    public int delCollection(@Param("collectionId")long collectionId);
}
