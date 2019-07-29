package com.gopoop.bd.tsc.service;

import com.gopoop.bd.tsc.common.utils.StringUtils;
import com.gopoop.bd.tsc.jdbc.sql.SqlExecuteObject;
import com.gopoop.bd.tsc.jdbc.sql.generator.*;
import com.gopoop.bd.tsc.spider.pipeline.PipelineConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
 *
 * @date 2019/7/19 17:46
 */
@Service
public class JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * 插入
     * @param executeObject
     * @return
     */
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

    /**
     * 更新
     * @param executeObject
     * @return
     */
    public int update(SqlExecuteObject executeObject){
        SqlGenerator generator = new UpdateSqlGenerator(executeObject);
        return jdbcTemplate.update(generator.generate());
    }

    /**
     * 列表查询
     * @param sqlExecuteObject
     * @return
     */
    public List<Map<String,Object>> listMap(SqlExecuteObject sqlExecuteObject){
        SqlGenerator generator = new SelectSqlGenerator(sqlExecuteObject);
        return jdbcTemplate.queryForList(generator.generate());
    }

    /**
     * 列表查询
     * @param sqlExecuteObject
     * @return
     */
    public <T> List<T> listObject(SqlExecuteObject sqlExecuteObject,Class<T> tClass){
        SqlGenerator generator = new SelectSqlGenerator(sqlExecuteObject);
        return jdbcTemplate.query(generator.generate(),new BeanPropertyRowMapper<>(tClass));
    }

    /**
     * 查询总数
     * @param sqlExecuteObject
     * @return
     */
    public Long count(SqlExecuteObject sqlExecuteObject){
        SelectSqlGenerator generator = new SelectSqlGenerator(sqlExecuteObject);
        return jdbcTemplate.queryForObject(generator.getCountSql(),Long.class);
    }


    /**
     * 获取单条记录
     * @param sqlExecuteObject
     * @return
     */
    public Map<String, Object> queryOneMap(SqlExecuteObject sqlExecuteObject){
        SelectSqlGenerator generator = new SelectSqlGenerator(sqlExecuteObject);
        return jdbcTemplate.queryForMap(generator.generate());
    }

    /**
     * 获取单条记录
     * @param sqlExecuteObject
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T queryOneObject(SqlExecuteObject sqlExecuteObject,Class<T> tClass){
        SelectSqlGenerator generator = new SelectSqlGenerator(sqlExecuteObject);
        return jdbcTemplate.queryForObject(generator.generate(),tClass);
    }

    /**
     * 检查表是否存在
     * @param tableName
     * @return
     */
    public boolean checkTableExist(String tableName){
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='tsc' AND TABLE_NAME= " + StringUtils.surround(tableName,StringUtils.SINGLE_QUOTES);
        String result = jdbcTemplate.queryForObject(sql,String.class);
        return StringUtils.isNotEmpty(result);
    }

    /**
     * 创建表
     * @param sqlExecuteObject
     */
    public void createTableIfNotExist(SqlExecuteObject sqlExecuteObject) {
        if(!checkTableExist(sqlExecuteObject.getTableName())){
            SqlGenerator sqlGenerator = new CreateTableSqlGenerator(sqlExecuteObject);
            jdbcTemplate.update(sqlGenerator.generate());
        }
    }
}
