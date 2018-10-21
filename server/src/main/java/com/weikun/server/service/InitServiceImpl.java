package com.weikun.server.service;

import com.weikun.server.mapper.AccountMapper;
import com.weikun.server.mapper.CategoryMapper;
import com.weikun.server.mapper.ProfileMapper;
import com.weikun.server.model.*;
import com.weikun.server.redis.dao.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2018/10/21
 * 说说功能：
 */
@Service
public class InitServiceImpl {
    @Autowired
    private RedisMapper rdao;

    @Autowired
    private CategoryMapper cdao;

    @Autowired
    private AccountMapper adao;

    @Autowired
    private ProfileMapper pdao;

    public void flushdb(){
        List list=new ArrayList();
        rdao.executeRedisByLua(list,"flushDB.lua");
    }
    public void init(){
        flushdb();
        this.initAccount();
        this.initProfile();
        this.initCategory();
    }
    private void initAccount(){
        AccountExample e=new AccountExample();
        e.createCriteria().andUseridIsNotNull();

        List<Account> list=adao.selectByExample(e);

        list.forEach(c->rdao.setHashTable("account",c.getUsername(),c));

    }
    private void initCategory(){

        CategoryExample ca=new CategoryExample();
        ca.createCriteria().andCatidIsNotNull();

        List<Category> list=cdao.selectByExample(ca);
        list.forEach(c->rdao.setHashTable("category",c.getCatid().toString(),c));


    }
    private void initProfile(){
        ProfileExample e=new ProfileExample();
        e.createCriteria().andUseridIsNotNull();

        List<Profile> list=pdao.selectByExample(e);

        list.forEach(c->rdao.setHashTable("profile",c.getUserid().toString(),c));

    }

}
