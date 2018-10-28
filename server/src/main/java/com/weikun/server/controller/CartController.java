package com.weikun.server.controller;

import com.weikun.server.model.Cart;
import com.weikun.server.service.CartServiceImpl;
import com.weikun.server.service.PetServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2018/10/28
 * 说说功能：
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/cart"},name="购物车操作")
@Api(value = "cart",description = "购物车操作数据接口")
public class CartController {

    @Autowired
    private CartServiceImpl service;

    @ApiResponses(value = {
            @ApiResponse(code=404,message = "增加错误"),
            @ApiResponse(code=200,message = "增加成功")
    })
    @PostMapping(value = {"/add"})
    public ResponseEntity<List> addCart(@RequestBody Cart cart){
        service.addCart(cart);

        return null;

    }

}
