package com.project.bookcenter.mdm.service;

import com.project.bookcenter.common.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private CommonDao commonDao;

    public List<Map<String, Object>> selectBookList(Map paramMap) {
        return commonDao.selectList("BookMaster.selectBookList", paramMap);
    }
}