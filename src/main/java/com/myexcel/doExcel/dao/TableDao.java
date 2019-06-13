package com.myexcel.doExcel.dao;

import com.myexcel.doExcel.util.HashDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TableDao {
    /**
     * 根据传入条件，获取表清单
     * @param param
     * @return
     */
    List<HashDto> getTableList(@Param("param") HashDto param);

    /**
     * 获取列
     * @param param
     * @return
     */
    List<HashDto> getColumn(@Param("param") HashDto param);


}
