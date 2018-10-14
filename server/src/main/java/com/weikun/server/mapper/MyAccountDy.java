package com.weikun.server.mapper;

import com.weikun.server.model.Account;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.Map;

/**
 * 创建者：weikun【YST】   日期：2018/10/14
 * 说说功能：
 */
public interface MyAccountDy {


    @Results({
            @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="xm", property="xm", jdbcType=JdbcType.VARCHAR),
            @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    @SelectProvider(type=MyAccountSQL.class,method = "login")
    public Account login(Map<String,String> par);
}
