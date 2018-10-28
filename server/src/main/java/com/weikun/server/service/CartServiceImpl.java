package com.weikun.server.service;

import com.weikun.server.mapper.CartMapper;
import com.weikun.server.mapper.CategoryMapper;
import com.weikun.server.mapper.OrdersMapper;
import com.weikun.server.model.Cart;
import com.weikun.server.model.Orders;
import com.weikun.server.redis.dao.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2018/10/28
 * 说说功能：
 */
@Service
public class CartServiceImpl {

    @Autowired
    private CartMapper cdao;

    @Autowired
    private OrdersMapper odao;
    @Autowired
    private RedisMapper rdao;

    public void addCart(Cart cart){
        boolean iu=true;//i true update false
        //取出orderid
        List list=new ArrayList();
        list.add(cart.getUserid()+"");//1userid
        List result=rdao.executeRedisByLua(list,"getMaxOrderidByUserid.lua");

        //存储mysql
        String sid=result.get(0).toString().substring(1);
        String flag=result.get(0).toString().substring(0,1);
        int id=Integer.parseInt(sid);

        if(flag.equals("0")){//可以继续购物
            cart.setOrderid(id);
        //需要叠加 当用户 订单 项目相同 数量叠加
            // 订单数量叠加
            //根据 用户id 订单id itemid，查是否曾经买过，

            list.clear();
            list.add("carts:"+cart.getUserid()+":"+cart.getOrderid()+":"+cart.getItemid());

            List l=rdao.executeRedisByLua(list,"getQuantity.lua");
            if(l.get(0)!=null){//老商品 叠加
                cart.setQuantity(Integer.parseInt(l.get(0).toString())+cart.getQuantity());
                iu=false;
            }


        }else{
            ++id;
            cart.setOrderid(id);

            //操作orders 主表，必须先有orderid
            Orders os=new Orders();

            os.setOrderid(id);
            os.setUserid(cart.getUserid());
            odao.insert(os);
        }

        if(!iu){//因为已经有当前商品了
            cdao.updateByPrimaryKey(cart);
        }else{
            cdao.insert(cart);
        }

        list.clear();
        //存储redis cart
        list.add(cart.getUserid()+"");//1userid
        list.add(cart.getOrderid()+"");//2orderid
        list.add(cart.getItemid()+"");//3itemid
        list.add(cart.getQuantity()+"");//4quantity 已经叠加完了
        //01 11
        String str= result.get(0).toString();
        String f=str.substring(1);//取出的是是否已经提交的标志位 0：继续购物 1：已经交钱了
        list.add(f+"");
        rdao.executeRedisByLua(list,"addCart.lua");

        //存储redis orders
        if(flag.equals("1")){//新订单
            list.clear();
            list.add(cart.getUserid()+"");//1userid
            list.add(cart.getOrderid()+"");//2orderid

            list.add(flag+id);//3 maxid的标记
            list.add(flag+id);//4 maxid的标记
            rdao.executeRedisByLua(list,"addOrders.lua");
        }

        //01 11






    }


}
