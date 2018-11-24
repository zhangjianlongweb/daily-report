package com.weikun.server.controller;

import com.weikun.server.model.Cart;
import com.weikun.server.model.Orders;
import com.weikun.server.service.CartServiceImpl;
import com.weikun.server.service.PetServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2018/10/28
 * ˵˵���ܣ�
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/cart"},name="���ﳵ����")
@Api(value = "cart",description = "���ﳵ�������ݽӿ�")
public class CartController {

    @Autowired
    private CartServiceImpl service;


    @ApiResponses(value = {
            @ApiResponse(code=404,message = "���Ӵ���"),
            @ApiResponse(code=200,message = "���ӳɹ�")
    })
    @GetMapping(value = {"/show/{userid}"})
    public ResponseEntity<List> showCart(@PathVariable String userid){

        String value=service.getMaxidByUserid(userid);//ͨ��redis ��userid ȡmaxid
        String flag=value.substring(0,1);
        String orderid=value.substring(1);
        int oid=0;
        oid=Integer.parseInt(orderid);
        if(flag.equals("1")){//�Ѿ��ύ���ˣ���Ҫorderid++
            ++oid;
        }
        List <Cart>list=service.showCart(userid,oid+"");


        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code=404,message = "���Ӵ���"),
            @ApiResponse(code=200,message = "���ӳɹ�")
    })
    @PostMapping(value = {"/add"})
    public ResponseEntity<Void> addCart(@RequestBody Cart cart){
        service.addCart(cart);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }



    @ApiResponses(value = {
            @ApiResponse(code=404,message = "�����ύ����"),
            @ApiResponse(code=200,message = "�����ύ�ɹ�")
    })
    @PostMapping(value = {"/checkout"})
    public ResponseEntity<Void> checkout(@RequestBody Orders orders){


        service.checkout(orders);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code=404,message = "�޸Ĵ���"),
            @ApiResponse(code=200,message = "�޸ĳɹ�")
    })
    @PostMapping(value = {"/up"})
    public ResponseEntity<Void> updateCart(@RequestBody Cart cart){
        service.update(cart);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code=404,message = "���Ӵ���"),
            @ApiResponse(code=200,message = "���ӳɹ�")
    })
    @PostMapping(value = {"/del"})
    public ResponseEntity<Void> delCart(@RequestBody Cart cart){
        service.delCart(cart);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
