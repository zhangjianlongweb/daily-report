package com.weikun.server.mapper;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2018/10/14
 * ˵˵���ܣ�
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
