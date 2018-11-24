package com.weikun.server.controller;

import com.weikun.server.config.MyConst;
import com.weikun.server.model.Account;
import com.weikun.server.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * �����ߣ�weikun��YST��   ���ڣ�2018/10/14
 * ˵˵���ܣ�
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/acc"},name="�˻�����")
@Api(value = "acc",description = "�˻��������ݽӿ�")
public class UserController {

    @Autowired
    private UserServiceImpl service;


    @ApiOperation(value = "registAccount",notes = "ע���˻���Ϣ")
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "ע�����"),
            @ApiResponse(code=200,message = "ע��ɹ�")
    })
    @PostMapping(value = {"/reg"})
    public ResponseEntity<Void> reg(@RequestBody Account account){
        service.register(account);


        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "loginAccount",notes = "��¼�˻���Ϣ")
    @ApiResponses(value = {
            @ApiResponse(code=404,message = "���ش���"),
            @ApiResponse(code=200,message = "���سɹ�")
    })
    @GetMapping(value = {"/ver"})
    public ResponseEntity<String> verify( ){
        String result=service.verCookie(MyConst.USERCOOKIE+":"+MyConst.SESSIONID1);

        System.out.println(result);
        if(result.indexOf(":")!=-1){
            //String s=result.split(":")[1];

            if(result!=null){//�ο�
                return new ResponseEntity<String>(result,HttpStatus.OK);

            }else{

                return new ResponseEntity<String>("gc",HttpStatus.NOT_FOUND);
            }

        }else{

            return new ResponseEntity<String>("gc",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = {"/out"})
    public ResponseEntity<Void> out(){
        service.delCookie(MyConst.USERCOOKIE+":"+MyConst.SESSIONID1);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

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
            //������
            service.addCookie(a,MyConst.USERCOOKIE+":"+MyConst.SESSIONID1);
            return new ResponseEntity<Account>(a,HttpStatus.OK);
        }
    }

}
