package com.weikun.server.service;

import com.weikun.server.mapper.CartMapper;
import com.weikun.server.mapper.CategoryMapper;
import com.weikun.server.mapper.OrdersMapper;
import com.weikun.server.model.Cart;
import com.weikun.server.model.CartKey;
import com.weikun.server.model.Item;
import com.weikun.server.model.Orders;
import com.weikun.server.redis.dao.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2018/10/28
 * ˵˵���ܣ�
 */
@Service
public class CartServiceImpl {

    @Autowired
    private CartMapper cdao;

    @Autowired
    private OrdersMapper odao;
    @Autowired
    private RedisMapper rdao;
    public List<Cart> showCart(String userid,String orderid){//�����еĶ�������Ʒչʾ

        Set<String> set=rdao.keysQuery("carts:"+userid+":"+orderid+"*");
        List<Cart> clist=new ArrayList<Cart>();

        set.forEach(c->{
            //carts:1:19:26
            String quantity =rdao.getString(c);
            String itemid=c.split(":")[3];
            Cart cart=new Cart();
            cart.setUserid(Integer.parseInt(userid));
            cart.setOrderid(Integer.parseInt(orderid));
            cart.setQuantity(Integer.parseInt(quantity));
            cart.setItemid(Integer.parseInt(itemid));
            //System.out.println(productid);
            //ͨ��itemidȥ��ÿ����Ʒ��productid

            Set s1=rdao.keysQuery("item:"+"*"+":"+itemid);
            List list=new ArrayList(s1);


            //ͨ��itemid ��item
            LinkedHashSet set1=(LinkedHashSet)rdao.getSet(list.get(0).toString());
            Iterator it=set1.iterator();
            while(it.hasNext()){

                Object obj=it.next();
                cart.setItem((Item)obj);
                clist.add(cart);

            }

            System.out.println(set1);
            //clist.add(cart);

        });//��ȡitemid����ȡ����


        return clist;

    }
    public String getMaxidByUserid(String userid){

        String value=rdao.getString("maxid:"+userid);
        return value;//value.substring(1);
    }
    public String addCart(Cart cart){
        boolean iu=true;//i true update false
        //ȡ��orderid
        List list=new ArrayList();
        list.add(cart.getUserid()+"");//1userid
        List result=rdao.executeRedisByLua(list,"getMaxOrderidByUserid.lua");

        //�洢mysql
        String sid=result.get(0).toString().substring(1);
        String flag=result.get(0).toString().substring(0,1);
        int id=Integer.parseInt(sid);

        if(flag.equals("0")){//���Լ�������
            cart.setOrderid(id);
        //��Ҫ���� ���û� ���� ��Ŀ��ͬ ��������
            // ������������
            //���� �û�id ����id itemid�����Ƿ����������

            list.clear();
            list.add("carts:"+cart.getUserid()+":"+cart.getOrderid()+":"+cart.getItemid());

            List l=rdao.executeRedisByLua(list,"getQuantity.lua");
            if(l.get(0)!=null){//����Ʒ ����
                cart.setQuantity(Integer.parseInt(l.get(0).toString())+cart.getQuantity());
                iu=false;
            }


        }else{
            ++id;
            cart.setOrderid(id);

            //����orders ������������orderid
            Orders os=new Orders();

            os.setOrderid(id);
            os.setUserid(cart.getUserid());
            odao.insert(os);
        }

        if(!iu){//��Ϊ�Ѿ��е�ǰ��Ʒ��
            cdao.updateByPrimaryKey(cart);
        }else{
            cdao.insert(cart);
        }

        list.clear();
        //�洢redis cart
        list.add(cart.getUserid()+"");//1userid
        list.add(cart.getOrderid()+"");//2orderid
        list.add(cart.getItemid()+"");//3itemid
        list.add(cart.getQuantity()+"");//4quantity �Ѿ���������
        //01 11
        String str= result.get(0).toString();
        String f=str.substring(1);//ȡ�������Ƿ��Ѿ��ύ�ı�־λ 0���������� 1���Ѿ���Ǯ��
        list.add(f+"");
        rdao.executeRedisByLua(list,"addCart.lua");

        //�洢redis orders
        if(flag.equals("1")){//�¶���
            list.clear();
            list.add(cart.getUserid()+"");//1userid
            list.add(cart.getOrderid()+"");//2orderid

            list.add(flag+id);//3 maxid�ı��
            list.add("0"+id);//4 maxid�ı��
            rdao.executeRedisByLua(list,"addOrders.lua");
        }
        //01 11
        return id+"";
    }


    public void delCart(Cart cart) {
        //ɾ��redis cart
        rdao.del("carts:"+cart.getUserid()+":"+cart.getOrderid()+":"+cart.getItemid());//cart
        //ɾ��mysql
        CartKey key=new CartKey();
        key.setUserid(cart.getUserid());
        key.setItemid(cart.getItemid());
        key.setOrderid(cart.getOrderid());
        cdao.deleteByPrimaryKey(key);
    }

    public void update(Cart cart) {
        //redis �޸�
        rdao.setString("carts:"+cart.getUserid()+":"+cart.getOrderid()+":"+cart.getItemid(),cart.getQuantity().toString());

        //mysql �޸�
        cdao.updateByPrimaryKey(cart);


    }

    public void checkout(Orders orders) {
        //�޸�MYSQL orders��
        orders.setOrderdate(new Date());
        odao.updateByPrimaryKey(orders);
        //�޸�redis��maxid��
        rdao.setString("maxid:"+orders.getUserid(),"1"+orders.getOrderid());

    }
}
