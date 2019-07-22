package com.gopoop.bd.tsc.service;

import cn.hutool.core.collection.CollectionUtil;
import com.gopoop.bd.tsc.common.utils.StringUtils;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.jdbc.sql.generator.InsertSqlGenerator;
import com.gopoop.bd.tsc.jdbc.sql.generator.SelectSqlGenerator;
import com.gopoop.bd.tsc.jdbc.sql.generator.SqlGenerator;
import com.gopoop.bd.tsc.jdbc.sql.generator.UpdateSqlGenerator;
import com.gopoop.bd.tsc.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 郭速凯
 * @date 2019/7/19 17:46
 */
@Service
public class JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    public int insert(SqlExecuteObject executeObject){
        SqlGenerator generator = new InsertSqlGenerator(executeObject);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = generator.generate();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(insertSql,new String[]{"id"});
            }
        },keyHolder);
        return keyHolder.getKey().intValue();
    }


    public int update(SqlExecuteObject executeObject){
        SqlGenerator generator = new UpdateSqlGenerator(executeObject);
        return jdbcTemplate.update(generator.generate());
    }

    public List<Map<String, Object>> list(SqlExecuteObject sqlExecuteObject){
        SqlGenerator generator = new SelectSqlGenerator(sqlExecuteObject);
        return jdbcTemplate.queryForList(generator.generate());
    }




}
