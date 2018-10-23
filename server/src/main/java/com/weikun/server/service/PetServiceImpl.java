package com.weikun.server.service;

import com.weikun.server.mapper.CategoryMapper;
import com.weikun.server.model.Category;
import com.weikun.server.model.CategoryExample;
import com.weikun.server.model.Item;
import com.weikun.server.model.Product;
import com.weikun.server.redis.dao.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<Item> querItem(String productid,String iid){
        Set set=rdao.keysQuery("item:"+productid+":"+iid);
        Set s1=rdao.unionSetObject(set);
        List<Item> list=new ArrayList(s1);
        return list;
    }

    public List<Item> querItems(String productid){
        Set set=rdao.keysQuery("item:"+productid+"*");
        Set s1=rdao.unionSetObject(set);
        List<Item> list=new ArrayList(s1);
        return list;
    }
    public List<Product> querProduct(String caid){
        Set set=rdao.keysQuery("product:"+caid+"*");
        Set s1=rdao.unionSetObject(set);
        List<Product> list=new ArrayList(s1);
        return list;

    }

}
