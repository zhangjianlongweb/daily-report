package com.weikun.server.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2018/10/21
 * ˵˵���ܣ�
 */
@Repository
public class RedisMapper {

    @Autowired
    public RedisTemplate<Serializable,Serializable> redisTemplate;
    @Autowired
    private RedisTemplate<Serializable,Serializable> redisTemplate1;//�Ƽ��ַ��������͵Ĵ洢

    public void setExpire(String key){
        redisTemplate.expire(key,30, TimeUnit.MINUTES);
    }
    public Set keysQuery(String key){
        Set set=redisTemplate.keys(key);
        return set;
    }


    public Set<Object> unionSetObject(Set otherkeys){//ͨ�����ļ��ϣ������еĶ���ȡ���������ض��󼯺�
        SetOperations s=redisTemplate.opsForSet();
        return s.union("",otherkeys);


    }
    public Object getSet(String key){

        SetOperations s=redisTemplate.opsForSet();
        return s.members(key);
    }
    public void setSet(String key,Object obj){
        SetOperations s=redisTemplate.opsForSet();

        s.add(key,obj);
    }
    public void del(String key){
        redisTemplate.delete(key);
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
        //2���ýű�Դ�ļ�
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(funName)));

        //3���ýű������б�
        script.setResultType(List.class);
        List list =redisTemplate1.execute(script,args);
        return list;
    }
    public String getString(String key){
        ValueOperations vs=redisTemplate1.opsForValue();
        return vs.get(key).toString();
    }
    public void setString(String key,String value){
        ValueOperations vs=redisTemplate1.opsForValue();
        vs.set(key,value);

    }
}
