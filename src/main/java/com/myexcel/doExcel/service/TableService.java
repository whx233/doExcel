package com.myexcel.doExcel.service;

import com.myexcel.doExcel.util.HashDto;

import java.util.List;

public interface TableService {

    List<HashDto> getTabList(HashDto param);

    List<HashDto> getColumn(HashDto param);
}
