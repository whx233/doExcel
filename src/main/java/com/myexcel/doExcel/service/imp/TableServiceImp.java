package com.myexcel.doExcel.service.imp;

import com.myexcel.doExcel.dao.TableDao;
import com.myexcel.doExcel.service.TableService;
import com.myexcel.doExcel.util.HashDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TableServiceImp implements TableService {

    @Resource
    TableDao tableDao;

    @Override
    public List<HashDto> getTabList(HashDto param){
        return tableDao.getTableList(param);
    }

    @Override
    public List<HashDto> getColumn(HashDto param){
        return tableDao.getColumn(param);
    }

}
