package com.weikun.server.controller;

import com.weikun.server.model.Account;
import com.weikun.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建者：weikun【YST】   日期：2018/10/14
 * 说说功能：
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/acc"},name="账户操作")
@Api(value = "acc",description = "账户操作数据接口")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @ApiOperation(value = "loginAccount",notes = "登录账户信息")
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "返回错误"),
            @ApiResponse(code=200,message = "返回成功")
    })
    @PostMapping(value = {"/login"})
    public ResponseEntity<Account> login(@RequestBody Account account){
        System.out.println(account.getUsername());
        Map<String,String> map=new HashMap<String,String>();
        map.put("username",account.getUsername());
        map.put("password",account.getPassword());
        Account a=service.login(map);
        if(a==null){
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Account>(a,HttpStatus.OK);
        }
    }

}
