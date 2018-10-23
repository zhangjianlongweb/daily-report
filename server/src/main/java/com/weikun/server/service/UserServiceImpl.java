package com.weikun.server.service;

import com.weikun.server.config.MyConst;
import com.weikun.server.mapper.AccountMapper;
import com.weikun.server.mapper.MyAccountDy;
import com.weikun.server.mapper.ProfileMapper;
import com.weikun.server.model.Account;
import com.weikun.server.redis.dao.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 创建者：weikun【YST】   日期：2018/10/14
 * 说说功能：
 */
@Service
public class UserServiceImpl {
    @Autowired
    private MyAccountDy adao;

    @Autowired
    private AccountMapper acdao;

    @Autowired
    private ProfileMapper pdao;

    @Autowired
    private RedisMapper rdao;

    public Account login(Map<String,String> map){
       // Account a=adao.login(map);
        Object o=rdao.getHashTableByKey("account",map.get("username"));
        if(o==null){
            return null;
        }
        Account a=(Account)o;
        if(map.get("password").toString().equals(a.getPassword())){
            return a;
        }else{
            return null;
        }


    }
    public String verCookie(String sessionid){//校验该sessionid指定的用户是否是用户
        List list=new ArrayList();
        list.add(sessionid);//usercookie+userid
        List result=rdao.executeRedisByLua(list,"verCookie.lua");
        return result.get(0).toString();
    }

    public void delCookie(String sessionid){
        List list=new ArrayList();
        list.add(sessionid);//usercookie+userid
        rdao.executeRedisByLua(list,"delCookie.lua");
    }

    public void register(Account account){
        acdao.insert(account);//mysql
        account.getProfile().setUserid(account.getUserid());
        pdao.insert(account.getProfile());//mysql
        //redis
        rdao.setHashTable("account",account.getUsername(),account);


        rdao.setHashTable("profile",account.getProfile().getUserid().toString(),account.getProfile());

    }
    public void addCookie(Account a,String sessionid){
        List list=new ArrayList();
        list.add(sessionid);//usercookie+userid
        list.add(a.getUserid()+":"+a.getUsername());//值
        rdao.executeRedisByLua(list,"addCookie.lua");

        rdao.setExpire(MyConst.USERCOOKIE+":"+MyConst.SESSIONID1);
    }

}
