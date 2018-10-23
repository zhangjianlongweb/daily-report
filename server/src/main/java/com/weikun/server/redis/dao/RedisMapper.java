package com.weikun.server.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 创建者：weikun【YST】   日期：2018/10/21
 * 说说功能：
 */
@Repository
public class RedisMapper {

    @Autowired
    public RedisTemplate<Serializable,Serializable> redisTemplate;
    @Autowired
    private RedisTemplate<Serializable,Serializable> redisTemplate1;//推荐字符串的类型的存储

    public void setExpire(String key){
        redisTemplate.expire(key,30, TimeUnit.MINUTES);
    }
    public Set keysQuery(String key){
        Set set=redisTemplate.keys(key);
        return set;
    }


    public Set<Object> unionSetObject(Set otherkeys){//通过键的集合，把所有的对象取出来，返回对象集合
        SetOperations s=redisTemplate.opsForSet();
        return s.union("",otherkeys);


    }
    public void setSet(String key,Object obj){
        SetOperations s=redisTemplate.opsForSet();

        s.add(key,obj);
    }

    public void setHashTable(String tablename,String key ,Object value){
        HashOperations hash=redisTemplate.opsForHash();
        hash.put(tablename,key,value);
    }
    public Object getHashTableByKey(String tablename,String key){
        HashOperations hash=redisTemplate.opsForHash();

        return hash.get(tablename,key);

    }
    public List getHashTable(String tablename){
        HashOperations hash=redisTemplate.opsForHash();

        List list=hash.values(tablename);
        return list;
    }

    public List executeRedisByLua(List args, String funName){

        DefaultRedisScript<List> script=new DefaultRedisScript();
        //2设置脚本源文件
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(funName)));

        //3设置脚本参数列表
        script.setResultType(List.class);
        List list =redisTemplate1.execute(script,args);
        return list;
    }

}
