package com.project.bookcenter.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommonDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    public int insert(String queryId, Map<String, Object> param) {
        return sqlSession.insert(queryId, param);
    }

    public int update(String queryId, Map<String, Object> param) {
        return sqlSession.update(queryId, param);
    }

    public int delete(String queryId, Map<String, Object> param) {
        return sqlSession.delete(queryId, param);
    }

    public Map<String, Object> selectOne(String queryId, Map<String, Object> param) {
        return sqlSession.selectOne(queryId, param);
    }

    public List<Map<String, Object>> selectList(String queryId, Map<String, Object> param) {
        return sqlSession.selectList(queryId, param);
    }
}