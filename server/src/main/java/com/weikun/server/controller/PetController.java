package com.weikun.server.controller;

import com.weikun.server.model.Account;
import com.weikun.server.model.Category;
import com.weikun.server.model.Item;
import com.weikun.server.model.Product;
import com.weikun.server.service.PetServiceImpl;
import com.weikun.server.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 创建者：weikun【YST】   日期：2018/10/21
 * 说说功能：
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/pet"},name="宠物操作")
@Api(value = "pet",description = "宠物操作数据接口")
public class PetController {

    @Autowired
    private PetServiceImpl service;

    @ApiResponses(value = {
            @ApiResponse(code=404,message = "查询错误"),
            @ApiResponse(code=200,message = "查询成功")
    })
    @GetMapping(value = {"/qc"})
    public ResponseEntity<List> qc(){
        List<Category> list=service.queryCategory();
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "查询产品错误"),
            @ApiResponse(code=200,message = "查询产品成功")
    })
    @GetMapping(value = {"/qp/{cid}"})
    public ResponseEntity<List> qp(@PathVariable String cid){//查询product
        List<Product> list=service.querProduct(cid);
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }
    @GetMapping(value = {"/qis/{pid}"})
    public ResponseEntity<List> qis(@PathVariable String pid){//查询product
        List<Item> list=service.querItems(pid);
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @GetMapping(value = {"/qi/{iid}/{pid}"})
    public ResponseEntity<List> qi(@PathVariable String iid,@PathVariable String pid){//查询product
        List<Item> list=service.querItem(pid,iid);
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }


}
