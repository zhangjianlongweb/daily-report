package com.weikun.server.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 创建者：weikun【YST】   日期：2018/10/14
 * 说说功能：
 */
public class MyAccountSQL {
    public String login(Map<String,String> par ){
        return new SQL(){
            {
                SELECT("*");
                FROM("account a");
                WHERE("a.username=#{username} " +
                        "and a.password=#{password}");
            }

        }.toString();
    }
}
