package com.weikun.server.service;

import com.weikun.server.mapper.CategoryMapper;
import com.weikun.server.model.Category;
import com.weikun.server.model.CategoryExample;
import com.weikun.server.redis.dao.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2018/10/21
 * 说说功能：
 */
@Service
public class PetServiceImpl {
    @Autowired
    private CategoryMapper cdao;

    @Autowired
    private RedisMapper rdao;


    public List queryCategory(){
        return rdao.getHashTable("category");

    }

}
