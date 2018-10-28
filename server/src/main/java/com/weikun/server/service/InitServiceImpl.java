package com.weikun.server.service;

import com.weikun.server.mapper.*;
import com.weikun.server.model.*;
import com.weikun.server.redis.dao.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ProductMapper prdao;

    @Autowired
    private OrdersMapper odao;

    @Autowired
    private ItemMapper idao;

    @Autowired
    private CartMapper cadao;

    public void flushdb(){
        List list=new ArrayList();
        rdao.executeRedisByLua(list,"flushDB.lua");
    }
    public void init(){
        flushdb();
        this.initAccount();
        this.initProfile();
        this.initCategory();
        this.initProduct();
        this.initItem();
        this.initOrders();
        this.initCart();


    }
    private void initAccount(){
        AccountExample e=new AccountExample();
        e.createCriteria().andUseridIsNotNull();

        List<Account> list=adao.selectByExample(e);

        list.forEach(c->rdao.setHashTable("account",c.getUsername(),c));

    }
    private void initOrders(){
        OrdersExample example=new OrdersExample();
        example.createCriteria().andOrderidIsNotNull();

        List <Orders>list=odao.selectByExample(example);


        list.forEach(c->{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            if(c.getOrderdate()!=null){
                rdao.setString("orders:"+c.getUserid()+":"+c.getOrderid(),sdf.format(c.getOrderdate())+":"+c.getTotalprice());

            }

        });

        //操作maxid字段 maxid:userid  ->0NO(1NO) 0代表orderdate=null 可以继续购物 1代表orderdate不为空，已经提交订单，再操作需要新开订单

        AccountExample example1=new AccountExample();
        example1.createCriteria().andUseridIsNotNull();//userid
        List<Account> list1=adao.selectByExample(example1);
        list1.forEach(a->{
            Optional optional=list.stream().filter(o->a.getUserid()==o.getUserid()).max((o1, o2)->o1.getOrderid()-o2.getOrderid());
            if(optional.isPresent()){
                Orders o=(Orders)optional.get();
                rdao.setString("maxid:"+a.getUserid(),(o.getOrderdate()==null?"0":"1")+ o.getOrderid());
            }
        });



    }
    private void initCart(){
        CartExample example=new CartExample();
        example.createCriteria().andItemidIsNotNull();

        List <Cart>list=cadao.selectByExample(example);
        list.forEach(c->rdao.setString("carts:"+c.getUserid()+":"+c.getOrderid()+":"+c.getItemid(),c.getQuantity().toString()));


    }
    private void initItem(){
        ItemExample item=new ItemExample();
        item.createCriteria().andItemidIsNotNull();
        List<Item> list=idao.selectByExample(item);

        list.forEach(c->rdao.setSet("item:"+c.getProductid()+":"+c.getItemid(),c));;//productid:itemid
    }
    private void initProduct(){
        ProductExample pro=new ProductExample();
        pro.createCriteria().andProductidIsNotNull();
        List<Product> list=prdao.selectByExample(pro);

        list.forEach(c->rdao.setSet("product:"+c.getCatid()+":"+c.getProductid(),c));//product:catid:productid
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
