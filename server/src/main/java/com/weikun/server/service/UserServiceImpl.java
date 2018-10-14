package com.weikun.server.service;

import com.weikun.server.mapper.MyAccountDy;
import com.weikun.server.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 创建者：weikun【YST】   日期：2018/10/14
 * 说说功能：
 */
@Service
public class UserServiceImpl {
    @Autowired
    private MyAccountDy adao;

    public Account login(Map<String,String> map){
        Account a=adao.login(map);
        return a;

    }

}
